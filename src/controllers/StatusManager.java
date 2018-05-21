package controllers;

import helpers.CloneHelper;
import javafx.scene.control.Alert;
import models.board.Board;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Status manager for board model, provides un-do/re-do/save/reload functionality
 * The code below does work, but smells like durian.
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class StatusManager
{
    private Stack<Board> statusStack;
    private Stack<Board> redoStack;
    private GameLogic gameLogic;
    private int turnCounter;

    public StatusManager(GameLogic gameLogic)
    {
        this.statusStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.gameLogic = gameLogic;
        this.turnCounter = 0;
    }

    /**
     * Record status
     * Recording will only happens when a turn is finished.
     *
     * @param board current board
     */
    public void recordStatus(Board board)
    {
        try {
            Board clonedBoard = (Board)CloneHelper.deepClone(board);
            this.statusStack.push(clonedBoard);

            // Only records the initial status of each turn
            if(this.isTurnFinishes()) {
                this.statusStack.pop();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform an un-do operation when user requested.
     * Un-do will not happens when status stack is empty
     */
    public void performUndo()
    {
        // Pop out the previous board
        Board board;

        try {
            board = this.statusStack.pop();
        } catch (EmptyStackException emptyEx) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Undo buffer is empty, no more status can be restored.");
            alert.show();
            return;
        }

        // Clear up the board before continue
        this.gameLogic.getBoard().getPieceList().clear();

        // Push into re-do stack for future re-do
        this.redoStack.push(board);

        // Bind game logic
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        this.gameLogic.setBoard(board);
    }

    /**
     * Perform an re-do operation when user requested.
     * Re-do will not happens when re-do status stack is empty
     */
    public void performRedo()
    {
        Board board;
        try {
            board = this.redoStack.pop();
        } catch (EmptyStackException emptyEx) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Re-do buffer is empty, no more status can be restored.");
            alert.show();
            return;
        }

        // Clear up the board before continue
        this.gameLogic.getBoard().getPieceList().clear();

        // Bind game logic and refresh the list to make sure everything is correct
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        this.statusStack.push(board);
        this.gameLogic.setBoard(board);
    }

    /**
     * Serialize status stack to file for future re-load
     * @param filePath file path
     * @throws IOException throws when IO issue occurs
     */
    public void serializeStatusToFile(String filePath) throws IOException
    {
        // Disallow users to save when status stack is empty
        if(this.statusStack.empty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Status buffer is empty, content cannot be saved.");
            alert.show();
            return;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(this.statusStack);

        objectOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Deserialize status stack from a previous status file
     * @param filePath file path to the file
     * @throws IOException throws when IO issue occurs
     * @throws ClassNotFoundException throws when serialization issue occurs
     */
    @SuppressWarnings("unchecked") // ...have no idea, just suppress it...
    public void loadStatusFromFile(String filePath) throws IOException, ClassNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        this.statusStack = (Stack<Board>)objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        // Restore to previous status
        Board restoredBoard = this.statusStack.pop();
        restoredBoard.setGameLogic(this.gameLogic);
        restoredBoard.refreshPieceList();

        // Set previous board
        this.gameLogic.setBoard(restoredBoard);
    }

    /**
     * Detects if the status recorder should keep the status to status stack.
     * Every turn should have 2 times of piece selection, 1 for each side of player.
     *
     * @return True if it needs recording
     */
    private boolean isTurnFinishes()
    {
        if(this.turnCounter < 1) {
            this.turnCounter += 1;
            return false;
        } else {
            this.turnCounter = 0;
            return true;
        }
    }
}

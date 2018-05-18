package controllers;

import helpers.CloneHelper;
import javafx.scene.control.Alert;
import models.board.Board;

import java.io.*;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Status manager for board model, provides un-do/re-do/save/reload functionality
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class StatusManager
{
    private Stack<Board> statusStack;
    private Stack<Board> redoStack;
    private GameLogic gameLogic;

    public StatusManager(GameLogic gameLogic)
    {
        this.statusStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.gameLogic = gameLogic;
    }

    public void recordStatus(Board board)
    {
        try {
            Board clonedBoard = (Board)CloneHelper.deepClone(board);
            this.statusStack.add(clonedBoard);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        this.gameLogic.getBoad().getPieceList().clear();

        // Push into re-do stack for future re-do
        this.redoStack.push(board);

        // Bind game logic
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        this.gameLogic.setBoard(board);
    }

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
        this.gameLogic.getBoad().getPieceList().clear();

        // Bind game logic and refresh the list to make sure everything is correct
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        this.statusStack.push(board);
        this.gameLogic.setBoard(board);
    }

    public void serializeStatusToFile(String filePath) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(this.statusStack);

        objectOutputStream.close();
        fileOutputStream.close();
    }

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
}

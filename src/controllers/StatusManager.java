package controllers;

import helpers.CloneHelper;
import models.board.Board;

import java.io.*;
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

    public Board performUndo()
    {
        Board board = this.statusStack.pop();
        this.redoStack.push(board);
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        return board;
    }

    public Board performRedo()
    {
        Board board = this.redoStack.pop();
        this.statusStack.push(board);
        board.setGameLogic(this.gameLogic);
        board.refreshPieceList();

        return board;
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

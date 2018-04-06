package controllers;

import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import javafx.collections.*;
import models.game.Coordinate;
import models.game.board.Board;
import models.game.piece.helpers.PieceFactory;
import models.gui.BoardButtonEvent;
import models.game.piece.*;


public class GameLogic
{
    private HomeController homeController;
    private Board board;


    public GameLogic(HomeController homeController)
    {
        // Assign home controller
        this.homeController = homeController;
        
        // Initialise board model
        this.board = new Board("Communism", "Capitalism");

        // Add change listener
        board.getPieceList().addListener((ListChangeListener<Piece>) change -> {
            while(change.next()) {
                if(change.wasAdded()) {
                    for(Piece piece : change.getAddedSubList()) {
                        homeController.commitUIChanges(piece.getCoordinate(), piece.getStyle());
                    }
                }

                if(change.wasRemoved()) {
                    for(Piece piece : change.getRemoved()) {
                        homeController.commitUIChanges(piece.getCoordinate(), "");
                    }
                }
            }
        });

        // Add all from random piece factory
        board.getPieceList().addAll(PieceFactory.createRandomPieceList());

        // First turn: communism player (player A)
        board.setCurrentPlayer(board.getCommunismPlayer());
        homeController.commitPlayerSelection(board.getCurrentPlayer());
    }

    /**
     * Commit changes to game map
     * @param buttonEvent Supplied click result information
     */
    public void commitMapChanges(BoardButtonEvent buttonEvent)
            throws DuplicatedPieceException, InvalidPieceSelectionException
    {
        Coordinate coordinate = new Coordinate(buttonEvent.getPosX(), buttonEvent.getPosY());

        // Firstly, try find the piece
        Piece pieceInList = getPieceFromList(coordinate.getPosX(), coordinate.getPosY());

        // Case 1: if it's null and the selected piece is null, then the user must have selected an empty piece,
        //         simply ignore and terminate
        if(pieceInList == null && board.getCurrentPlayer().getSelectedPiece() == null) {
            return;
        }

        // Case 2: if selectedPiece is null but piece found in list is not null, then the user must
        //         selecting a valid piece, simply passing it to selectedPiece.
        if(pieceInList != null && board.getCurrentPlayer().getSelectedPiece() == null) {
            selectPiece(pieceInList);
            return;
        }

        // Case 3: if selectedPiece is not null and piece found in list is null, then user is placing a valid piece
        if(pieceInList == null && board.getCurrentPlayer().getSelectedPiece() != null ) {
            placePiece(coordinate);
            return;
        }

        // Case 4: if selectedPiece is not null and the piece found in list is also not null, then it means the user
        //         is placing a damn piece on a coordinate where it already filled by another piece.
        //         Raise an exception here instead (no needS to evaluate any more)
        throw new DuplicatedPieceException(
                String.format("User putting pieces on a wrong place, x: %d, y: %d",
                        coordinate.getPosX(), coordinate.getPosY()));
    }

    /**
     * Try find the piece in the list
     *
     * @param posX X-axis of the piece
     * @param posY Y-axis of the piece
     * @return The piece object if it has been found, or null if it has not been found
     */
    private Piece getPieceFromList(int posX, int posY)
    {
        for(Piece piece : board.getPieceList()) {
            if(piece.getCoordinate().getPosX() == posX && piece.getCoordinate().getPosY() == posY) {
                return piece;
            }
        }

        return null;
    }

    /**
     * Try select a piece to candidate position
     * @param piece Piece to select
     * @throws InvalidPieceSelectionException Occurs when user selecting a wrong piece which is not in the same role
     */
    private void selectPiece(Piece piece) throws InvalidPieceSelectionException
    {
        // Check their role type first...
        if(piece.getRoleType() != board.getCurrentPlayer().getRoleType()) {
            throw new InvalidPieceSelectionException("Wrong piece selected, check your role please.");
        } else {
            board.getCurrentPlayer().setSelectedPiece(piece);
        }

        homeController.commitPlayerSelection(board.getCurrentPlayer());
        board.getPieceList().remove(piece);

        System.out.println(String.format("User selected piece at x %d, y %d",
                piece.getCoordinate().getPosX(), piece.getCoordinate().getPosY()));
    }

    /**
     * Place a candidate piece to a certain position
     * @param coordinate New coordinate for the candidate piece
     */
    private void placePiece(Coordinate coordinate)
    {
        // Set the new coordinate for the piece
        board.getCurrentPlayer().getSelectedPiece().getCoordinate().setPosX(coordinate.getPosX());
        board.getCurrentPlayer().getSelectedPiece().getCoordinate().setPosY(coordinate.getPosY());


        System.out.println(String.format("User placed piece at x %d, y %d",
                coordinate.getPosX(), coordinate.getPosY()));

        // Re-add modified piece
        board.getPieceList().add(board.getCurrentPlayer().getSelectedPiece());

        // Set current player to another player (another's turn)
        board.setCurrentPlayer(board.getCurrentPlayer().getPlayerName().equals(
                board.getCommunismPlayer().getPlayerName()) ?
                board.getCapitalismPlayer() : board.getCommunismPlayer()
        );

        // Clean up the candidate piece position
        board.getCurrentPlayer().setSelectedPiece(null);

        // Commit to GUI
        homeController.commitPlayerSelection(board.getCurrentPlayer());
    }
}

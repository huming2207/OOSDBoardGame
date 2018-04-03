package controllers;

import helpers.exceptions.DuplicatedPieceException;
import javafx.collections.*;
import models.game.piece.helpers.PieceFactory;
import models.game.player.Player;
import models.gui.BoardButtonEvent;
import models.game.piece.*;


public class GameLogic
{
    private ObservableList<Piece> pieceList = FXCollections.observableArrayList();
    private HomeController homeController;
    private Player communismPlayer;
    private Player capitalismPlayer;
    private Player currentPlayer;

    public GameLogic(HomeController homeController)
    {
        // Assign home controller
        this.homeController = homeController;

        // Add change listener
        pieceList.addListener((ListChangeListener<Piece>) change -> {
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
        pieceList.addAll(PieceFactory.createRandomPieceList());

        // Player init (name written directly in code for now, change later on Assignment 2)
        this.communismPlayer = new Player("Player A");
        this.capitalismPlayer = new Player("Player B");

        // First turn: communism player (player A)
        this.currentPlayer = this.communismPlayer;
    }

    /**
     * Commit changes to game map
     * @param buttonEvent Supplied click result information
     */
    public void commitMapChanges(BoardButtonEvent buttonEvent) throws DuplicatedPieceException
    {
        int posX = buttonEvent.getPosX();
        int posY = buttonEvent.getPosY();

        // Firstly, try find the piece
        Piece pieceInList = getPieceFromList(posX, posY);

        // Case 1: if it's null and the selected piece is null, then the user must have selected an empty piece,
        //         simply ignore and terminate
        if(pieceInList == null && this.currentPlayer.getSelectedPiece() == null) {
            return;
        }

        // Case 2: if selectedPiece is null but piece found in list is not null, then the user must
        //         selecting a valid piece, simply passing it to selectedPiece.
        if(pieceInList != null && this.currentPlayer.getSelectedPiece() == null) {
            this.currentPlayer.setSelectedPiece(pieceInList);
            homeController.commitPlayerSelection(this.currentPlayer);
            this.pieceList.remove(pieceInList);

            System.out.println(String.format("User selected piece at x %d, y %d", posX, posY));
            return;
        }

        // Case 3: if selectedPiece is not null and piece found in list is null, then user is placing a valid piece
        if(pieceInList == null && this.currentPlayer.getSelectedPiece() != null ) {

            int index = this.pieceList.indexOf(this.currentPlayer.getSelectedPiece());

            this.currentPlayer.getSelectedPiece().getCoordinate().setPosX(posX);
            this.currentPlayer.getSelectedPiece().getCoordinate().setPosY(posY);


            System.out.println(String.format("User placed piece at x %d, y %d", posX, posY));

            // Re-add modified piece
            this.pieceList.add(this.currentPlayer.getSelectedPiece());

            // Set current player to another player (another's turn)
            this.currentPlayer = this.currentPlayer.getPlayerName().equals(this.communismPlayer.getPlayerName()) ?
                    this.capitalismPlayer : this.communismPlayer;

            // Commit to GUI
            this.currentPlayer.setSelectedPiece(null);
            homeController.commitPlayerSelection(this.currentPlayer);


            return;
        }

        // Case 4: if selectedPiece is not null and the piece found in list is also not null, then it means the user
        //         is placing a damn piece on a coordinate where it already filled by another piece.
        //         Raise an exception here instead (no needS to evaluate any more)
        throw new DuplicatedPieceException(
                String.format("User putting pieces on a wrong place, x: %d, y: %d", posX, posY));
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
        for(Piece piece : this.pieceList) {
            if(piece.getCoordinate().getPosX() == posX && piece.getCoordinate().getPosY() == posY) {
                return piece;
            }
        }

        return null;
    }
}

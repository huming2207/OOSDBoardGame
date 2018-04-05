package controllers;

import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import javafx.collections.*;
import models.game.Coordinate;
import helpers.PieceFactory;
import models.game.piece.type.RoleType;
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
        this.communismPlayer = new Player("Communism", RoleType.COMMUNISM_PIECE);
        this.capitalismPlayer = new Player("Capitalism", RoleType.CAPITALISM_PIECE);

        // First turn: communism player (player A)
        this.currentPlayer = this.communismPlayer;
        homeController.commitPlayerSelection(this.currentPlayer);
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
        if(pieceInList == null && this.currentPlayer.getSelectedPiece() == null) {
            return;
        }

        // Case 2: if selectedPiece is null but piece found in list is not null, then the user must
        //         selecting a valid piece, simply passing it to selectedPiece.
        if(pieceInList != null && this.currentPlayer.getSelectedPiece() == null) {
            selectPiece(pieceInList);
            return;
        }

        // Case 3: if selectedPiece is not null and piece found in list is null, then user is placing a valid piece
        if(pieceInList == null && this.currentPlayer.getSelectedPiece() != null ) {
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
        for(Piece piece : this.pieceList) {
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
        if(piece.getRoleType() != this.currentPlayer.getRoleType()) {
            throw new InvalidPieceSelectionException("Wrong piece selected, check your role please.");
        } else {
            this.currentPlayer.setSelectedPiece(piece);
        }

        homeController.commitPlayerSelection(this.currentPlayer);
        this.pieceList.remove(piece);

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
        this.currentPlayer.getSelectedPiece().getCoordinate().setPosX(coordinate.getPosX());
        this.currentPlayer.getSelectedPiece().getCoordinate().setPosY(coordinate.getPosY());


        System.out.println(String.format("User placed piece at x %d, y %d",
                coordinate.getPosX(), coordinate.getPosY()));

        // Re-add modified piece
        this.pieceList.add(this.currentPlayer.getSelectedPiece());

        // Set current player to another player (another's turn)
        this.currentPlayer = this.currentPlayer.getPlayerName().equals(this.communismPlayer.getPlayerName()) ?
                this.capitalismPlayer : this.communismPlayer;

        // Clean up the candidate piece position
        this.currentPlayer.setSelectedPiece(null);

        // Commit to GUI
        homeController.commitPlayerSelection(this.currentPlayer);
    }
}

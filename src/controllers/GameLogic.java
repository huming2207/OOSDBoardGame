package controllers;

import helpers.exceptions.DuplicatedPieceException;
import javafx.collections.*;
import models.game.piece.helpers.PieceFactory;
import models.gui.BoardButtonEvent;
import models.game.piece.*;


public class GameLogic implements ListChangeListener<Piece>
{
    private ObservableList<Piece> pieceList = FXCollections.observableArrayList();
    private HomeController homeController;
    private Piece selectedPiece;

    public GameLogic(HomeController homeController)
    {
        // Assign home controller
        this.homeController = homeController;

        // Add change listener
        pieceList.addListener(this);

        // Add all from random piece factory
        pieceList.addAll(PieceFactory.createRandomPieceList());
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
        if(pieceInList == null && this.selectedPiece == null) {
            return;
        }

        // Case 2: if selectedPiece is null but piece found in list is not null, then the user must have
        //         selecting a valid piece, simply passing it to selectedPiece.
        if(pieceInList != null && this.selectedPiece == null) {
            this.selectedPiece = pieceInList;
            this.pieceList.remove(pieceInList);
            homeController.commitPieceSelection(this.selectedPiece.getStyle());
            System.out.println(String.format("User selected piece at x %d, y %d", posX, posY));
            return;
        }

        // Case 3: if selectedPiece is not null and piece found in list is null, then user is placing a valid piece
        if(pieceInList == null && this.selectedPiece != null ) {

            int index = this.pieceList.indexOf(this.selectedPiece);
            this.selectedPiece.getCoordinate().setPosX(posX);
            this.selectedPiece.getCoordinate().setPosY(posY);
            homeController.commitPieceSelection("");
            System.out.println(String.format("User placed piece at x %d, y %d", posX, posY));

            // Replace modified
            this.pieceList.add(this.selectedPiece);
            this.selectedPiece = null;
            return;
        }

        // Case 4: if selectedPiece is not null and the piece found in list is also not null, then it means the user
        //         is placing a damn piece on a coordinate where it already filled by another piece.
        //         Raise an exception here instead (no needS to evaluate any more)
        throw new DuplicatedPieceException(
                String.format("User putting pieces on a wrong place, x: %d, y: %d", posX, posY));
    }




    /**
     * Trigger automatically when piece list is changed.
     * @param change items changed
     */
    @Override
    public void onChanged(Change<? extends Piece> change)
    {
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

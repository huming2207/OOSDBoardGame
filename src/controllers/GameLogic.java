package controllers;

import com.google.java.contract.Requires;
import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import javafx.scene.control.Alert;
import models.coordinate.Coordinate;
import models.board.Board;
import models.coordinate.BoardCellCoordinate;
import models.piece.Piece;
import models.piece.PiecePrototype;


public class GameLogic
{
    private HomeController homeController;
    private Board board;

    public GameLogic(HomeController homeController)
    {
        // Assign home controller
        this.homeController = homeController;
        
        // Initialise board model
        this.board = new Board(this,"Communism", "Capitalism");

        // Initialise piece generator
        PiecePrototype piecePrototype = new PiecePrototype(this.homeController.getBoardSize());
        piecePrototype.generateNewPieces();

        // Add all from random piece factory
        board.getPieceList().addAll(piecePrototype.getPieceList());

        // First turn: communism player (player A)
        board.setCurrentPlayer(board.getCommunismPlayer());
        homeController.commitPlayerSelection(board.getCurrentPlayer());
    }

    /**
     * Commit changes to game map
     * @param buttonEvent Supplied click result information
     */
    @Requires({"buttonEvent.getPosX() > 0", "buttonEvent.getPosY() > 0"})
    protected void commitMapChanges(BoardCellCoordinate buttonEvent)
            throws DuplicatedPieceException, InvalidPieceSelectionException
    {
        if(buttonEvent == null) return;

        Coordinate coordinate = new Coordinate(
                buttonEvent.getPosX(),
                buttonEvent.getPosY());

        // Firstly, try find the piece
        Piece pieceInList = board.getPieceFromList(coordinate.getPosX(), coordinate.getPosY());
        Piece selectedPiece = board.getCurrentPlayer().getSelectedPiece();

        // Possible error 1: if selected piece and the piece found in list are both null, then
        //                   just forget about it lol...
        if(pieceInList == null && selectedPiece == null) return;

        // Possible error 2: if selectedPiece is not null and the piece found in list is also not null,
        //                   then it means the user is placing a damn piece on a coordinate where it already
        //                   filled by another piece.
        //                   Raise an exception here instead (no need to evaluate any more)
        if(pieceInList != null && selectedPiece != null) {
            throw new DuplicatedPieceException(
                    String.format("User putting pieces on a wrong place, x: %d, y: %d",
                            coordinate.getPosX(), coordinate.getPosY()));
        }

        // If selected piece is null, then the user still haven't select a piece yet, go select one instead
        // If not, then go place a piece.
        if(selectedPiece == null) {
            selectPiece(pieceInList);
        } else {
            placePiece(coordinate);
        }


    }

    /**
     * Trigger by Board model
     * @param piece Piece selected
     */
    @Requires({"piece != null", "piece.getCoordinate().getPosX() > 0", "piece.getCoordinate().getPosY() > 0"})
    public void timeout(Piece piece)
    {
        // Put back to original place when timeout
        placePiece(piece.getCoordinate());

        // Show timeout alert
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "5 seconds holding timeout! Try again in the next turn!");
        alert.show();

        board.stopCountdown();
    }

    /**
     * Get the current GUI controller
     * @return GUI controller
     */
    public HomeController getGuiController()
    {
        return this.homeController;
    }


    /**
     * Try select a piece to candidate position
     * @param piece Piece to select
     * @throws InvalidPieceSelectionException Occurs when user selecting a wrong piece which is not in the same role
     */
    @Requires({"piece != null", "piece.getCoordinate().getPosX() > 0", "piece.getCoordinate().getPosY() > 0",
            "!board.getPieceList().isEmpty()"})
    private void selectPiece(Piece piece) throws InvalidPieceSelectionException
    {
        // Check their role type first...
        if(piece.getRoleType() != board.getCurrentPlayer().getRoleType()) {
            throw new InvalidPieceSelectionException("Wrong piece selected, check your role please.");
        } else {
            board.beginCountdown(piece);
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
    @Requires({"coordinate.getPosX() > 0", "coordinate.getPosY() > 0"})
    private void placePiece(Coordinate coordinate)
    {
        // Stop timer
        board.stopCountdown();

        // Set the new coordinate for the piece
        board.getCurrentPlayer().getSelectedPiece().setCoordinate(coordinate);

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

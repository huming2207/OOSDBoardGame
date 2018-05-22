package controllers.logic;

import com.google.java.contract.Requires;
import controllers.HomeController;
import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import helpers.reactions.Reaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import javafx.collections.ListChangeListener;
import models.coordinate.Coordinate;
import models.board.Board;
import models.coordinate.BoardCellCoordinate;
import models.misc.GenericSettings;
import models.piece.Piece;
import models.piece.PiecePrototype;

/**
 * Game logic controller
 *
 * Provides some low-level piece moves rules
 *
 * @author Ming Hu
 * @since Assignment 1
 */
public class GameLogic implements ListChangeListener<Piece>
{
    private HomeController homeController;
    private StatusManager statusManager;
    private CompeteManager competeManager;
    private Board board;
    private Reaction reaction;

    public GameLogic(HomeController homeController)
    {
        // Assign home controller
        this.homeController = homeController;
        
        // Initialize board model
        this.board = new Board(this,
                homeController.getSettings().getCommunismPlayerName(),
                homeController.getSettings().getCapitalismPlayerName());

        // Initialize piece generator
        PiecePrototype piecePrototype = new PiecePrototype(
                homeController.getBoardSize(),
                homeController.getSettings().getPieceCount());

        // Initialize status manager
        this.statusManager = new StatusManager(this);

        // Add all from random piece factory
        this.board.getPieceList().addAll(piecePrototype.getPieceList());

        // First turn: communism player (player A)
        this.board.setCurrentPlayer(this.board.getCommunismPlayer());
        homeController.commitPlayerSelection(this.board.getCurrentPlayer());

        this.reaction = ReactionManager.getReaction();
        this.competeManager = new CompeteManager(this);
    }

    /**
     * Commit changes to game map
     * @param buttonEvent Supplied click result information
     */
    @Requires({"buttonEvent.getPosX() > 0", "buttonEvent.getPosY() > 0"})
    public void commitMapChanges(BoardCellCoordinate buttonEvent)
            throws DuplicatedPieceException, InvalidPieceSelectionException
    {
        if(buttonEvent == null) return;

        Coordinate coordinate = new Coordinate(
                buttonEvent.getPosX(),
                buttonEvent.getPosY());

        // Firstly, try find the piece
        Piece pieceInList = this.board.getPieceFromList(coordinate.getPosX(), coordinate.getPosY());
        Piece selectedPiece = this.board.getCurrentPlayer().getSelectedPiece();

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
            this.selectPiece(pieceInList);
        } else {
            this.placePiece(coordinate, true);
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
        placePiece(piece.getCoordinate(), false);

        // Show timeout alert
        this.reaction.handleReaction(ReactionLevel.WARN, "5-second Timeout! Switching to next turn!");

        this.board.stopCountdown();
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
        if(piece.getRoleType() != this.board.getCurrentPlayer().getRoleType()) {
            throw new InvalidPieceSelectionException("Wrong piece selected, check your role please.");
        } else {
            this.statusManager.recordStatus(this.board);
            this.board.beginCountdown(piece);
            this.board.getCurrentPlayer().setSelectedPiece(piece);
        }

        this.homeController.commitPlayerSelection(this.board.getCurrentPlayer());
        this.board.getPieceList().remove(piece);

        this.reaction.handleReaction(ReactionLevel.DEBUG, String.format("User selected piece at x %d, y %d",
                piece.getCoordinate().getPosX(), piece.getCoordinate().getPosY()));
    }

    /**
     * Place a candidate piece to a certain position
     * @param coordinate New coordinate for the candidate piece
     */
    @Requires({"coordinate.getPosX() > 0", "coordinate.getPosY() > 0"})
    private void placePiece(Coordinate coordinate, boolean applyRules)
    {
        this.reaction.handleReaction(ReactionLevel.DEBUG, String.format("User placed piece at x %d, y %d",
                coordinate.getPosX(), coordinate.getPosY()));

        // Validate if the piece should move to this position
        if(applyRules && !this.competeManager.validateMoveRange(this.board.getCurrentPlayer(), coordinate)) {
            this.reaction.handleReaction(ReactionLevel.WARN,
                    "You are placing the piece out of its single move range");
            return; // ...stop the placement
        }

        // Stop timer
        this.board.stopCountdown();

        // Defensive mode toggle, if enabled, no one gets hurt. Otherwise it will perform attack
        if(!this.board.isDefensiveMode()) {
            this.competeManager.performPossibleAttack(this.board.getCurrentPlayer());
        }

        // Set the new coordinate for the piece
        this.board.getCurrentPlayer().getSelectedPiece().setCoordinate(coordinate);

        // Re-add modified piece
        this.board.getPieceList().add(this.board.getCurrentPlayer().getSelectedPiece());

        // Set current player to another player (another's turn)
        this.board.setCurrentPlayer(this.board.getCurrentPlayer().getPlayerName().equals(
                this.board.getCommunismPlayer().getPlayerName()) ?
                this.board.getCapitalismPlayer() : board.getCommunismPlayer()
        );

        // Clean up the candidate piece position
        this.board.getCurrentPlayer().setSelectedPiece(null);

        // Commit to GUI
        this.homeController.commitPlayerSelection(board.getCurrentPlayer());


    }

    public StatusManager getStatusManager()
    {
        return statusManager;
    }

    public void setBoard(Board board)
    {
        this.board = board;
    }

    public Board getBoard()
    {
        return this.board;
    }

    /**
     * This method triggers when any changes is made in the piece list. It will update UI via home controller.
     * @param change Piece changes in the list
     */
    @Override
    public void onChanged(Change<? extends Piece> change)
    {
        while(change.next()) {
            if(change.wasAdded()) {
                for(Piece piece : change.getAddedSubList()) {
                    this.getGuiController().commitUIChanges(piece.getCoordinate(), piece.getStyle());
                }
            }

            if(change.wasRemoved()) {
                for(Piece piece : change.getRemoved()) {
                    // Put an empty string to the style (may need to set a default button style later on)
                    this.getGuiController().commitUIChanges(piece.getCoordinate(), null);
                }
            }
        }
    }
}

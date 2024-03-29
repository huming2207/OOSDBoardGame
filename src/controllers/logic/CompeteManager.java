package controllers.logic;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import helpers.reactions.Reaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Compete logic manager, for calculating move/attack range offsets
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public class CompeteManager
{
    private GameLogic gameLogic;
    private Reaction reaction;

    public CompeteManager(GameLogic gameLogic)
    {
        this.gameLogic = gameLogic;
        this.reaction = ReactionManager.getReaction();
    }

    /**
     * Validate move range, if not valid then a false value will return
     * @param currentPlayer current player instance
     * @param newCoordinate new coordinate
     * @return true if it can move
     */
    protected boolean validateMoveRange(Player currentPlayer, Coordinate newCoordinate)
    {
        List<Coordinate> moveRangeCoordinates =
                this.generateTargetCoordinates(currentPlayer, currentPlayer.getSelectedPiece().getMoveRangeOffset());

        for(Coordinate moveRangeCoordinate : moveRangeCoordinates) {
            if(moveRangeCoordinate.equals(newCoordinate)) return true;
        }

        return false;
    }

    /**
     * Perform attack if possible, and return the reference of the piece that HAS BEEN ATTACKED BY OTHER PIECE
     * @param currentPlayer Current player who perform the attack
     * @return the piece that HAS BEEN ATTACKED BY OTHER PIECE
     */
    @Ensures("result != null")
    protected Piece performPossibleAttack(Player currentPlayer)
    {
        // Generate dead zone
        List<Coordinate> deadzoneCoordinates =
                this.generateTargetCoordinates(currentPlayer, currentPlayer.getSelectedPiece().getAttackZoneOffset());

        // Query other piece in the board piece list, with each dead-zone coordinate
        for(Piece piece : this.gameLogic.getBoard().getPieceList()) {
            for(Coordinate deathCoordinate : deadzoneCoordinates) {

                if(piece.getCoordinate().equals(deathCoordinate)
                        && piece.getRoleType() != currentPlayer.getSelectedPiece().getRoleType()) {

                    // Perform attack
                    // Oops, it hurts...
                    piece.sufferAttack(currentPlayer.getSelectedPiece().getAttackLevel());

                    // Log it down
                    this.reaction.handleReaction(
                            ReactionLevel.WARN, "Oops, you hurt " +
                                    piece.getCharacterType().toString());

                    return piece;
                }
            }
        }

        return null;
    }

    @Requires("offsets.length > 0")
    @Ensures("!result.isEmpty()")
    private List<Coordinate> generateTargetCoordinates(Player currentPlayer, int[][] offsets)
    {
        // Generate dead zone
        List<Coordinate> targetCoordinates = new ArrayList<>();

        // Get current piece's coordinate value
        int currentPiecePosX = currentPlayer.getSelectedPiece().getCoordinate().getPosX();
        int currentPiecePosY = currentPlayer.getSelectedPiece().getCoordinate().getPosY();

        for(int[] rawCoordinateArray : offsets) {
            targetCoordinates.add(new Coordinate(
                    currentPiecePosX + rawCoordinateArray[0],
                    currentPiecePosY + rawCoordinateArray[1]));
        }

        return targetCoordinates;
    }
}

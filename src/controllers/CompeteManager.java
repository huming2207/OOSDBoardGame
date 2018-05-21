package controllers;

import helpers.reactions.Reaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CompeteManager
{
    private GameLogic gameLogic;
    private Reaction reaction;

    public CompeteManager(GameLogic gameLogic)
    {
        this.gameLogic = gameLogic;
        this.reaction = ReactionManager.getReaction();
    }

    public boolean validateMoveRange(Player currentPlayer, Coordinate newCoordinate)
    {
        List<Coordinate> moveRangeCoordinates =
                this.generateTargetCoordinates(currentPlayer, currentPlayer.getSelectedPiece().getMoveRangeOffset());

        for(Coordinate moveRangeCoordinate : moveRangeCoordinates) {
            if(moveRangeCoordinate.equals(newCoordinate)) return true;
        }

        return false;
    }

    public void performPossibleAttack(Player currentPlayer)
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
                    piece.sufferAttack(currentPlayer.getSelectedPiece().getAttackLevel()); // Oops, it hurts...

                    // Log it down
                    this.reaction.handleReaction(
                            ReactionLevel.WARN, "Oops, you hurt " +
                                    currentPlayer.getSelectedPiece().getCharacterType().toString());
                }
            }
        }
    }

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

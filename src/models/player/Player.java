package models.player;

import models.piece.Piece;
import models.piece.type.RoleType;

/**
 * Player interface
 *
 * @author Ming Hu
 * @since Assignment 1
 */
public interface Player
{
    /**
     * Get selected piece
     * @return selected piece
     */
    Piece getSelectedPiece();

    /**
     * Set selected piece
     * @param selectedPiece piece picked by user
     */
    void setSelectedPiece(Piece selectedPiece);

    /**
     * Get player name
     * @return player name in String
     */
    String getPlayerName();

    /**
     * Set player name
     * @param playerName player name in string
     */
    void setPlayerName(String playerName);

    /**
     * Get role type
     * @return role type
     */
    RoleType getRoleType();

    /**
     * Get current score of a player
     * @return current score
     */
    int getScore();

    /**
     * Set current score of a player
     * @param score new score
     */
    void setScore(int score);
}

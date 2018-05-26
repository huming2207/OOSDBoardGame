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
    Piece getSelectedPiece();
    void setSelectedPiece(Piece selectedPiece);
    String getPlayerName();
    void setPlayerName(String playerName);
    RoleType getRoleType();
    int getScore();
    void setScore(int score);
}

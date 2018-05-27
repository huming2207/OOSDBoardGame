package models.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import models.piece.Piece;
import models.piece.type.RoleType;

import java.io.Serializable;

/**
 * Communism player (for Abstract Factory to generate)
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant("score >= 0")
public class CommunismPlayer implements Player,Serializable
{
    private String playerName;
    private Piece selectedPiece;
    private int score = 0;

    /**
     * Initialize a player with player name and type
     * @param playerName Player name, e.g. "capitalism player"
     */
    @Requires({"!playerName.isEmpty()"})
    public CommunismPlayer(String playerName)
    {
        this.playerName = playerName;
    }

    @Ensures("result != null")
    public Piece getSelectedPiece()
    {
        return selectedPiece;
    }

    @Requires("selectedPiece != null")
    public void setSelectedPiece(Piece selectedPiece)
    {
        this.selectedPiece = selectedPiece;
    }

    @Ensures("!result.isEmpty()")
    public String getPlayerName()
    {
        return playerName;
    }

    @Requires("!playerName.isEmpty()")
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public RoleType getRoleType()
    {
        return RoleType.COMMUNISM_PIECE;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}

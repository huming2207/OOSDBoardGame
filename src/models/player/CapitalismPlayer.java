package models.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import models.piece.Piece;
import models.piece.type.RoleType;

import java.io.Serializable;

/**
 * Capitalism player (for Abstract Factory to generate)
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant("score >= 0")
public class CapitalismPlayer implements Player, Serializable
{
    private String playerName;
    private Piece selectedPiece;
    private int score;


    /**
     * Initialize a player with player name and type
     * @param playerName Player name, e.g. "capitalism player"
     */
    @Requires({"!playerName.isEmpty()"})
    public CapitalismPlayer(String playerName)
    {
        this.playerName = playerName;
    }


    @Ensures("result != null")
    public Piece getSelectedPiece()
    {
        return selectedPiece;
    }

    @Requires("!playerName.isEmpty()")
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
        return RoleType.CAPITALISM_PIECE;
    }

    @Ensures("result > 0")
    public int getScore()
    {
        return score;
    }

    @Requires("score > 0")
    public void setScore(int score)
    {
        this.score = score;
    }
}

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


    /**
     * Get selected piece
     * @return selected piece
     */
    @Ensures("result != null")
    public Piece getSelectedPiece()
    {
        return selectedPiece;
    }

    /**
     * Set selected piece
     * @param selectedPiece piece picked by user
     */
    @Requires("!playerName.isEmpty()")
    public void setSelectedPiece(Piece selectedPiece)
    {
        this.selectedPiece = selectedPiece;
    }

    /**
     * Get player name
     * @return player name in String
     */
    @Ensures("!result.isEmpty()")
    public String getPlayerName()
    {
        return playerName;
    }

    /**
     * Set player name
     * @param playerName player name in string
     */
    @Requires("!playerName.isEmpty()")
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    /**
     * Get role type
     * @return role type
     */
    public RoleType getRoleType()
    {
        return RoleType.CAPITALISM_PIECE;
    }

    /**
     * Get current score of a player
     * @return current score
     */
    @Ensures("result > 0")
    public int getScore()
    {
        return score;
    }

    /**
     * Set current score of a player
     * @param score new score
     */
    @Requires("score > 0")
    public void setScore(int score)
    {
        this.score = score;
    }
}

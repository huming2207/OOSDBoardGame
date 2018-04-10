package models.game.player;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import models.game.piece.Piece;
import models.game.piece.type.RoleType;

public class Player
{
    private String playerName;
    private RoleType allowedType;
    private Piece selectedPiece;

    /**
     * Initialise a player with player name and type
     * @param playerName Player name, e.g. "capitalism player"
     * @param allowedType Role type, e.g. RoleType.COMMUNISM_PLAYER
     */
    @Requires({"!playerName.isEmpty()", "allowedType != null"})
    public Player(String playerName, RoleType allowedType)
    {
        this.playerName = playerName;
        this.allowedType = allowedType;
    }

    /**
     * Get selected piece
     * @return selected piece
     */
    public Piece getSelectedPiece()
    {
        return selectedPiece;
    }

    /**
     * Set selected piece
     * @param selectedPiece piece picked by user
     */
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
    @Ensures("result != null")
    public RoleType getRoleType()
    {
        return allowedType;
    }

    /**
     * Set role type
     * @param allowedType role type
     */
    @Requires("allowedType != null")
    public void setRoleType(RoleType allowedType)
    {
        this.allowedType = allowedType;
    }
}

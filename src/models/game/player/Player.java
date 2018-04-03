package models.game.player;

import models.game.piece.Piece;
import models.game.piece.type.RoleType;

public class Player
{
    private String playerName;
    private RoleType allowedType;
    private Piece selectedPiece;

    public Player(String playerName, RoleType allowedType)
    {
        this.playerName = playerName;
        this.allowedType = allowedType;
    }

    public Piece getSelectedPiece()
    {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece)
    {
        this.selectedPiece = selectedPiece;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public RoleType getRoleType()
    {
        return allowedType;
    }

    public void setRoleType(RoleType allowedType)
    {
        this.allowedType = allowedType;
    }
}

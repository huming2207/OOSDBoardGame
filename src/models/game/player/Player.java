package models.game.player;

import models.game.Coordinate;
import models.game.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Player
{
    private String playerName;
    private Map<Coordinate, Piece> pieceMap;

    public Player(String playerName)
    {
        this.playerName = playerName;
        this.pieceMap = new HashMap<>();
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public Map<Coordinate, Piece> getPieceMap()
    {
        return pieceMap;
    }

    public void setPieceMap(Map<Coordinate, Piece> pieceMap)
    {
        this.pieceMap = pieceMap;
    }
}

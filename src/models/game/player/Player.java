package models.game.player;

import models.game.Coordinate;
import models.game.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player
{
    private String playerName;
    private ArrayList<Piece> pieceList;

    private Piece selectedPiece;

    public Player(String playerName)
    {
        this.playerName = playerName;
        this.pieceList = new ArrayList<>();
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

    public ArrayList<Piece> getPieceList()
    {
        return this.pieceList;
    }

    public void setPieceMap(ArrayList<Piece> pieceList)
    {
        this.pieceList = pieceList;
    }
}

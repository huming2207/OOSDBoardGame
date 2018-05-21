package models.misc;

import java.io.Serializable;

public class GenericSettings implements Serializable
{
    private int boardSize;
    private int pieceCount;
    private String communismPlayerName;
    private String capitalismPlayerName;

    public GenericSettings()
    {
        this.boardSize = 10;
        this.pieceCount = 6;
        this.communismPlayerName = "Communism";
        this.capitalismPlayerName = "Capitalism";
    }

    public GenericSettings(int boardSize, int pieceCount, String capitalismPlayerName, String communismPlayerName)
    {
        this.boardSize = boardSize;
        this.pieceCount = pieceCount;
        this.capitalismPlayerName = capitalismPlayerName;
        this.communismPlayerName = communismPlayerName;
    }

    public int getBoardSize()
    {
        return this.boardSize;
    }

    public void setBoardSize(int boardSize)
    {
        this.boardSize = boardSize;
    }

    public int getPieceCount()
    {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount)
    {
        this.pieceCount = pieceCount;
    }

    public String getCommunismPlayerName()
    {
        return communismPlayerName;
    }

    public void setCommunismPlayerName(String communismPlayerName)
    {
        this.communismPlayerName = communismPlayerName;
    }

    public String getCapitalismPlayerName()
    {
        return capitalismPlayerName;
    }

    public void setCapitalismPlayerName(String capitalismPlayerName)
    {
        this.capitalismPlayerName = capitalismPlayerName;
    }
}

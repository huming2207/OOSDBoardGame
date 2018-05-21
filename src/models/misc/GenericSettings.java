package models.misc;

import java.io.Serializable;

public class GenericSettings implements Serializable
{
    private int boardSize;
    private String communismPlayerName;
    private String capitalismPlayerName;

    public GenericSettings()
    {
        this.boardSize = 10;
        this.communismPlayerName = "Communism";
        this.capitalismPlayerName = "Capitalism";
    }

    public GenericSettings(int boardSize, String capitalismPlayerName, String communismPlayerName)
    {
        this.boardSize = boardSize;
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

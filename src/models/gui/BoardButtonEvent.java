package models.gui;

public class BoardButtonEvent
{
    private int posX;
    private int posY;
    private String id;

    public BoardButtonEvent(int posX, int posY, String id)
    {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
    }


    public int getPosX()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}

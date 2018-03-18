package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BoardButtonResult
{
    private IntegerProperty posX = new SimpleIntegerProperty();
    private IntegerProperty posY = new SimpleIntegerProperty();

    public BoardButtonResult(IntegerProperty posX, IntegerProperty posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public BoardButtonResult(int posX, int posY)
    {
        this.posX.set(posX);
        this.posY.set(posY);
    }

    public int getPosX()
    {
        return posX.get();
    }

    public IntegerProperty posXProperty()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX.set(posX);
    }

    public int getPosY()
    {
        return posY.get();
    }

    public IntegerProperty posYProperty()
    {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY.set(posY);
    }
}

package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.management.BufferPoolMXBean;

public class BoardButtonResult
{
    private IntegerProperty posX = new SimpleIntegerProperty();
    private IntegerProperty posY = new SimpleIntegerProperty();
    private StringProperty  id = new SimpleStringProperty();

    public BoardButtonResult(IntegerProperty posX, IntegerProperty posY, StringProperty id)
    {
        this.posX = posX;
        this.posY = posY;
        this.id = id;
    }

    public BoardButtonResult(int posX, int posY, String id)
    {
        this.posX.set(posX);
        this.posY.set(posY);
        this.id.set(id);
    }


    public String getId()
    {
        return id.get();
    }

    public StringProperty idProperty()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id.set(id);
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

    @Override
    public boolean equals(Object o)
    {
        if(o == null) return false;

        BoardButtonResult otherResult = (BoardButtonResult)o;
        return this.posX.get() == otherResult.posX.get() && this.posY.get() == otherResult.posY.get();
    }

    @Override
    public int hashCode()
    {
        return this.posX.get() * 100 + this.posX.get();
    }
}

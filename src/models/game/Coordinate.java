package models.game;

import java.util.Objects;

public class Coordinate
{
    private int posX;
    private int posY;

    public Coordinate(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX()
    {
        return posX;
    }

    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void setPosY(int posY)
    {
        this.posY = posY;
    }


    @Override
    public boolean equals(Object o)
    {
        // Self check
        if(this == o) return true;

        // Null check
        if(o == null) return false;

        // Type check
        if(o.getClass() != this.getClass()) return false;

        // Detailed check
        Coordinate coordinate = (Coordinate) o;
        return this.getPosX() == coordinate.getPosX() && this.getPosY() == coordinate.getPosY();
    }

    @Override
    public int hashCode()
    {
        // ...should be auto boxed to Integer object??
        return Objects.hash(this.posX, this.posY);
    }
}

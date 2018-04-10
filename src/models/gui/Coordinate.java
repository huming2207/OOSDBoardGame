package models.gui;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.Objects;

public class Coordinate
{
    private int posX;
    private int posY;

    @Requires({"posX >= 0", "posX <= 7", "posY >= 0", "posY <= 7"})
    public Coordinate(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    @Ensures({"result >= 0", "result <= 7"})
    public int getPosX()
    {
        return posX;
    }

    @Requires({"posX >= 0", "posX <= 7"})
    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    @Ensures({"result >= 0", "result <= 7"})
    public int getPosY()
    {
        return posY;
    }

    @Requires({"posY >= 0", "posY <= 7"})
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

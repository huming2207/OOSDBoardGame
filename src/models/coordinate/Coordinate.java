package models.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.io.Serializable;
import java.util.Objects;

public class Coordinate implements Serializable
{
    private int posX;
    private int posY;

    /**
     * Create a coordinate for a piece
     * @param posX X axis
     * @param posY Y axis
     */
    @Requires({"posX > 0", "posY > 0"})
    public Coordinate(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Protected coordinate constructor to be cloned
     * @param oldCoordinate original coordinate to be cloned...
     */
    @Requires({"oldCoordinate.getPosX() > 0", "oldCoordinate.getPosY() > 0"})
    public Coordinate(Coordinate oldCoordinate)
    {
        this.posX = oldCoordinate.getPosX();
        this.posY = oldCoordinate.getPosY();
    }

    /**
     * Get X axis
     * @return X axis value
     */
    @Ensures({"result > 0"})
    public int getPosX()
    {
        return posX;
    }

    /**
     * Get Y axis
     * @return Y axis value
     */
    @Ensures({"result > 0"})
    public int getPosY()
    {
        return posY;
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

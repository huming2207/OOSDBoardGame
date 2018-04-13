package models.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;

import java.util.Objects;

public class Coordinate
{
    private int posX;
    private int posY;

    /**
     * Create a coordinate for a piece
     * @param posX X axis
     * @param posY Y axis
     */
    @Requires({"posX >= 0", "posX <= 7", "posY >= 0", "posY <= 7"})
    public Coordinate(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Protected coordinate constructor to be cloned
     * @param oldCoordinate original coordinate to be cloned...
     */
    @Requires({"oldCoordinate.getPosX() >= 0", "oldCoordinate.getPosX() <= 7",
            "oldCoordinate.getPosY() >= 0", "oldCoordinate.getPosY() <= 7"})
    protected Coordinate(Coordinate oldCoordinate)
    {
        this.posX = oldCoordinate.getPosX();
        this.posY = oldCoordinate.getPosY();
    }

    /**
     * Get X axis
     * @return X axis value
     */
    @Ensures({"result >= 0", "result <= 7"})
    public int getPosX()
    {
        return posX;
    }

    /**
     * Set X axis
     * @param posX X axis value
     */
    @Requires({"posX >= 0", "posX <= 7"})
    public void setPosX(int posX)
    {
        this.posX = posX;
    }

    /**
     * Get Y axis
     * @return Y axis value
     */
    @Ensures({"result >= 0", "result <= 7"})
    public int getPosY()
    {
        return posY;
    }

    /**
     * Set Y axis
     * @param posY Y axis value
     */
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

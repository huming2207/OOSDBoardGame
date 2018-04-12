package models.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.scene.control.Button;

public class BoardCellCoordinate
{
    private Button button;
    private Coordinate coordinate;

    /**
     * Create a new board cell coordinate
     * @param posX X axis
     * @param posY Y axis
     * @param button Button clicked
     */
    @Requires({"posX >= 0", "posX <= 7", "posY >= 0", "posY <= 7", "button != null"})
    public BoardCellCoordinate(int posX, int posY, Button button)
    {
        this.coordinate = new Coordinate(posX, posY);
        this.button = button;
    }

    /**
     * Get coordinate
     * @return coordinate of this clicked cell
     */
    public Coordinate getCoordinate()
    {
        return this.coordinate;
    }

    /**
     * Set coordinate
     * @param coordinate coordinate of this clicked cell
     */
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    /**
     * Get the clicked button
     * @return Button clicked
     */
    public Button getButton()
    {
        return button;
    }

    /**
     * Set to clicked button
     * @param button Button clicked
     */
    public void setButton(Button button)
    {
        this.button = button;
    }

}


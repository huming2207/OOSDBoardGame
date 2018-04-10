package models.game.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.scene.control.Button;

public class BoardCellCoordinate extends Coordinate
{
    private Button button;

    /**
     * Create a new board cell coordinate
     * @param posX X axis
     * @param posY Y axis
     * @param button Button clicked
     */
    @Requires({"posX >= 0", "posX <= 7", "posY >= 0", "posY <= 7", "button != null"})
    public BoardCellCoordinate(int posX, int posY, Button button)
    {
        super(posX, posY);
        this.button = button;
    }

    /**
     * Get the clicked button
     * @return Button clicked
     */
    @Ensures("result != null")
    public Button getButton()
    {
        return button;
    }

    /**
     * Set to clicked button
     * @param button Button clicked
     */
    @Requires("button != null")
    public void setButton(Button button)
    {
        this.button = button;
    }

}


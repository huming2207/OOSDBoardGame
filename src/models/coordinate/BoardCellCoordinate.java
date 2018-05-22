package models.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.scene.control.Button;

import java.io.Serializable;

public class BoardCellCoordinate implements ICoordinate, Serializable
{
    private Button button;
    private int posX;
    private int posY;

    /**
     * Create a new board cell coordinate
     * @param posX X axis
     * @param posY Y axis
     * @param button Button clicked
     */
    @Requires({"posX > 0", "posY > 0", "button != null"})
    public BoardCellCoordinate(int posX, int posY, Button button)
    {
        this.posX = posX;
        this.posY = posY;
        this.button = button;
    }

    /**
     * Get the clicked button
     * Note: this method is not yet been used due to out of scope for Assignment 1 stage
     *
     * @return Button clicked
     */
    public Button getButton()
    {
        return button;
    }

    /**
     * Set to clicked button
     * Note: this method is not yet been used due to out of scope for Assignment 1 stage
     *
     * @param button Button clicked
     */
    public void setButton(Button button)
    {
        this.button = button;
    }

    /**
     * Get X axis
     * @return X axis value
     */
    @Ensures({"result > 0"})
    public int getPosX()
    {
        return this.posX;
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

}

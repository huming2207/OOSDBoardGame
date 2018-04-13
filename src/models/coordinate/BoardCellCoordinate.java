package models.coordinate;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.scene.control.Button;

public class BoardCellCoordinate implements ICoordinate
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
    @Requires({"posX >= 0", "posX <= 7", "posY >= 0", "posY <= 7", "button != null"})
    public BoardCellCoordinate(int posX, int posY, Button button)
    {
        this.posX = posX;
        this.posY = posY;
        this.button = button;
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

    /**
     * Get X axis
     * @return X axis value
     */
    @Ensures({"result >= 0", "result <= 7"})
    public int getPosX()
    {
        return this.posX;
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

}


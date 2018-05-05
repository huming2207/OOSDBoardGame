package helpers;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import models.coordinate.BoardCellCoordinate;

public class BoardButtonHelper
{
    /**
     * Parse click event to button click result
     * @param actionEvent Action event from a button
     * @return Button result, including x & y axis and button ID string.
     */
    @Requires("actionEvent != null")
    @Ensures({"result.getPosX() >= 0", "result.getPosY() >= 0"})
    public static BoardCellCoordinate parseClickResult(ActionEvent actionEvent)
    {
        if(actionEvent == null) return null;

        Button buttonClicked = (Button)actionEvent.getSource();
        String buttonId = buttonClicked.getId();

        // Button ID includes a prefix, X axis and Y axis, separated by "_" symbol
        // e.g. board_0_1 is a button at (0, 1)
        String[] splitedId = buttonId.split("_");
        int posX = Integer.valueOf(splitedId[1]);
        int posY = Integer.valueOf(splitedId[2]);

        return new BoardCellCoordinate(posX, posY, buttonClicked);
    }
}

package helpers;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import models.gui.BoardButtonEvent;

import javax.xml.bind.annotation.XmlEnum;

public class BoardButtonHelper
{
    /**
     * Parse click event to button click result
     * @param actionEvent Action event from a button
     * @return Button result, including x & y axis and button ID string.
     */
    @Requires("actionEvent != null")
    @Ensures({"result.getPosX() <= 7", "result.getPosX() >= 0", "result.getPosY() <= 7", "result.getPosY() >= 0"})
    public static BoardButtonEvent parseClickResult(ActionEvent actionEvent)
    {
        if(actionEvent == null) return null;

        String buttonId = ((Button)actionEvent.getSource()).getId();
        String[] splitedId = buttonId.split("_");
        int posX = Integer.valueOf(splitedId[1]);
        int posY = Integer.valueOf(splitedId[2]);

        return new BoardButtonEvent(posX, posY, buttonId);
    }
}

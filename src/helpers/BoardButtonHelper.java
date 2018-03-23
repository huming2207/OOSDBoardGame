package helpers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import models.BoardButtonResult;

public class BoardButtonHelper
{
    public static BoardButtonResult parseClickResult(ActionEvent actionEvent)
    {
        if(actionEvent == null) return null;

        String buttonId = ((Button)actionEvent.getSource()).getId();
        String[] splitedId = buttonId.split("_");
        int posX = Integer.valueOf(splitedId[1]);
        int posY = Integer.valueOf(splitedId[2]);

        return new BoardButtonResult(posX, posY, buttonId);
    }
}

package controllers;

import helpers.BoardButtonHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import models.BoardButtonResult;

public class HomeController
{
    @FXML
    private void handleBoardButtonClick(ActionEvent clickEvent)
    {
        BoardButtonResult buttonResult = BoardButtonHelper.parseClickResult(clickEvent);


        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                String.format("Button at row %d col %d clicked!", buttonResult.getPosX(), buttonResult.getPosY()));

        alert.show();
    }
}

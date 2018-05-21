package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AboutController
{
    @FXML
    private void handleCloseAction(ActionEvent event)
    {
        Button closeButton = (Button)event.getSource();
        closeButton.getScene().getWindow().hide();
    }
}

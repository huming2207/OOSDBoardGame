package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * About controller
 * Shows about window and handle the close action
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public class AboutController
{
    @FXML
    private void handleCloseAction(ActionEvent event)
    {
        Button closeButton = (Button)event.getSource();
        closeButton.getScene().getWindow().hide();
    }
}

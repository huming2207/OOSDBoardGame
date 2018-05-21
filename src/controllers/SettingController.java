package controllers;

import helpers.reactions.Reaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.misc.GenericSettings;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SettingController
{
    private int boardSize = 8;

    @FXML
    private Slider boardSizeSlider;

    @FXML
    private TextField capitalismPlayerName;

    @FXML
    private TextField communismPlayerName;

    @FXML
    private Button saveButton;

    @FXML
    private Label boardSizeIndicator;

    private Reaction reaction;

    @FXML
    private void initialize()
    {
        // "Single-way binding" from the slider to board size
        this.boardSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.boardSizeIndicator.setText(Integer.toString(newValue.intValue()));
            this.boardSize = newValue.intValue();
        });

        this.reaction = ReactionManager.getReaction();
    }

    @FXML
    private void handleSaveRequest(ActionEvent event)
    {
        GenericSettings settings = new GenericSettings(
                this.boardSize,
                this.capitalismPlayerName.getText(),
                this.communismPlayerName.getText());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("settings.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(settings);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            this.reaction.handleReaction(ReactionLevel.CRASH, "Cannot save the file!");
            e.printStackTrace();
        }

        // Do not use alert.show() here as it will not block the thread and let the app exit without notice
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Status saved, please restart the app");
        alert.showAndWait();

        System.exit(0);
    }
}

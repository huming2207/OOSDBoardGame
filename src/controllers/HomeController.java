package controllers;

import helpers.BoardButtonHelper;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import models.BoardButtonResult;
import models.game.Coordinate;
import models.game.piece.Piece;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HomeController
{
    private GameLogic gameLogic;
    private Map<String, Object> controlMap;

    public HomeController()
    {
        this.gameLogic = new GameLogic(this);
    }

    @FXML
    private void initialize()
    {

    }

    /**
     * Set the FXML Control map from the main class (i.e. from loader.getNamespace())
     *
     * @param controlMap Map source to be set
     */
    public void setControlMap(Map<String, Object> controlMap)
    {
        this.controlMap = controlMap;
    }

    /**
     * Handle all board button click event
     *
     * @param clickEvent event input
     */
    @FXML
    private void handleBoardButtonClick(ActionEvent clickEvent)
    {
        BoardButtonResult buttonResult = BoardButtonHelper.parseClickResult(clickEvent);


        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                String.format("Button at row %d col %d clicked!", buttonResult.getPosX(), buttonResult.getPosY()));

        alert.show();

        gameLogic.commitMapChanges(buttonResult);
    }

    /**
     * Update the UI from game logic
     *  - Query the controlMap to get the button, if null, raise an Alert dialog
     *  - Apply style when a valid button
     *
     * @param coordinate Coordinate of a button
     * @param style CSS style
     */
    public void commitUIChanges(Coordinate coordinate, String style)
    {
        // The ID format is "button_posX_posY"
        Button button = (Button)controlMap.get(String.format("board_%d_%d",
                coordinate.getPosX(), coordinate.getPosY()));

        // In case the game algorithm goes wrong...
        // Btw style string does not need to check since it is constant in the model.
        if(button == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Operation out of range!");
            alert.showAndWait();
            return;
        }

        button.setStyle(style);
    }

}

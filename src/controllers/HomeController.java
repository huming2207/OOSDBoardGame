package controllers;

import com.google.java.contract.Requires;
import helpers.BoardButtonHelper;
import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.player.Player;
import models.coordinate.BoardCellCoordinate;
import models.coordinate.Coordinate;

import java.util.Map;

public class HomeController
{
    private GameLogic gameLogic;
    private Map<String, Object> controlMap;

    @FXML
    private Button selectedPiece;

    @FXML
    private Label playerNameLabel;

    /**
     * Set the FXML Control map from the main class (i.e. from loader.getNamespace()) and initialise game logic
     *
     * @param controlMap Map source to be set
     */
    public void gameInit(Map<String, Object> controlMap)
    {
        this.controlMap = controlMap;
        this.gameLogic = new GameLogic(this);
    }

    /**
     * Handle all board button click event
     *
     * @param clickEvent event input
     */
    @FXML
    private void handleBoardButtonClick(ActionEvent clickEvent)
    {
        BoardCellCoordinate buttonResult = BoardButtonHelper.parseClickResult(clickEvent);

        try {
            gameLogic.commitMapChanges(buttonResult);
        } catch (DuplicatedPieceException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You're putting the piece on a non-empty place!");
            alert.show();
        } catch (InvalidPieceSelectionException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You're selecting a piece in the wrong role!");
            alert.show();
        }
    }

    /**
     * Update the UI from game logic
     *  - Query the controlMap to get the button, if null, raise an Alert dialog
     *  - Apply style when a valid button
     *
     * @param coordinate Coordinate of a button
     * @param style CSS style
     */
    @Requires({"coordinate.getPosX() <= 7", "coordinate.getPosX() >= 0",
            "coordinate.getPosY() <= 7", "coordinate.getPosX() >= 0"})
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

    /**
     * Set the selected piece to selectPiece button to tell user they've selected a piece in GUI
     *
     * PS: here using cofoja for null checking is useful,
     *      just in case if someone use this method when currentPlayer is not initialised.
     * @param player Committed player
     *
     */
    @Requires("player != null")
    public void commitPlayerSelection(Player player)
    {
        // Set the style of selectPiece button. If piece is null, then set a empty style.
        if(player.getSelectedPiece() != null) {
            this.selectedPiece.setStyle(player.getSelectedPiece().getStyle());
        } else {
            this.selectedPiece.setStyle("");
        }

        // Set the player name
        this.playerNameLabel.setText(player.getPlayerName());
    }

}

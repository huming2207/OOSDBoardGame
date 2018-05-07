package controllers;

import com.google.java.contract.Requires;
import helpers.BoardButtonHelper;
import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import models.player.Player;
import models.coordinate.BoardCellCoordinate;
import models.coordinate.Coordinate;

import java.util.Map;

public class HomeController
{
    private GameLogic gameLogic;
    private ObservableMap<String, Button> buttonMap;
    private int boardSize;

    @FXML
    private Button selectedPiece;

    @FXML
    private Label playerNameLabel;

    @FXML
    private TilePane mainBoard;

    /**
     * Set the FXML Control map from the main class (i.e. from loader.getNamespace()) and initialise game logic.
     *
     * We shouldn't use constructor here because the FXML loader may not recognise and handle it correctly.
     *
     */
    public void gameInit(int boardSize)
    {
        this.buttonMap = FXCollections.observableHashMap();

        // Board pane initialisation
        this.initBoardGui(this.mainBoard, boardSize);
        this.boardSize = boardSize;

        // Initialise game logic
        this.gameLogic = new GameLogic(this);
    }

    /**
     * Handle all board button click event
     *
     * @param clickEvent event input
     */
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
    @Requires({"coordinate.getPosX() > 0", "coordinate.getPosX() > 0"})
    public void commitUIChanges(Coordinate coordinate, String style)
    {
        // The ID format is "button_posX_posY"
        Button button = this.buttonMap.get(String.format("board_%d_%d",
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

    /**
     *  Initialise the board with specific size and button
     * @param pane Tile pane, i.e. GUI of the board
     * @param boardSize size of the board
     */
    @Requires({"pane != null", "boardSize > 0"})
    public void initBoardGui(TilePane pane, int boardSize)
    {
        // Disable gaps
        pane.setHgap(0);
        pane.setVgap(0);

        // Set to specific size of the pane
        pane.setPrefColumns(boardSize);
        pane.setPrefRows(boardSize);

        for(int buttonXCounter = 0; buttonXCounter < boardSize; buttonXCounter += 1) {
            for(int buttonYCounter = 0; buttonYCounter < boardSize; buttonYCounter += 1) {

                // Initialise a button
                Button button = new Button();

                // Set the size of it, we have to -1 to make sure it can display correctly
                button.setPrefSize((this.mainBoard.getPrefWidth() / boardSize) - 1,
                        (this.mainBoard.getPrefHeight() / boardSize) - 1);

                // Set board button's CSS pseudo class to "board-button"
                button.getStyleClass().add("board-button");

                // Add button event listener
                button.setOnAction(this::handleBoardButtonClick);

                // Add button to the button map with specific name
                String buttonId = String.format("board_%d_%d", buttonXCounter, buttonYCounter);
                button.setId(buttonId);
                this.buttonMap.put(buttonId, button);

                // Add button to the GUI
                this.mainBoard.getChildren().add(button);
            }
        }
    }

    public ObservableMap<String, Button> getButtonMap()
    {
        return this.buttonMap;
    }

    public int getBoardSize()
    {
        return this.boardSize;
    }

}

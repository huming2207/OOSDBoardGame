package controllers;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import controllers.logic.GameLogic;
import helpers.BoardButtonHelper;
import helpers.exceptions.DuplicatedPieceException;
import helpers.exceptions.InvalidPieceSelectionException;
import helpers.reactions.Reaction;
import helpers.reactions.ReactionLevel;
import helpers.reactions.ReactionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.misc.GenericSettings;
import models.coordinate.Coordinate;
import models.player.Player;

import java.io.*;

/**
 * Home GUI controller
 *
 * @author Ming Hu
 * @since Assignment 1
 */
public class HomeController
{
    private GameLogic gameLogic;
    private ObservableMap<String, Button> buttonMap;
    private int boardSize;
    private Stage currentStage;
    private Reaction reaction;
    private GenericSettings settings;

    @FXML
    private Button selectedPiece;

    @FXML
    private Label playerNameLabel;

    @FXML
    private TilePane mainBoard;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label communismPlayerScore;

    @FXML
    private Label capitalismPlayerScore;

    /**
     * Set the FXML Control map from the main class (i.e. from loader.getNamespace()) and initialize game logic.
     * <p>
     * We shouldn't use constructor here because the FXML loader may not recognise and handle it correctly.
     */
    public void gameInit(Stage stage)
    {
        // Load the settings
        // This function should run before game logic class is created
        this.loadSettings();

        this.buttonMap = FXCollections.observableHashMap();

        // Board pane initialisation
        this.initBoardGui(this.mainBoard, this.settings.getBoardSize());
        this.boardSize = this.settings.getBoardSize();

        // Initialize game logic
        this.gameLogic = new GameLogic(this);

        // Set current stage
        this.currentStage = stage;
    }

    @FXML
    public void initialize()
    {
        // Workaround for macOS users
        // Push the menu bar to the system native menu bar area (top of the screen)
        // Ref: https://stackoverflow.com/questions/22569046/how-to-make-an-os-x-menubar-in-javafx
        final String osName = System.getProperty("os.name");
        if (osName != null && osName.toUpperCase().contains("MAC"))
            menuBar.useSystemMenuBarProperty().set(true);

        this.reaction = ReactionManager.getReaction();
    }

    @FXML
    private void handleSaveAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save your status to file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("BoardGame status (*.status)", "*.status"));

        File statusFile = fileChooser.showSaveDialog(this.currentStage);

        if (statusFile == null) {
            return; // does nothing if user didn't correctly pick a file
        }

        String statusFilePath = statusFile.getAbsolutePath();

        // Prevents user set a wrong extension name
        if (!statusFilePath.endsWith(".status")) {
            statusFilePath += ".status";
        }

        try {
            this.gameLogic.getStatusManager().serializeStatusToFile(statusFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            this.reaction.handleReaction(ReactionLevel.CRASH, "Cannot save the file!");
        }
    }

    @FXML
    private void handleLoadAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load your status from file");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("BoardGame status (*.status)", "*.status"));

        File statusFile = fileChooser.showOpenDialog(this.currentStage);

        if (statusFile == null) {
            return; // does nothing if user didn't correctly pick a file
        } else {
            // Clear the board before loading the status
            this.gameLogic.getBoard().getPieceList().clear();
        }

        String statusFilePath = statusFile.getAbsolutePath();

        try {
            this.gameLogic.getStatusManager().loadStatusFromFile(statusFilePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            this.reaction.handleReaction(ReactionLevel.CRASH, "Cannot load the file!");
        }
    }

    @FXML
    private void handleRedoAction()
    {
        this.gameLogic.getStatusManager().performRedo();
    }

    @FXML
    private void handleUndoAction()
    {
        this.gameLogic.getStatusManager().performUndo();
    }

    @FXML
    private void handleExitAction()
    {
        System.exit(0);
    }

    @FXML
    private void handleDefensiveToggle(ActionEvent event)
    {
        ToggleButton defensiveToggle = (ToggleButton) event.getSource();
        this.gameLogic.getBoard().setDefensiveMode(defensiveToggle.isSelected());
    }

    @FXML
    private void handleAboutAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("views/About.fxml"));

            // Set the scene by getting the Parent scene from FXMLLoader
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("About");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSettingAction()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("views/Settings.fxml"));

            // Set the scene by getting the Parent scene from FXMLLoader
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Settings");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle all board button click event
     *
     * @param clickEvent event input
     */
    private void handleBoardButtonClick(ActionEvent clickEvent)
    {
        Coordinate buttonResult = BoardButtonHelper.parseClickResult(clickEvent);

        try {
            this.gameLogic.commitMapChanges(buttonResult);
        } catch (DuplicatedPieceException e) {
            this.reaction.handleReaction(ReactionLevel.WARN, "You're putting the piece on a non-empty place!");
        } catch (InvalidPieceSelectionException e) {
            this.reaction.handleReaction(ReactionLevel.WARN, "You're selecting a piece in the wrong role!");
        }
    }

    /**
     * Update the UI from game logic
     * - Query the controlMap to get the button, if null, raise an Alert dialog
     * - Apply style when a valid button
     *
     * @param coordinate Coordinate of a button
     * @param style      CSS style
     */
    @Requires({"coordinate.getPosX() > 0", "coordinate.getPosX() > 0"})
    public void commitUIChanges(Coordinate coordinate, String style)
    {
        // The ID format is "button_posX_posY"
        Button button = this.buttonMap.get(String.format("board_%d_%d",
                coordinate.getPosX(), coordinate.getPosY()));

        // In case the game algorithm goes wrong...
        // Btw style string does not need to check since it is constant in the model.
        if (button == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Operation out of range!");
            alert.showAndWait();
            return;
        }

        button.setStyle(style);
    }

    /**
     * Set the selected piece to selectPiece button to tell user they've selected a piece in GUI
     * <p>
     * PS: here using cofoja for null checking is useful,
     * just in case if someone use this method when currentPlayer is not initialized.
     *
     * @param player Committed player
     */
    @Requires("player != null")
    public void commitPlayerSelection(Player player)
    {
        // Set the style of selectPiece button. If piece is null, then set a empty style.
        if (player.getSelectedPiece() != null) {
            this.selectedPiece.setStyle(player.getSelectedPiece().getStyle());
        } else {
            this.selectedPiece.setStyle(null);
        }

        // Set the next player name
        this.playerNameLabel.setText(player.getPlayerName());
    }

    /**
     * Initialize the board with specific size and button
     *
     * @param pane      Tile pane, i.e. GUI of the board
     * @param boardSize size of the board
     */
    @Requires({"pane != null", "boardSize > 0"})
    private void initBoardGui(TilePane pane, int boardSize)
    {
        // Disable gaps
        pane.setHgap(0);
        pane.setVgap(0);

        // Set to specific size of the pane
        pane.setPrefColumns(boardSize);
        pane.setPrefRows(boardSize);

        for (int buttonXCounter = 0; buttonXCounter < boardSize; buttonXCounter += 1) {
            for (int buttonYCounter = 0; buttonYCounter < boardSize; buttonYCounter += 1) {

                // Initialize a button
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

    /**
     * Get board size, e.g. 8 for 8x8 board or 10 for 10x10 board
     * @return board size
     */
    @Ensures("result >= 6")
    public int getBoardSize()
    {
        return this.boardSize;
    }

    /**
     * Get setting instance
     * @return generic settings instance
     */
    public GenericSettings getSettings()
    {
        return this.settings;
    }

    /**
     * Update score indicators with specific String
     * @param communismPlayer communism player's score in String
     * @param capitalismPlayer capitalism player's score in String
     */
    public void updateScoreIndicators(String communismPlayer, String capitalismPlayer)
    {
        this.communismPlayerScore.setText(communismPlayer);
        this.capitalismPlayerScore.setText(capitalismPlayer);
    }

    private void loadSettings()
    {
        File file = new File("settings.bin");

        // If file does not exist, load the default settings and stop
        if (!file.exists() || file.isDirectory()) {
            this.reaction.handleReaction(ReactionLevel.DEBUG, "setting.bin is not found, loading default...");
            this.settings = new GenericSettings();
            return;
        }

        // ...or, load the settings
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            this.settings = (GenericSettings) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // if failed, print stack trace for debugging and load default settings
            e.printStackTrace();
            this.reaction.handleReaction(ReactionLevel.DEBUG,
                    "setting.bin found but failed to load, loading default...");
            this.settings = new GenericSettings();
        }
    }

}

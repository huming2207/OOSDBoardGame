package controllers;

import helpers.BoardButtonHelper;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.BoardButtonResult;
import models.game.Coordinate;
import models.game.piece.Piece;

import java.util.Map;

public class HomeController implements MapChangeListener<Coordinate, Piece>
{
    private GameMap gameMap;
    private Map<String, Object> controlMap;

    public HomeController()
    {
        this.gameMap = new GameMap();
    }

    @FXML
    private void initialize()
    {
        gameMap.getGameMap().addListener(this);
    }

    /**
     * Set the FXML Control map from the main class (i.e. from loader.getNamespace())
     * @param controlMap Map source to be set
     */
    public void setControlMap(Map<String, Object> controlMap)
    {
        this.controlMap = controlMap;
    }

    /**
     * Handle all board button click event
     * @param clickEvent event input
     */
    @FXML
    private void handleBoardButtonClick(ActionEvent clickEvent)
    {
        BoardButtonResult buttonResult = BoardButtonHelper.parseClickResult(clickEvent);


        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                String.format("Button at row %d col %d clicked!", buttonResult.getPosX(), buttonResult.getPosY()));

        alert.show();
    }

    /**
     * Triggers when GameMap is changed
     * @param change
     */
    @Override
    public void onChanged(Change<? extends Coordinate, ? extends Piece> change)
    {

    }
}

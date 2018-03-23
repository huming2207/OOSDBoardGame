package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import models.game.Coordinate;
import models.game.piece.NullPiece;
import models.game.piece.Piece;


public class GameMap
{
    private ObservableMap<Coordinate, Piece> gameMap = FXCollections.observableHashMap();

    public GameMap()
    {
        // Initialize game map
        for(int posX = 0; posX <= 7; posX += 1) {
            for(int posY = 0; posY <= 7; posY += 1) {
                gameMap.put(new Coordinate(posX, posY), new NullPiece());
            }
        }
    }

    public ObservableMap<Coordinate, Piece> getGameMap()
    {
        return gameMap;
    }

    public void setGameMap(ObservableMap<Coordinate, Piece> gameMap)
    {
        this.gameMap = gameMap;
    }
}

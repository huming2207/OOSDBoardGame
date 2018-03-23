package controllers;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import models.BoardButtonResult;
import models.game.Coordinate;
import models.game.piece.MagePiece;
import models.game.piece.NullPiece;
import models.game.piece.Piece;


public class GameLogic implements MapChangeListener<Coordinate, Piece>
{
    private ObservableMap<Coordinate, Piece> gameMap = FXCollections.observableHashMap();
    private HomeController parentController;

    public GameLogic(HomeController homeController)
    {
        // Initialize game map
        for(int posX = 0; posX <= 7; posX += 1) {
            for(int posY = 0; posY <= 7; posY += 1) {
                gameMap.put(new Coordinate(posX, posY), new NullPiece());
            }
        }
    }

    public void commitMapChanges(BoardButtonResult clickResult)
    {
        // TODO: Here's just a demo, need to replace with the real logic later...
        gameMap.put(new Coordinate(clickResult.getPosX() + 1, clickResult.getPosY() + 1 ), new MagePiece());
        gameMap.put(new Coordinate(clickResult.getPosX() - 1, clickResult.getPosY() + 1 ), new MagePiece());
        gameMap.put(new Coordinate(clickResult.getPosX() + 1, clickResult.getPosY() - 1 ), new MagePiece());
        gameMap.put(new Coordinate(clickResult.getPosX() - 1, clickResult.getPosY() - 1 ), new MagePiece());
    }


    /**
     * Trigger automatically when GameMap is changed.
     * @param change items changed
     */
    @Override
    public void onChanged(Change<? extends Coordinate, ? extends Piece> change)
    {
        parentController.commitUIChanges(change.getKey(), change.getValueAdded().getStyle());
    }
}

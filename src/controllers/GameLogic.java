package controllers;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import models.gui.BoardButtonEvent;
import models.game.Coordinate;
import models.game.piece.*;


public class GameLogic implements MapChangeListener<Coordinate, Piece>
{
    private ObservableMap<Coordinate, Piece> gameMap = FXCollections.observableHashMap();
    private HomeController homeController;

    public GameLogic(HomeController homeController)
    {
        // Initialize game map
        for(int posX = 0; posX <= 7; posX += 1) {
            for(int posY = 0; posY <= 7; posY += 1) {
                gameMap.put(new Coordinate(posX, posY), new EmptyPiece());
            }
        }

        // Assign home controller
        this.homeController = homeController;

        // Add change listener
        gameMap.addListener(this);
    }

    /**
     * Commit changes to game map
     * @param clickResult
     */
    public void commitMapChanges(BoardButtonEvent clickResult)
    {
        // TODO: Here's just a demo, need to replace with the real logic later...
        gameMap.put(new Coordinate(clickResult.getPosX() + 1, clickResult.getPosY() + 1 ), new GooglePiece());
        gameMap.put(new Coordinate(clickResult.getPosX() - 1, clickResult.getPosY() + 1 ), new WeChatPiece());
        gameMap.put(new Coordinate(clickResult.getPosX() + 1, clickResult.getPosY() - 1 ), new FacebookPiece());
        gameMap.put(new Coordinate(clickResult.getPosX() - 1, clickResult.getPosY() - 1 ), new TwitterPiece());
    }


    /**
     * Trigger automatically when GameMap is changed.
     * @param change items changed
     */
    @Override
    public void onChanged(Change<? extends Coordinate, ? extends Piece> change)
    {
        homeController.commitUIChanges(change.getKey(), change.getValueAdded().getStyle());
    }
}

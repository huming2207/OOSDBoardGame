package controllers;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import models.gui.BoardButtonEvent;
import models.game.Coordinate;
import models.game.piece.*;

import java.util.HashMap;
import java.util.Map;


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
     * @param buttonEvent Supplied click result information
     */
    public void commitMapChanges(BoardButtonEvent buttonEvent)
    {

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

    /**
     * Detect pieces nearby
     *
     * @param coordinate Coordinate of the center piece
     * @param piece Center piece
     * @return a key-value map of piece information. If no piece found, return an empty list.
     */
    private Map<Coordinate, Piece> detectPiece(Coordinate coordinate, Piece piece)
    {
        HashMap<Coordinate, Piece> pieceMap = new HashMap<>();

        // If null, return empty map
        if(coordinate == null || piece == null) return pieceMap;

        // If coordinate itself is wrong, return empty map
        if(coordinate.getPosX() < 0 || coordinate.getPosX() > 7
                || coordinate.getPosY() < 0 || coordinate.getPosY() > 7) return pieceMap;

        /*
          Assume
            1. original piece is in point (0, 0);
            2. this method should perform a search in 5x5 area, i.e. (-2, -2) to (2, 2).

          Note
            1. ignore original piece itself at (0, 0);
            2. ignore overflow position (less than 0 or larger than 7);
            3. ignore placeholder (EmptyPiece).
         */

        for(int offsetX = -2; offsetX <= 2; offsetX += 1) {
            for(int offsetY = -2; offsetY <= 2; offsetY += 1) {
                int posX = coordinate.getPosX() + offsetX;
                int posY = coordinate.getPosY() + offsetY;

                // Ignore original piece
                if(posX == coordinate.getPosX() && posY == coordinate.getPosY()) continue;

                // Try get the piece, it will be null if out of range.
                Piece selectedPiece = gameMap.get(new Coordinate(posX, posY));

                // Ignore non-existence (out of range) piece or placeholder
                if(selectedPiece == null || selectedPiece instanceof EmptyPiece) continue;


                pieceMap.put(new Coordinate(posX, posY), selectedPiece);
            }
        }

        return pieceMap;
    }
}

package models.game;

import models.game.piece.NullPiece;
import models.game.piece.Piece;

import java.util.HashMap;

public class GameMap
{
    private HashMap<Coordinate, Piece> gameMap = new HashMap<>();

    public GameMap()
    {
        // Initialize game map
        for(int posX = 0; posX <= 7; posX += 1) {
            for(int posY = 0; posY <= 7; posY += 1) {
                gameMap.put(new Coordinate(posX, posY), new NullPiece());
            }
        }
    }


}

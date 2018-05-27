package models.factory;

import com.google.java.contract.Requires;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;
import models.player.Player;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Coordinate factory
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public class CoordinateFactory implements BoardFactory
{
    @Override
    @Requires("boardSize >= 6")
    public Coordinate createCoordinate(int boardSize)
    {
        int posX = ThreadLocalRandom.current().nextInt(0, boardSize);
        int posY = ThreadLocalRandom.current().nextInt(0, boardSize);
        return new Coordinate(posX, posY);
    }

    @Override
    public Coordinate createCoordinate(int x, int y)
    {
        return new Coordinate(x, y);
    }

    @Override
    public Piece createPiece(CharacterType characterType, Coordinate coordinate)
    {
        return null;
    }

    @Override
    public Player createPlayer(String playerName, RoleType roleType)
    {
        return null;
    }
}

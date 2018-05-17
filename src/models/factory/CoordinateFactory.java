package models.factory;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;

import java.util.concurrent.ThreadLocalRandom;

public class CoordinateFactory extends AbstractBoardFactory
{
    public CoordinateFactory(int boardSize)
    {
        super(boardSize);
    }

    @Override
    public Coordinate createCoordinate()
    {
        int posX = ThreadLocalRandom.current().nextInt(0, super.boardSize);
        int posY = ThreadLocalRandom.current().nextInt(0, super.boardSize);
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
}

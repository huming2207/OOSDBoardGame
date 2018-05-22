package models.factory;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;

public class PlayerFactory extends AbstractBoardFactory
{
    protected PlayerFactory(int boardSize)
    {
        super(boardSize);
    }

    @Override
    public Coordinate createCoordinate()
    {
        return null;
    }

    @Override
    public Coordinate createCoordinate(int x, int y)
    {
        return null;
    }

    @Override
    public Piece createPiece(CharacterType characterType, Coordinate coordinate)
    {
        return null;
    }
}

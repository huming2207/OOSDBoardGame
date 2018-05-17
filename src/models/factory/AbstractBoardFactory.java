package models.factory;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;

public abstract class AbstractBoardFactory
{
    protected int boardSize;

    public AbstractBoardFactory(int boardSize)
    {
        this.boardSize = boardSize;
    }

    public abstract Coordinate createCoordinate();
    public abstract Coordinate createCoordinate(int x, int y);
    public abstract Piece createPiece(CharacterType characterType, Coordinate coordinate);
}

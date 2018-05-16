package models.piece.decorators.ranges;

import models.piece.Piece;

public abstract class AbstractRange implements Piece
{
    protected Piece decoratePiece;

    public AbstractRange(Piece piece)
    {
        this.decoratePiece = piece;
    }
}

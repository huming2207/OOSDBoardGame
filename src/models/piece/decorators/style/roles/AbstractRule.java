package models.piece.decorators.style.roles;

import models.piece.Piece;

public abstract class AbstractRule implements Piece
{
    protected Piece decoratePiece;

    public AbstractRule(Piece piece)
    {
        this.decoratePiece = piece;
    }

}

package models.piece.decorators.style.characters;

import models.piece.Piece;

public abstract class AbstractCharacter implements Piece
{
    protected Piece decoratePiece;
    protected int mark;

    public AbstractCharacter(Piece piece)
    {
        this.decoratePiece = piece;
    }
}

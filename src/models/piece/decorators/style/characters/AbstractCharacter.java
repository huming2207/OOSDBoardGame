package models.piece.decorators.style.characters;

import models.piece.Piece;
import models.piece.PiecePrototype;

import java.io.IOException;
import java.io.Serializable;

public abstract class AbstractCharacter implements Piece, Cloneable, Serializable
{
    protected Piece decoratePiece;
    protected int mark;

    public AbstractCharacter(Piece piece)
    {
        this.decoratePiece = piece;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Piece clone()
    {
        try {
            return PiecePrototype.deepClone(this);

        } catch (IOException | ClassNotFoundException e) {

            // If something happens, do a shallow clone instead...
            e.printStackTrace();

            try {
                return (Piece)super.clone();

            } catch (CloneNotSupportedException cloneEx) {
                cloneEx.printStackTrace();
                return null;
            }
        }
    }
}

package models.piece.decorators.style.characters;

import com.google.java.contract.Invariant;
import helpers.CloneHelper;
import models.piece.Piece;

import java.io.IOException;
import java.io.Serializable;

/**
 * Abstract character decorator
 * It adds icons and character type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant({"mark >= 0"})
public abstract class Character implements Piece, Cloneable, Serializable
{
    protected Piece decoratePiece;
    protected int mark;

    public Character(Piece piece)
    {
        this.decoratePiece = piece;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Piece clone()
    {
        try {
            return (Piece)CloneHelper.deepClone(this);

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

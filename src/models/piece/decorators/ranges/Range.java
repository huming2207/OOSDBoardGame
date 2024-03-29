package models.piece.decorators.ranges;

import helpers.CloneHelper;
import models.piece.Piece;

import java.io.IOException;
import java.io.Serializable;

/**
 * Range decorators
 * It sets specific attack ranges and move ranges offset
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public abstract class Range implements Piece, Cloneable, Serializable
{
    protected Piece decoratePiece;

    public Range(Piece piece)
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

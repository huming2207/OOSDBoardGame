package models.piece.decorators.style.roles;

import helpers.CloneHelper;
import models.piece.Piece;

import java.io.IOException;
import java.io.Serializable;

/**
 * Communism character decorator
 * It sets icon's color and set the role type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public abstract class Role implements Piece, Cloneable, Serializable
{
    protected Piece decoratePiece;

    public Role(Piece piece)
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

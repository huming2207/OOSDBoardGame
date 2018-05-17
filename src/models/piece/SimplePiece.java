package models.piece;

import models.coordinate.Coordinate;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

import java.io.*;

public class SimplePiece implements Piece, Cloneable, Serializable
{
    private Coordinate coordinate;
    private int mark;

    public SimplePiece(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    @Override
    public String getStyle()
    {
        return "";
    }

    @Override
    public int getAttackLevel()
    {
        return -1;
    }

    @Override
    public void applyAttack(int deduction)
    {
        this.mark += deduction;
    }

    @Override
    public int getMark()
    {
        return this.mark;
    }

    @Override
    public Coordinate getCoordinate()
    {
        return this.coordinate;
    }

    @Override
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    @Override
    public RoleType getRoleType()
    {
        return null;
    }

    @Override
    public int[][] getMoveRangeAllowance()
    {
        return new int[0][];
    }

    @Override
    public int[][] getAttackRangeAllowance()
    {
        return new int[0][];
    }

    @Override
    public CharacterType getCharacterType()
    {
        return null;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Piece clone()
    {
        try {
            return PieceGenerator.deepClone(this);

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

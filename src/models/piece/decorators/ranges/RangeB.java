package models.piece.decorators.ranges;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * Range Type B decorator
 * It sets specific attack ranges and move ranges offset
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public class RangeB extends Range
{
    public RangeB(Piece piece)
    {
        super(piece);
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle();
    }

    public int getAttackLevel()
    {
        return super.decoratePiece.getAttackLevel();
    }

    public void sufferAttack(int deduction)
    {
        super.decoratePiece.sufferAttack(deduction);
    }

    public int getMark()
    {
        return super.decoratePiece.getMark();
    }

    public Coordinate getCoordinate()
    {
        return super.decoratePiece.getCoordinate();
    }

    public void setCoordinate(Coordinate coordinate)
    {
        super.decoratePiece.setCoordinate(coordinate);
    }

    public RoleType getRoleType()
    {
        return super.decoratePiece.getRoleType();
    }

    public CharacterType getCharacterType()
    {
        return super.decoratePiece.getCharacterType();
    }

    public int[][] getMoveRangeOffset()
    {
        return new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    }

    public int[][] getAttackZoneOffset()
    {
        return new int[][] {
                {-2, 0}, {-1, 1}, {0, 2}, {1, 1},
                {2, 0}, {1, -1}, {0, -2}, {-1, -1}
        };
    }
}

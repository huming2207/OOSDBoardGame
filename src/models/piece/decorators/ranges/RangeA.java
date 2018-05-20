package models.piece.decorators.ranges;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

public class RangeA extends AbstractRange
{
    public RangeA(Piece piece)
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
        return RoleType.COMMUNISM_PIECE;
    }

    public CharacterType getCharacterType()
    {
        return super.decoratePiece.getCharacterType();
    }

    public int[][] getMoveRangeAllowance()
    {
        return new int[][] {{-1, 1}, {1, 1}, {-1, -1}, {1, -1}};
    }

    public int[][] getAttackRangeAllowance()
    {
        return new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    }
}

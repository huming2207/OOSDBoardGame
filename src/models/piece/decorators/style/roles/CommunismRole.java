package models.piece.decorators.style.roles;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.RoleType;

public class CommunismRole extends AbstractRule
{
    public CommunismRole(Piece piece)
    {
        super(piece);
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle() + " -fx-color: #ff0000; ";
    }

    public int getAttackLevel()
    {
        return super.decoratePiece.getAttackLevel();
    }

    public void applyAttack(int deduction)
    {
        super.decoratePiece.applyAttack(deduction);
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

    public int[][] getMoveRangeAllowance()
    {
        return super.decoratePiece.getMoveRangeAllowance();
    }

    public int[][] getAttackRangeAllowance()
    {
        return super.decoratePiece.getMoveRangeAllowance();
    }
}

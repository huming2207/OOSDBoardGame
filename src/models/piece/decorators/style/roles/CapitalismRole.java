package models.piece.decorators.style.roles;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * Capitalism role decorator
 * It sets icons to blue and set capitalism role type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public class CapitalismRole extends Role
{
    public CapitalismRole(Piece piece)
    {
        super(piece);
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle() + " -fx-color: #0000ff; ";
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
        return RoleType.CAPITALISM_PIECE;
    }

    public CharacterType getCharacterType()
    {
        return super.decoratePiece.getCharacterType();
    }

    public int[][] getMoveRangeOffset()
    {
        return super.decoratePiece.getMoveRangeOffset();
    }

    public int[][] getAttackZoneOffset()
    {
        return super.decoratePiece.getMoveRangeOffset();
    }
}

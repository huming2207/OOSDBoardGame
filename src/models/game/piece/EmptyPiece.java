package models.game.piece;

public class EmptyPiece implements Piece
{
    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return "-fx-border-color: #000000;";
    }

    @Override
    public int getAttackLevel()
    {
        return -1;
    }

    @Override
    public void applyAttack(int deduction)
    {
        // Do nothing?
    }

    @Override
    public int getMark()
    {
        return -1;
    }
}

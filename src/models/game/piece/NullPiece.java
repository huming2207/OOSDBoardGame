package models.game.piece;

public class NullPiece implements Piece
{
    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return null;
    }

    @Override
    public PieceType getType()
    {
        return PieceType.Null;
    }
}

package models.game.piece;

public class NullPiece implements Piece
{
    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return "-fx-border-color: #000000;";
    }

    @Override
    public PieceType getType()
    {
        return PieceType.Null;
    }
}

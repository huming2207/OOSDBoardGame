package models.game.piece;

public class KnightPiece implements Piece
{
    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return "-fx-border-color: #0000ff;";
    }


    @Override
    public PieceType getType()
    {
        return PieceType.Knight;
    }
}

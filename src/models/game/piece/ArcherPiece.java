package models.game.piece;

public class ArcherPiece implements Piece
{
    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return "-fx-border-color: #00ff00;";
    }


    @Override
    public PieceType getType()
    {
        return PieceType.Archer;
    }
}

package models.game.piece;

import models.game.Coordinate;

public class EmptyPiece extends Piece
{
    public EmptyPiece(Coordinate coordinate)
    {
        super(coordinate, -1, -1);
    }

    @Override
    public String getStyle()
    {
        // FIXME: add Archer CSS and SVG string here
        return "-fx-border-color: #000000;";
    }
}

package models.game.piece;

import javafx.scene.paint.Color;

public class KnightPiece implements Piece
{
    private Color color;

    @Override
    public void setColor(Color color)
    {

    }

    @Override
    public Color getColor()
    {
        return null;
    }


    @Override
    public PieceType getType()
    {
        return PieceType.Knight;
    }
}

package models.game.piece;

import javafx.scene.paint.Color;

public interface Piece
{
    void setColor(Color color);
    Color getColor();
    PieceType getType();
}

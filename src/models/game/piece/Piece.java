package models.game.piece;

public interface Piece
{
    /**
     * Style string method of each piece
     * The style string is based on JavaFX CSS, we can also put in some SVG images.
     *
     * @return CSS String
     */
    String getStyle();

    /**
     * Supply piece type of each kind of piece
     * Ref: {@link PieceType}
     *
     * @return Piece type in Enum
     */
    PieceType getType();
}

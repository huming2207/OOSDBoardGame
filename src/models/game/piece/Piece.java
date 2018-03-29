package models.game.piece;

public interface Piece
{
    /**
     * Style string method of each piece
     * The style string is based on JavaFX CSS, we can also put in some SVG images.
     *
     * TODO: bring a independent SVG parser instead of writing a constant JavaFX Style Sheet string inside the code
     *
     * @return CSS String
     */
    String getStyle();

    /**
     * Set the value of privacy attack level
     *
     * @return Attack level value in x/100 marks
     */
    int getAttackLevel();

    /**
     * Apply attack from other pieces
     *
     * @param deduction HP value (mark) deduction
     */
    void applyAttack(int deduction);

    /**
     * Get current mark (max. 100?)
     *
     * @return current mark, 0 to 100
     */
    int getMark();

}

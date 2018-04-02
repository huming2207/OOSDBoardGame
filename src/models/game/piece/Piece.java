package models.game.piece;

import models.game.Coordinate;

public abstract class Piece
{
    private int mark;
    private Coordinate coordinate;
    private final int attackLevel;

    public Piece(Coordinate coordinate, int mark, int attackLevel)
    {
        this.mark = mark;
        this.coordinate = coordinate;
        this.attackLevel = attackLevel;
    }

    /**
     * Style string method of each piece
     * The style string is based on JavaFX CSS, we can also put in some SVG images.
     *
     * TODO: bring a independent SVG parser instead of writing a constant JavaFX Style Sheet string inside the code
     *
     * @return CSS String
     */
    public abstract String getStyle();

    /**
     * Set the value of privacy attack level
     *
     * @return Attack level value in x/100 marks
     */
    public int getAttackLevel()
    {
        return this.attackLevel;
    }

    /**
     * Apply attack from other pieces
     *
     * @param deduction HP value (mark) deduction
     */
    public void applyAttack(int deduction)
    {
        this.mark = this.mark - deduction;
    }

    /**
     * Get current mark (max. 100?)
     *
     * @return current mark, 0 to 100
     */
    public int getMark()
    {
        return this.mark;
    }

    /**
     * Get coordinate of the piece
     *
     * @return current coordinate of the piece
     */
    public Coordinate getCoordinate()
    {
        return this.coordinate;
    }

    /**
     * Set coordinate of the piece
     *
     * @param coordinate coordinate of the piece to set
     */
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

}

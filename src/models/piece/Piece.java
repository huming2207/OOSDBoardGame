package models.piece;

import models.coordinate.Coordinate;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 *
 * Piece interface
 *
 * Ref: https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
 *      https://en.wikipedia.org/wiki/Decorator_pattern#Java
 *
 * This model uses decorator design pattern, please check these packages:
 *
 *  - models.piece.decorator.ranges
 *  - models.piece.decorator.style.characters
 *  - models.piece.decorator.style.roles
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 1
 *
 */
public interface Piece
{
    /**
     * Style string method of each piece
     * The style string is based on JavaFX CSS, we can also put in some SVG images.
     *
     * PS: Icon collection used in this project comes from Alibaba IconFont and their contributors,
     *      free for personal & education purposes.
     *
     * Ref:  http://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.28
     *
     * TODO: bring a independent SVG parser instead of writing a constant JavaFX Style Sheet string inside the code
     *
     * @return CSS String
     */
    String getStyle();

    /**
     * Set the value of attack level
     *
     * @return Attack level value in x/100 marks
     */
    int getAttackLevel();

    /**
     * Suffer attack from other pieces
     * Deduction should be a negative number
     *
     * @param deduction HP value (mark) deduction
     */
    void sufferAttack(int deduction);

    /**
     * Get current mark (max. 100?)
     *
     * @return current mark, 0 to 100
     */
    int getMark();

    /**
     * Get coordinate of the piece
     *
     * @return current coordinate of the piece
     */
    Coordinate getCoordinate();

    /**
     * Set coordinate of the piece
     *
     * @param coordinate coordinate of the piece to set
     */
    void setCoordinate(Coordinate coordinate);

    /**
     * Get the TYPE of the piece
     *
     * @return Role TYPE for player's selection
     */
    RoleType getRoleType();

    CharacterType getCharacterType();

    int[][] getMoveRangeOffset();

    int[][] getAttackZoneOffset();

    @Override
    int hashCode();

    @Override
    boolean equals(Object o);

    Piece clone();
}

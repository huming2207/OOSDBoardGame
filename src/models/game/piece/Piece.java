package models.game.piece;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import models.game.coordinate.Coordinate;
import models.game.piece.type.RoleType;

import java.util.Objects;

public abstract class Piece
{
    private int mark;
    private Coordinate coordinate;
    private final int ATTACK_LEVEL;
    private final RoleType TYPE;

    public Piece(Coordinate coordinate, int mark, int attackLevel, RoleType type)
    {
        this.mark = mark;
        this.coordinate = coordinate;
        this.ATTACK_LEVEL = attackLevel;
        this.TYPE = type;
    }

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
    @Ensures("!result.isEmpty()")
    public abstract String getStyle();

    /**
     * Set the value of privacy attack level
     *
     * @return Attack level value in x/100 marks
     */
    @Ensures("result > 0")
    public int getAttackLevel()
    {
        return this.ATTACK_LEVEL;
    }

    /**
     * Apply attack from other pieces
     *
     * @param deduction HP value (mark) deduction
     */
    @Requires("deduction > 0")
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
    @Ensures({"result.getPosX() <= 7", "result.getPosX() >= 0", "result.getPosY() <= 7", "result.getPosY() >= 0"})
    public Coordinate getCoordinate()
    {
        return this.coordinate;
    }

    /**
     * Set coordinate of the piece
     *
     * @param coordinate coordinate of the piece to set
     */
    @Requires({"coordinate.getPosX() <= 7", "coordinate.getPosX() >= 0",
            "coordinate.getPosY() <= 7", "coordinate.getPosY() >= 0"})
    public void setCoordinate(Coordinate coordinate)
    {
        this.coordinate = coordinate;
    }

    /**
     * Get the TYPE of the piece
     *
     * @return Role TYPE for player's selection
     */
    public RoleType getRoleType()
    {
        return this.TYPE;
    }

    @Override
    public int hashCode()
    {
        // ...should be auto boxed to Integer object??
        return Objects.hash(this.coordinate, this.ATTACK_LEVEL, this.mark);
    }


    @Override
    public boolean equals(Object o)
    {
        // Self check
        if(this == o) return true;

        // Null check
        if(o == null) return false;

        // Type check
        if(o.getClass() != this.getClass()) return false;

        // Detailed check
        Piece piece = (Piece) o;
        return this.coordinate.getPosX() == piece.getCoordinate().getPosX()
                && this.coordinate.getPosY() == piece.getCoordinate().getPosY()
                && this.mark == piece.getMark()
                && this.ATTACK_LEVEL == piece.getAttackLevel();
    }
}

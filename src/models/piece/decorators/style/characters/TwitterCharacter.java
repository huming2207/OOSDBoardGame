package models.piece.decorators.style.characters;

import com.google.java.contract.Invariant;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * Twitter character decorator
 * It adds Twitter icon and character type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant({"mark >= 0"})
public class TwitterCharacter extends Character
{
    public TwitterCharacter(Piece piece)
    {
        super(piece);
        super.mark = 100;
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle() + " -fx-shape: " +
                "\"M512 0c-282.76736 0-512 229.23264-512 512s229.23264 512 512 512 512-229.23264 " +
                "512-512-229.23264-512-512-512zM766.48448 381.48096c0.24576 5.632 0.38912 11.30496 0.38912 16.97792 " +
                "0 173.50656-132.07552 373.59616-373.57568 373.59616-74.15808 0-143.17568-21.72928-201.27744-59.00288 " +
                "10.28096 1.20832 20.72576 1.8432 31.31392 1.8432 61.52192 0 118.12864-20.992 163.08224-56.2176-" +
                "57.46688-1.04448-105.94304-39.0144-122.65472-91.17696 8.02816 1.536 16.24064 2.3552 24.69888 2.3552 " +
                "11.9808 0 23.57248-1.61792 34.59072-4.608-60.06784-12.06272-105.32864-65.1264-105.32864-128.75776 " +
                "0-0.55296 0-1.10592 0.02048-1.65888 17.69472 9.8304 37.94944 15.74912 59.47392 16.42496-35.2256-" +
                "23.552-58.40896-63.73376-58.40896-109.30176 0-24.064 6.47168-46.61248 17.77664-66.00704 64.75776 " +
                "79.44192 161.50528 131.70688 270.6432 137.19552-2.23232-9.60512-3.39968-19.64032-3.39968-29.92128 " +
                "0-72.51968 58.79808-131.29728 131.29728-131.29728 37.76512 0 71.8848 15.95392 95.8464 41.472 " +
                "29.9008-5.87776 57.99936-16.81408 83.37408-31.86688-9.80992 30.65856-30.6176 56.40192-57.73312 " +
                "72.64256 26.56256-3.1744 51.87584-10.24 75.40736-20.66432-17.59232 26.33728-39.85408 " +
                "49.4592-65.51552 67.97312z\"; ";
    }

    public int getAttackLevel()
    {
        return -20;
    }
    
    public void sufferAttack(int deduction)
    {
        super.mark += deduction;
    }

    public int getMark()
    {
        return super.mark;
    }
    
    public Coordinate getCoordinate()
    {
        return super.decoratePiece.getCoordinate();
    }

    public void setCoordinate(Coordinate coordinate)
    {
        super.decoratePiece.setCoordinate(coordinate);
    }

    public RoleType getRoleType()
    {
        return super.decoratePiece.getRoleType();
    }

    public CharacterType getCharacterType()
    {
        return CharacterType.TWITTER;
    }

    public int[][] getMoveRangeOffset()
    {
        return super.decoratePiece.getMoveRangeOffset();
    }

    public int[][] getAttackZoneOffset()
    {
        return super.decoratePiece.getMoveRangeOffset();
    }
}

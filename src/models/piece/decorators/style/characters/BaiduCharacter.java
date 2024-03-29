package models.piece.decorators.style.characters;

import com.google.java.contract.Invariant;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * Baidu character decorator
 * It adds Baidu icon and character type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant({"mark >= 0"})
public class BaiduCharacter extends Character
{
    public BaiduCharacter(Piece piece)
    {
        super(piece);
        super.mark = 150;
    }

    @Override
    public String getStyle()
    {
        return super.decoratePiece.getStyle() +
                " -fx-shape: " +
                "\"M610.752 697.504l-45.6 0c0 0-5.856-3.616-5.856-13.824l0-95.68-51.456 0.672 0 106.432c0 " +
                "0 4.96 28.16 42.272 28.16l86.4 0 0-128.672-25.728 0 0 102.912zM456.352 594.56l-48.256 0c0 " +
                "0-44.544-1.76-60.16 48.736-5.408 33.696 4.832 46.528 6.624 50.816 1.792 4.224 16.192 29.12 52.32 " +
                "29.12l75.168 0 0-193.6-25.728-0.576 0 65.536zM456.352 697.504l-38.048 0c0 " +
                "0-23.424-0.64-30.656-27.648-3.616-12.064 0.576-23.552 2.464-29.024 1.76-5.408 9.6-20.544 " +
                "25.792-20.544l40.448 0 0 77.184zM511.2 33.6c-267.168 0-483.744 216.608-483.744 483.776s216.608 " +
                "483.744 483.744 483.744 483.744-216.608 483.744-483.744-216.576-483.776-483.744-483.776zM540.736 " +
                "290.336c3.84-35.2 45.92-89.184 79.616-81.44 33.6 7.648 64.288 52.256 58.08 90.624-6.08 38.4-36.608 " +
                "89.12-84.128 82.912-47.392-6.08-58.144-49.056-53.568-92.096zM441.216 193.504c35.52 0 64.288 40.896 " +
                "64.288 91.424 0 50.56-28.768 91.488-64.288 91.488-35.488 0-64.288-40.896-64.288-91.488 0-50.496 " +
                "28.8-91.424 64.288-91.424zM240.768 410.112c0 0 7.584-75.232 59.68-79.904 41.376-3.584 71.84 41.696 " +
                "75.008 67.616 1.952 16.8 10.72 93.696-53.664 107.552-64.192 13.792-88.096-60.48-81.056-95.264zM730." +
                "592 734.304c-35.2 82.976-163.872 39.872-163.872 39.872s-47.456-15.232-102.496-3.04c-55.072 " +
                "12.32-102.528 7.648-102.528 7.648s-64.416 1.568-82.752-79.808c-18.336-81.472 64.288-126.08 " +
                "70.464-133.664 6.08-7.712 48.96-36.896 76.48-82.944 27.648-46.08 110.272-82.976 168.416 7.616 " +
                "42.912 61.472 116.384 118.368 116.384 118.368s55.072 43.008 19.904 125.952zM719.84 545.28c-70.464 " +
                "1.6-73.472-47.616-73.472-82.88 0-36.928 7.584-89.184 64.32-89.184 56.64 0 71.872 55.36 71.872 73.728 " +
                "0 18.528 7.712 96.8-62.752 98.336z\"; ";
    }

    public int getAttackLevel()
    {
        return -50;
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
        return CharacterType.BAIDU;
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

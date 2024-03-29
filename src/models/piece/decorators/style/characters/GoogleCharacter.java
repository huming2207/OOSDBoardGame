package models.piece.decorators.style.characters;

import com.google.java.contract.Invariant;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * Google character decorator
 * It adds Google icon and character type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant({"mark >= 0"})
public class GoogleCharacter extends Character
{
    public GoogleCharacter(Piece piece)
    {
        super(piece);
        super.mark = 150;
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle() +
                " -fx-shape: " +
                "\"M410.148864 279.972864c-40.522752-1.19808-67.724288 39.596032-60.695552 92.85632 6.962176 " +
                "53.327872 45.504512 90.55232 86.028288 91.810816 40.464384 1.175552 63.740928-33.110016 " +
                "56.771584-86.445056C485.289984 324.934656 450.605056 281.208832 410.148864 279.972864zM427.14112 " +
                "573.678592c-60.367872-0.687104-111.486976 38.178816-111.486976 83.257344 0 45.973504 43.568128 " +
                "84.265984 103.958528 84.265984 84.836352 0 114.412544-35.93728 114.412544-81.94048 " +
                "0-5.551104-0.6912-10.984448-1.921024-16.229376-6.672384-26.00448-33.060864-40.281088-65.876992-" +
                "63.115264C454.358016 576.062464 441.20064 573.812736 427.14112 573.678592zM513.676288 0c-281.63072 " +
                "0-509.958144 228.763648-509.958144 510.911488 0 282.18368 228.328448 510.892032 509.958144 510.892032 " +
                "281.673728 0 509.950976-228.708352 509.950976-510.892032C1023.627264 228.763648 795.350016 0 " +
                "513.676288 0zM552.64256 375.949312c0 33.31584-18.363392 60.12928-44.394496 80.45056-25.376768 " +
                "19.893248-30.186496 28.19584-30.186496 45.099008 0 14.39744 30.314496 35.861504 44.274688 46.309376 " +
                "48.535552 36.478976 58.381312 59.42784 58.381312 105.023488 0 56.945664-61.270016 " +
                "113.509376-160.78336 113.509376-87.346176 0-161.050624-35.557376-161.050624-92.490752 0-57.750528 " +
                "61.134848-117.991424 148.466688-117.991424 9.531392 0 18.228224-0.26112 27.25376-0.26112-11.9296-" +
                "11.593728-21.594112-21.61664-21.594112-39.1936 0-10.484736 3.291136-20.451328 7.98208-29.379584-4.736 " +
                "0.305152-9.590784 0.618496-14.58688 0.618496-71.701504 0-113.475584-50.500608-113.475584-113.821696 " +
                "0-61.983744 63.561728-118.367232 139.828224-118.367232 39.33184 0 150.327296 0 150.327296 0l-33.619968 " +
                "35.369984-39.465984 0C537.884672 306.7904 552.64256 339.64032 552.64256 375.949312zM768.468992 " +
                "366.660608l-69.35552 0 0 69.419008-34.670592 0 0-69.419008-69.280768 0L595.162112 331.922432l69.280768 " +
                "0 0-69.489664 34.670592 0 0 69.489664 69.35552 0L768.468992 366.660608z\"; ";
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
        return CharacterType.GOOGLE;
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

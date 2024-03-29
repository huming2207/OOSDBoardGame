package models.piece.decorators.style.characters;

import com.google.java.contract.Invariant;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

/**
 * WeChat character decorator
 * It adds WeChat icon and character type to a certain piece
 *
 * @author Ming Hu
 * @since Assignment 2
 */
@Invariant({"mark >= 0"})
public class WeChatCharacter extends Character
{
    public WeChatCharacter(Piece piece)
    {
        super(piece);
        super.mark = 120;
    }

    public String getStyle()
    {
        return super.decoratePiece.getStyle() +
                " -fx-shape: " +
                "\"M320.417684 308.224c-19.442526 0-39.073684 13.056-39.073684 32.848842 0 19.712 " +
                "19.631158 32.929684 39.073684 32.929684 19.469474 0 32.525474-13.217684 32.525474-32.929684C352.943158 " +
                "321.28 339.900632 308.224 320.417684 308.224zM502.177684 374.016c19.631158 0 32.606316-13.231158 " +
                "32.606316-32.929684 0-19.806316-12.975158-32.848842-32.606316-32.848842-19.442526 0-39.006316 " +
                "13.056-39.006316 32.848842C463.184842 360.784842 482.735158 374.016 502.177684 374.016zM512 " +
                "0C229.214316 0 0 229.200842 0 512c0 282.785684 229.214316 512 512 512 282.799158 0 " +
                "512.013474-229.214316 512.013474-512C1024.013474 229.200842 794.799158 0 512 0zM404.843789 " +
                "644.082526c-32.444632 0-58.556632-6.642526-90.974316-13.217684l-90.799158 46.187789 " +
                "25.936842-79.211789c-65.010526-46.066526-103.922526-105.364211-103.922526-177.650526 0-125.278316 " +
                "116.992-223.905684 259.759158-223.905684 127.730526 0 239.602526 78.834526 262.036211 " +
                "184.818526-8.178526-0.929684-16.559158-1.536-25.007158-1.536-123.392 0-220.820211 " +
                "93.372632-220.820211 208.289684 0 19.213474 2.977684 37.645474 8.003368 55.228632C421.052632 " +
                "643.745684 413.022316 644.082526 404.843789 644.082526zM787.968 736.242526l19.536842 " +
                "65.886316L736.282947 762.610526c-26.044632 6.575158-52.048842 13.150316-77.918316 " +
                "13.150316-123.567158 0-221.008842-85.598316-221.008842-190.976 0-105.283368 97.441684-191.056842 " +
                "221.008842-191.056842 116.641684 0 220.577684 85.76 220.577684 191.056842C878.928842 644.082526 " +
                "840.124632 696.656842 787.968 736.242526zM729.532632 499.105684c-12.8 0-25.694316 " +
                "13.231158-25.694316 26.381474 0 13.204211 12.894316 26.273684 25.694316 26.273684 19.536842 0 " +
                "32.592842-13.069474 32.592842-26.273684C762.112 512.336842 749.069474 499.105684 729.532632 " +
                "499.105684zM586.657684 499.105684c-12.867368 0-25.923368 13.231158-25.923368 26.381474 0 13.204211 " +
                "13.056 26.273684 25.923368 26.273684 19.712 0 32.525474-13.069474 32.525474-26.273684C619.183158 " +
                "512.336842 606.369684 499.105684 586.657684 499.105684z\"; ";
    }

    public int getAttackLevel()
    {
        return -30;
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
        return CharacterType.WECHAT;
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

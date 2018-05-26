package models.factory;

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import models.coordinate.Coordinate;
import models.factory.command.PieceCreator;
import models.piece.Piece;
import models.piece.SimplePiece;
import models.piece.decorators.ranges.RangeA;
import models.piece.decorators.ranges.RangeB;
import models.piece.decorators.ranges.RangeC;
import models.piece.decorators.style.characters.*;
import models.piece.decorators.style.roles.CapitalismRole;
import models.piece.decorators.style.roles.CommunismRole;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;
import models.player.Player;

import java.util.HashMap;

/**
 * Piece factory, the factory class to create pieces
 *
 * Ref: https://en.wikipedia.org/wiki/Command_pattern#Java_8
 *
 * (for team members: please have a look for Java 8 Lambda expression first if you don't know it)
 *
 * @author Ming Hu
 * @since assignment 2
 */
public class PieceFactory extends AbstractBoardFactory
{
    private final HashMap<CharacterType, PieceCreator> creatorCommands;

    public PieceFactory()
    {
        this.creatorCommands = new HashMap<>();
        this.initPieceCreationCommands();
    }

    @Override
    public Coordinate createCoordinate(int boardSize)
    {
        return null;
    }

    @Override
    public Coordinate createCoordinate(int x, int y)
    {
        return null;
    }

    @Override
    @Requires("!creatorCommands.isEmpty()")
    @Ensures("result != null")
    public Piece createPiece(CharacterType characterType, Coordinate coordinate)
    {
        Piece newPiece = this.creatorCommands.get(characterType).createPiece();
        newPiece.setCoordinate(coordinate);

        return newPiece;
    }

    @Override
    public Player createPlayer(String playerName, RoleType roleType)
    {
        return null;
    }

    private void initPieceCreationCommands()
    {
        this.creatorCommands.put(CharacterType.BAIDU, () ->
                 new RangeA(new BaiduCharacter(new CommunismRole(new SimplePiece()))));

        this.creatorCommands.put(CharacterType.FACEBOOK, () ->
                new RangeB(new FacebookCharacter(new CapitalismRole(new SimplePiece()))));

        this.creatorCommands.put(CharacterType.GOOGLE, () ->
                new RangeA(new GoogleCharacter(new CapitalismRole(new SimplePiece()))));

        this.creatorCommands.put(CharacterType.TWITTER, () ->
                new RangeC(new TwitterCharacter(new CapitalismRole(new SimplePiece()))));

        this.creatorCommands.put(CharacterType.WECHAT, () ->
                new RangeB(new WeChatCharacter(new CommunismRole(new SimplePiece()))));

        this.creatorCommands.put(CharacterType.WEIBO, () ->
                new RangeC(new WeiboCharacter(new CommunismRole(new SimplePiece()))));
    }
}

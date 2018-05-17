package models.factory;

import models.coordinate.Coordinate;
import models.factory.command.PieceCreator;
import models.piece.Piece;
import models.piece.SimplePiece;
import models.piece.decorators.style.characters.*;
import models.piece.decorators.style.roles.CapitalismRule;
import models.piece.decorators.style.roles.CommunismRole;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

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

    public PieceFactory(int boardSize)
    {
        super(boardSize);
        this.creatorCommands = new HashMap<>();
        this.initPieceCreationCommands();
    }

    @Override
    public Coordinate createCoordinate()
    {
        return null;
    }

    @Override
    public Coordinate createCoordinate(int x, int y)
    {
        return null;
    }

    @Override
    public Piece createPiece(CharacterType characterType, Coordinate coordinate)
    {
        Piece newPiece = this.creatorCommands.get(characterType).createPiece();
        newPiece.setCoordinate(coordinate);

        return newPiece;
    }

    private void initPieceCreationCommands()
    {
        this.creatorCommands.put(CharacterType.BAIDU, () ->
                new CommunismRole(new BaiduCharacter(new SimplePiece())));

        this.creatorCommands.put(CharacterType.FACEBOOK, () ->
                new CapitalismRule(new FacebookCharacter(new SimplePiece())));

        this.creatorCommands.put(CharacterType.GOOGLE, () ->
                new CapitalismRule(new GoogleCharacter(new SimplePiece())));

        this.creatorCommands.put(CharacterType.TWITTER, () ->
                new CapitalismRule(new TwitterCharacter(new SimplePiece())));

        this.creatorCommands.put(CharacterType.WECHAT, () ->
                new CommunismRole(new WeChatCharacter(new SimplePiece())));

        this.creatorCommands.put(CharacterType.WEIBO, () ->
                new CommunismRole(new WeiboCharacter(new SimplePiece())));
    }
}

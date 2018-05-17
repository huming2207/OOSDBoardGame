package models.board.factory;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.SimplePiece;
import models.piece.decorators.style.characters.*;
import models.piece.decorators.style.roles.CapitalismRule;
import models.piece.decorators.style.roles.CommunismRole;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

public class PieceFactory extends AbstractBoardFactory
{
    public PieceFactory(int boardSize)
    {
        super(boardSize);
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
        Piece piece = new SimplePiece(coordinate);

        switch (characterType)
        {
            case BAIDU:
            {
                piece = new CommunismRole(new BaiduCharacter(piece));
                break;
            }

            case WEIBO:
            {
                piece = new CommunismRole(new WeiboCharacter(piece));
                break;
            }

            case GOOGLE:
            {
                piece = new CapitalismRule(new GoogleCharacter(piece));
                break;
            }

            case WECHAT:
            {
                piece = new CommunismRole(new WeChatCharacter(piece));
                break;
            }

            case TWITTER:
            {
                piece = new CapitalismRule(new TwitterCharacter(piece));
                break;
            }

            case FACEBOOK:
            {
                piece = new CapitalismRule(new FacebookCharacter(piece));
                break;
            }
        }

        return piece;
    }
}

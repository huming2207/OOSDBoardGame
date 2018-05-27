package models.factory;

import com.google.java.contract.Ensures;
import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;
import models.player.CapitalismPlayer;
import models.player.CommunismPlayer;
import models.player.Player;

public class PlayerFactory implements BoardFactory
{

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
    public Piece createPiece(CharacterType characterType, Coordinate coordinate)
    {
        return null;
    }

    @Override
    @Ensures("result != null")
    public Player createPlayer(String playerName, RoleType roleType)
    {
        if(roleType == RoleType.COMMUNISM_PIECE) {
            return new CommunismPlayer(playerName);
        } else {
            return new CapitalismPlayer(playerName);
        }
    }
}

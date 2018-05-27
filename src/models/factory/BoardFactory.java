package models.factory;

import models.coordinate.Coordinate;
import models.piece.Piece;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;
import models.player.Player;

public interface BoardFactory
{
    /**
     * Create a coordinate with a random axis
     * @return new coordinate
     */
    Coordinate createCoordinate(int boardSize);

    /**
     * Create a coordinate with specified axis
     * @param x x-axis value
     * @param y y-axis value
     * @return new coordinate
     */
    Coordinate createCoordinate(int x, int y);

    /**
     * Create a new piece with specified type and coordinate
     * @param characterType Character type, e.g. WeChat or Facebook
     * @param coordinate Coordinate of this piece
     * @return new piece
     */
    Piece createPiece(CharacterType characterType, Coordinate coordinate);

    /**
     * Create a new player with specified role type and name
     * @param playerName Player's nick name
     * @param roleType Role type
     * @return new player
     */
    Player createPlayer(String playerName, RoleType roleType);
}

package helpers;

import com.google.java.contract.Ensures;
import models.coordinate.Coordinate;
import models.piece.*;
import models.piece.decorators.ranges.RangeA;
import models.piece.decorators.ranges.RangeB;
import models.piece.decorators.ranges.RangeC;
import models.piece.decorators.style.characters.*;
import models.piece.decorators.style.roles.CapitalismRule;
import models.piece.decorators.style.roles.CommunismRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class PieceFactory
{
    /**
     * Create a random piece list for game logic
     * @param boardX Size of X axis in the board, e.g. for a 8x8 board, boardX should be 8
     * @param boardY Size of Y axis in the board, e.g. for a 8x8 board, boardY should be 8
     * @return Collection of 6 pieces
     */
    @Ensures("result.size() == 6")
    public static Collection<Piece> createRandomPieceList(int boardX, int boardY)
    {
        Collection<Piece> pieceList = new ArrayList<>();
        LinkedList<Coordinate> coordinates = createRandomCoordinateQueue(boardX, boardY);

        pieceList.add(new CommunismRole(new RangeA(new BaiduCharacter(new SimplePiece(coordinates.pop())))));
        pieceList.add(new CapitalismRule(new RangeB(new FacebookCharacter(new SimplePiece(coordinates.pop())))));
        pieceList.add(new CapitalismRule(new RangeA(new GoogleCharacter(new SimplePiece(coordinates.pop())))));
        pieceList.add(new CapitalismRule(new RangeC(new TwitterCharacter(new SimplePiece(coordinates.pop())))));
        pieceList.add(new CommunismRole(new RangeB(new WeChatCharacter(new SimplePiece(coordinates.pop())))));
        pieceList.add(new CommunismRole(new RangeC(new WeiboCharacter(new SimplePiece(coordinates.pop())))));

        return pieceList;
    }

    /**
     * Create a queue of random coordinate
     * @param boardX Size of X axis in the board, e.g. for a 8x8 board, boardX should be 8
     * @param boardY Size of Y axis in the board, e.g. for a 8x8 board, boardY should be 8
     * @return linked List (queue) of random coordinates
     */
    @Ensures("result.size() == 6")
    private static LinkedList<Coordinate> createRandomCoordinateQueue(int boardX, int boardY)
    {
        LinkedList<Coordinate> coordinateList = new LinkedList<>();

        // Only stop looping when coordinate is enough (6 for now)
        while (coordinateList.size() < 6) {

            int posX = ThreadLocalRandom.current().nextInt(0, boardX);
            int posY = ThreadLocalRandom.current().nextInt(0, boardY);

            if (coordinateList.size() < 1) {
                coordinateList.push(new Coordinate(posX, posY));
            } else {
                // Remove the same coordinate and try again later
                if (coordinateList.contains(new Coordinate(posX, posY))) coordinateList.pop();
                coordinateList.push(new Coordinate(posX, posY));
            }

        }

        return coordinateList;
    }
}

package helpers;

import models.game.Coordinate;
import models.game.piece.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class PieceFactory
{
    /**
     * Create a random piece list for game logic
     * @return Collection of 6 pieces
     */
    public static Collection<Piece> createRandomPieceList()
    {
        Collection<Piece> pieceList = new ArrayList<>();
        LinkedList<Coordinate> coordinates = createRandomCoordinateArray();

        pieceList.add(new BaiduPiece(coordinates.pop()));
        pieceList.add(new FacebookPiece(coordinates.pop()));
        pieceList.add(new GooglePiece(coordinates.pop()));
        pieceList.add(new TwitterPiece(coordinates.pop()));
        pieceList.add(new WeChatPiece(coordinates.pop()));
        pieceList.add(new WeiboPiece(coordinates.pop()));

        return pieceList;
    }

    /**
     * Create a queue of random coordinate
     * @return linked List (queue) of random coordinates
     */
    private static LinkedList<Coordinate> createRandomCoordinateArray()
    {
        LinkedList<Coordinate> coordinateList = new LinkedList<>();

        // Only stop looping when coordinate is enough (6 for now)
        while (coordinateList.size() < 6) {

            int posX = ThreadLocalRandom.current().nextInt(0, 8);
            int posY = ThreadLocalRandom.current().nextInt(0, 8);

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

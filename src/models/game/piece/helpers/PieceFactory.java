package models.game.piece.helpers;

import models.game.Coordinate;
import models.game.piece.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PieceFactory
{
    public static List<Piece> createRandomPieceList()
    {
        List<Piece> pieceList = new ArrayList<>();
        LinkedList<Coordinate> coordinates = createRandomCoordinateArray();

        pieceList.add(new BaiduPiece(coordinates.pop()));
        pieceList.add(new FacebookPiece(coordinates.pop()));
        pieceList.add(new GooglePiece(coordinates.pop()));
        pieceList.add(new TwitterPiece(coordinates.pop()));
        pieceList.add(new WeChatPiece(coordinates.pop()));
        pieceList.add(new WeiboPiece(coordinates.pop()));

        return pieceList;
    }

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

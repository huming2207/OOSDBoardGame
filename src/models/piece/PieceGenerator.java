package models.piece;

import models.board.factory.AbstractBoardFactory;
import models.board.factory.CoordinateFactory;
import models.board.factory.PieceFactory;
import models.coordinate.Coordinate;
import models.piece.type.CharacterType;

import java.io.*;
import java.util.*;

/**
 * Piece generator class
 *
 * It generates new pieces in the beginning. When controllers need the piece, it clones and return a new one.
 *
 * This class uses Prototype pattern, ref:
 *  https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
 *  https://en.wikipedia.org/wiki/Prototype_pattern
 *
 * @author Ming Hu (s3554025)
 */
public class PieceGenerator
{
    private HashMap<CharacterType, Piece> pieceMap;
    private int boardSize;

    public PieceGenerator(int boardSize)
    {
        this.pieceMap = new HashMap<>();
        this.boardSize = boardSize;
    }

    public void generateNewPieces()
    {
        AbstractBoardFactory pieceFactory = new PieceFactory(this.boardSize);
        AbstractBoardFactory coordinateFactory = new CoordinateFactory(this.boardSize);

        // Iterate through all types of characters and add them into the piece map
        EnumSet.allOf(CharacterType.class).forEach(characterType ->{

            Coordinate coordinate;

            do {
                coordinate = coordinateFactory.createCoordinate();
            } while (checkExistingCoordinate(coordinate));

            this.pieceMap.put(characterType, pieceFactory.createPiece(characterType, coordinate));
        });
    }

    public Piece getPieceByCharacterType(CharacterType type)
    {
        return this.pieceMap.get(type).clone();
    }

    public List<Piece> getPieceList()
    {
        List<Piece> pieceList = new ArrayList<>();

        for(Piece piece : this.pieceMap.values()) {
            pieceList.add(piece.clone());
        }

        return pieceList;
    }

    public static Piece deepClone(Piece originalPiece) throws IOException, ClassNotFoundException
    {
        // A reasonable way to do deep cloning for Piece object
        // Simply serialize the Piece to byte array and convert it back to Piece object
        // Ref: https://stackoverflow.com/questions/64036/how-do-you-make-a-deep-copy-of-an-object-in-java
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Covert the Piece object to byte[]
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(originalPiece);
        objectOutputStream.flush();
        objectOutputStream.close();
        byte[] byteBuffer = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();

        // ...then covert it back to a brand new Piece
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer);
        return (Piece) new ObjectInputStream(byteArrayInputStream).readObject();
    }

    private boolean checkExistingCoordinate(Coordinate coordinate)
    {
        for(Piece piece : pieceMap.values()) {
            if(piece.getCoordinate().getPosX() == coordinate.getPosX() &&
                    piece.getCoordinate().getPosY() == coordinate.getPosY())
            {
                return true;
            }
        }

        return false;
    }
}

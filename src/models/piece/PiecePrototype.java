package models.piece;

import models.coordinate.Coordinate;
import models.factory.AbstractBoardFactory;
import models.factory.CoordinateFactory;
import models.factory.PieceFactory;
import models.piece.type.CharacterType;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

/**
 * Piece prototype generator class
 *
 * It generates new pieces in the beginning. When controllers need the piece, it clones and return a new one.
 *
 * This class uses Prototype pattern, ref:
 *  https://www.tutorialspoint.com/design_pattern/prototype_pattern.htm
 *  https://en.wikipedia.org/wiki/Prototype_pattern
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class PiecePrototype
{
    private HashMap<CharacterType, Piece> pieceMap;
    private int boardSize;

    /**
     * Constructor of Piece Prototype Generator
     * @param boardSize Board size of a certain board
     */
    public PiecePrototype(int boardSize)
    {
        this.pieceMap = new HashMap<>();
        this.boardSize = boardSize;
        this.generateNewPieces();
    }

    /**
     *(Re)generate new pieces for the prototype map.
     * If a new random coordinate is necessary, then this method should be used.
     */
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

    /**
     * Get one piece with specified character type
     * Returned piece will be deep cloned, i.e. not the same piece as the one in the prototype map.
     *
     * @param  type Character type
     * @return cloned piece
     */
    public Piece getPiece(CharacterType type)
    {
        return this.pieceMap.get(type).clone();
    }

    /**
     * Get a full 6 piece list
     * Returned pieces will be deep cloned, i.e. not the same pieces as those in the prototype map.
     *
     * @return list with cloned pieces
     */
    public List<Piece> getPieceList()
    {
        List<Piece> pieceList = new ArrayList<>();

        for(Piece piece : this.pieceMap.values()) {
            pieceList.add(piece.clone()); // Neeed to be cloned
        }

        return pieceList;
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

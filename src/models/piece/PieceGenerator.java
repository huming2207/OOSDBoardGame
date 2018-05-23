package models.piece;

import models.coordinate.Coordinate;
import models.factory.*;
import models.piece.type.CharacterType;
import models.piece.type.RoleType;

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
public class PieceGenerator
{
    private HashMap<CharacterType, Piece> pieceMap;
    private int capitalismScore = 0;
    private int communismScore = 0;

    /**
     * Constructor of Piece Prototype Generator
     * @param boardSize Board size of a certain board
     */
    public PieceGenerator(int boardSize, int pieceCount)
    {
        this.pieceMap = new HashMap<>();
        this.generateNewPieces(boardSize, pieceCount);
    }

    /**
     *(Re)generate new pieces for the prototype map.
     * If a new random coordinate is necessary, then this method should be used.
     */
    private void generateNewPieces(int boardSize, int pieceCount)
    {
        AbstractBoardFactory pieceFactory = FactoryGenerator.getFactory(FactoryType.PIECE, boardSize);
        AbstractBoardFactory coordinateFactory = FactoryGenerator.getFactory(FactoryType.COORDINATE, boardSize);

        // Iterate through all types of characters and add them into the piece map
        EnumSet.allOf(CharacterType.class).stream().limit(pieceCount).forEach(characterType ->{

            Coordinate coordinate;

            // Make sure no conflicts on coordinates
            do {
                assert coordinateFactory != null;
                coordinate = coordinateFactory.createCoordinate();
            } while (checkExistingCoordinate(coordinate));

            // Add into piece map
            assert pieceFactory != null;
            Piece piece = pieceFactory.createPiece(characterType, coordinate);
            this.pieceMap.put(characterType, piece);

            // Count the score
            if(piece.getRoleType() == RoleType.COMMUNISM_PIECE) {
                this.communismScore += piece.getMark();
            } else {
                this.capitalismScore += piece.getMark();
            }
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


    public int getCapitalismScore()
    {
        return capitalismScore;
    }

    public int getCommunismScore()
    {
        return communismScore;
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

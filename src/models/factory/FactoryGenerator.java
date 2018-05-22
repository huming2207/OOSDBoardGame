package models.factory;

public class FactoryGenerator
{
    public static AbstractBoardFactory getFactory(FactoryType type, int boardSize)
    {
        switch (type)
        {
            case PIECE: return new PieceFactory(boardSize);
            case COORDINATE: return new CoordinateFactory(boardSize);
            default: return null;
        }
    }
}

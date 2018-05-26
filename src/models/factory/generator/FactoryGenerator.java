package models.factory.generator;

import models.factory.AbstractBoardFactory;
import models.factory.CoordinateFactory;
import models.factory.PieceFactory;
import models.factory.PlayerFactory;
import models.factory.type.FactoryType;

public class FactoryGenerator
{
    public static AbstractBoardFactory getFactory(FactoryType type)
    {
        switch (type)
        {
            case PIECE: return new PieceFactory();
            case COORDINATE: return new CoordinateFactory();
            case PLAYER: return new PlayerFactory();
            default: return new PieceFactory(); // Place holder for shutting up compilers/IDEs
        }
    }
}

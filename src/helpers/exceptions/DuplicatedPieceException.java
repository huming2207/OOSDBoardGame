package helpers.exceptions;

import helpers.logger.AbstractReaction;
import helpers.logger.ReactionLevel;
import helpers.logger.ReactionManager;

public class DuplicatedPieceException extends Exception
{
    public DuplicatedPieceException(String message)
    {
        super(message);
    }
}

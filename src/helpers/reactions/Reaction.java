package helpers.reactions;

/**
 * Logger helper for Board Game
 *
 * It uses Chain of Responsibility pattern, each level behaves differently.
 *
 * @author Ming Hu
 * @since Assignment 2
 */
public abstract class Reaction
{
    protected ReactionLevel reactionLevel;
    private Reaction nextLevelLogger;

    public void setNextLevelLogger(Reaction nextLevelLogger)
    {
        this.nextLevelLogger = nextLevelLogger;
    }

    public void handleReaction(ReactionLevel level, String message)
    {
        if(this.reactionLevel.getLevel() <= level.getLevel()) {
            this.performReaction(message);
        } else if(this.nextLevelLogger != null) {
            this.nextLevelLogger.handleReaction(level, message);
        }
    }

    protected abstract void performReaction(String message);
}

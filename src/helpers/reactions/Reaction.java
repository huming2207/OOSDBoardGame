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
    private Reaction nextReaction;

    /**
     * Set next level of reaction
     * @param nextReaction next level of reaction
     */
    public void setNextReaction(Reaction nextReaction)
    {
        this.nextReaction = nextReaction;
    }

    /**
     * Handle reaction with specific level
     * @param level reaction level
     * @param message message string
     */
    public void handleReaction(ReactionLevel level, String message)
    {
        if(this.reactionLevel.getLevel() <= level.getLevel()) {
            this.performReaction(message);
        } else if(this.nextReaction != null) {
            this.nextReaction.handleReaction(level, message);
        }
    }

    protected abstract void performReaction(String message);
}

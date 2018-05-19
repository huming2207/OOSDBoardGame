package helpers.logger;

/**
 * Reaction level enumeration class
 * Separate the level of reaction
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public enum ReactionLevel
{
    DEBUG(0), WARN(1), CRASH(2);

    private int level;

    private ReactionLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return level;
    }
}

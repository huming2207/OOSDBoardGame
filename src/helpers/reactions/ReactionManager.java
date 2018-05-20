package helpers.reactions;

public class ReactionManager
{
    public static AbstractReaction getReaction()
    {
        AbstractReaction crashReaction = new CrashReaction();
        AbstractReaction warningReaction = new WarningReaction();
        AbstractReaction debugReaction = new DebugReaction();

        crashReaction.setNextLevelLogger(warningReaction);
        warningReaction.setNextLevelLogger(debugReaction);

        return crashReaction;
    }
}

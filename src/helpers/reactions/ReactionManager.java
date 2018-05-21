package helpers.reactions;

public class ReactionManager
{
    public static Reaction getReaction()
    {
        Reaction crashReaction = new CrashReaction();
        Reaction warningReaction = new WarningReaction();
        Reaction debugReaction = new DebugReaction();

        crashReaction.setNextLevelLogger(warningReaction);
        warningReaction.setNextLevelLogger(debugReaction);

        return crashReaction;
    }
}

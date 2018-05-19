package helpers.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Crash logger: Crashes app with non-zero exit code
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class CrashReaction extends AbstractReaction
{
    public CrashReaction()
    {
        this.reactionLevel = ReactionLevel.CRASH;
    }

    @Override
    protected void performReaction(String message)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.err.println(String.format("[ERROR][%s]: will exit!", dateFormatter.format(new Date())));

        System.exit(1);
    }
}

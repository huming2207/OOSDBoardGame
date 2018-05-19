package helpers.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Debug logger: does nothing but just log to console
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class DebugReaction extends AbstractReaction
{
    public DebugReaction()
    {
        this.reactionLevel = ReactionLevel.DEBUG;
    }

    @Override
    protected void performReaction(String message)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println(String.format("[DEBUG][%s]: %s", dateFormatter.format(new Date()), message));
    }
}

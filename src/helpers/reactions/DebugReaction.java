package helpers.reactions;

import com.google.java.contract.Requires;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Debug reactions: does nothing but just log to console
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class DebugReaction extends Reaction
{
    public DebugReaction()
    {
        this.reactionLevel = ReactionLevel.DEBUG;
    }

    @Override
    @Requires("!message.isEmpty()")
    protected void performReaction(String message)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println(String.format("[DEBUG][%s]: %s", dateFormatter.format(new Date()), message));
    }
}

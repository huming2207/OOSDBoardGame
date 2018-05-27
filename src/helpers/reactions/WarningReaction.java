package helpers.reactions;

import com.google.java.contract.Requires;
import javafx.scene.control.Alert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Warning reactions: shows a warning dialog box with logged info
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class WarningReaction extends Reaction
{
    public WarningReaction()
    {
        this.reactionLevel = ReactionLevel.WARN;
    }

    @Override
    @Requires("!message.isEmpty()")
    protected void performReaction(String message)
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        System.out.println(String.format("[WARNING][%s]: %s", dateFormatter.format(new Date()), message));

        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
}

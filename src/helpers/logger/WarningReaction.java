package helpers.logger;

import javafx.scene.control.Alert;

/**
 * Warning logger: shows a warning dialog box with logged info
 *
 * @author Ming Hu (s3554025)
 * @since Assignment 2
 */
public class WarningReaction extends AbstractReaction
{
    public WarningReaction()
    {
        this.reactionLevel = ReactionLevel.WARN;
    }

    @Override
    protected void performReaction(String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
}

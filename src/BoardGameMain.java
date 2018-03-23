import controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BoardGameMain extends Application
{
    public static void main(String[] args)
    {
        Application.launch(BoardGameMain.class, args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("OOSD Board Game - Group G3");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/Home.fxml"));

            // Set the scene by getting the Parent scene from FXMLLoader
            primaryStage.setScene(new Scene(loader.load()));

            // Get the controller from loader, then set the object map to it.
            // We can also use loader.setController() but we can't set "fx:controller" parameter in FXML any more,
            // and it will result to some issues with FXML editor in Intellij IDEA.
            HomeController controller = loader.getController();
            controller.setControlMap(loader.getNamespace());

            primaryStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

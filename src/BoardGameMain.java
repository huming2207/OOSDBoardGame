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

            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

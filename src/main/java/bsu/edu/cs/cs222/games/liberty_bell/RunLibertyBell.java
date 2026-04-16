package bsu.edu.cs.cs222.games.liberty_bell;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunLibertyBell extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("/fxmls/LibertyBellEntryview.fxml")
        );
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle(" Liberty Bell Slots");
        stage.setScene(scene);
        stage.show();
    }
}
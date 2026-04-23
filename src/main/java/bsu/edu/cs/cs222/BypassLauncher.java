package bsu.edu.cs.cs222;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Debug terminal that bypasses the launcher entirely for better testing
 *
 * @author Holden Hankins
 */
public class BypassLauncher extends Application {
    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/menues/main_menu.fxml"));
            Parent root = mmLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            MainMenuController mmController = mmLoader.getController();
            mmController.setUser(new User("Test", "test", 5000));

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package bsu.edu.cs.cs222;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.menues.MainMenuController;
import bsu.edu.cs.cs222.menues.leaderboard.Leaderboard;
import bsu.edu.cs.cs222.menues.leaderboard.RunLeaderboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class BypassLauncher extends Application {
    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader mmLoader = new FXMLLoader(getClass().getResource("/fxmls/main_menu.fxml"));
            Parent root = mmLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            MainMenuController mmController = mmLoader.getController();
            mmController.setUser(new User("Test", "test", 5000));

            stage.setScene(scene);
            stage.show();

            /*
            // Legal Notices
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/legal_stuff.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Splash Screen
            Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/splash_screen.fxml")));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> scene.setRoot(root2));
                }
            }, 2000);

            // Title Screen
            Parent root3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/launch_page.fxml")));
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> scene.setRoot(root3));
                }
            }, 4000);
             */

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
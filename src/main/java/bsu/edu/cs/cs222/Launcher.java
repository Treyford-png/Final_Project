package bsu.edu.cs.cs222;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Legal Notices
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/legal_stuff.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Splash Screen
            Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/splash_screen.fxml")));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> scene.setRoot(root2));
                }
            }, 2000);

            // Title Screen
            Parent root3 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxmls/menues/launch_page.fxml")));
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> scene.setRoot(root3));
                }
            }, 4000);

        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
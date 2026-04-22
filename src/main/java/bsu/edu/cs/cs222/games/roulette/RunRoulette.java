package bsu.edu.cs.cs222.games.roulette;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Runs an FXML version of roulette purely for testing
 *
 * @author Treyford Mercer
 */
public class RunRoulette extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxmls/games/roulette-view.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package bsu.edu.cs.cs222.games.roulette;

import bsu.edu.cs.cs222.characters.User;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerRoulette {

    @FXML private ImageView wheelImage;
    @FXML private Label pointsLabel;

    private Roulette roulette;

    private int points = 1000;
    private int betAmount = 0;

    private String colorGuess = "";
    private String numberGuess = "x";

    private enum BetMode {
        NUMBER_COLOR,
        COLOR_ONLY
    }

    private BetMode betMode = null;

    private final String[] redNumbers = {
            "1","3","5","7","9","12","14","16","18","19",
            "21","23","25","27","30","32","34","36"
    };

    private final String[] blackNumbers = {
            "2","4","6","8","10","11","13","15","17","20",
            "22","24","26","28","29","31","33","35"
    };

    @FXML
    public void initialize() {

        roulette = new Roulette(new User("You", "test", 0));

        Image image = new Image(
                getClass().getResource("/fxmls/roulette.png").toExternalForm()
        );
        wheelImage.setImage(image);

        updatePoints();
    }

    @FXML
    private void betRed() {
        colorGuess = "red";
        showBetModeChoice();
    }

    @FXML
    private void betBlack() {
        colorGuess = "black";
        showBetModeChoice();
    }

    @FXML
    private void colorOnlyBet() {
        betMode = BetMode.COLOR_ONLY;
        numberGuess = "x";

        askBet();
    }

    private void showBetModeChoice() {

        ButtonType numberBet = new ButtonType("Number + Color");
        ButtonType colorBet = new ButtonType("Color Only");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose Bet Type");
        alert.setHeaderText("Select betting mode");
        alert.setContentText("How do you want to bet?");

        alert.getButtonTypes().setAll(numberBet, colorBet, cancel);

        alert.showAndWait().ifPresent(choice -> {

            if (choice == numberBet) {
                betMode = BetMode.NUMBER_COLOR;
                showNumberPicker(colorGuess.equals("red") ? redNumbers : blackNumbers);
            }

            else if (choice == colorBet) {
                betMode = BetMode.COLOR_ONLY;
                colorOnlyBet();
            }
        });
    }

    private void showNumberPicker(String[] numbers) {

        ChoiceDialog<String> dialog = new ChoiceDialog<>(numbers[0], numbers);
        dialog.setTitle("Pick Number");
        dialog.setHeaderText("Choose a " + colorGuess + " number");
        dialog.setContentText("Number:");

        dialog.showAndWait().ifPresent(choice -> {
            numberGuess = choice;
            askBet();
        });
    }

    private void askBet() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Place Bet");
        dialog.setHeaderText("You have " + points + " points");
        dialog.setContentText("Enter bet amount:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                betAmount = Integer.parseInt(input);
            } catch (Exception e) {
                betAmount = 0;
            }
        });
    }

    @FXML
    private void handleSpin() {

        if (betAmount <= 0 || betAmount > points || betMode == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Bet");
            alert.setContentText("Complete your bet before spinning.");
            alert.showAndWait();
            return;
        }

        RotateTransition rotate = new RotateTransition(Duration.seconds(4), wheelImage);
        rotate.setByAngle(1440);

        rotate.setOnFinished(e -> {

            String number = roulette.spinWheel();
            String color = roulette.getColor(number);

            int result = roulette.calcResults(
                    number,
                    numberGuess,
                    colorGuess,
                    betAmount
            );

            points += result;
            updatePoints();

            String outcome;
            if (result == betAmount * 7) {
                outcome = "BIG WIN (x7)";
            } else if (result == betAmount * 2) {
                outcome = "WIN (x2)";
            } else {
                outcome = "LOSS";
            }

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxmls/result-viewer-roulette.fxml")
                );

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                ResultController controller = loader.getController();
                controller.setData(
                        number,
                        color,
                        outcome,
                        points
                );

                stage.setTitle("Spin Result");
                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            betAmount = 0;
            colorGuess = "";
            numberGuess = "x";
            betMode = null;
        });

        rotate.play();
    }

    private void updatePoints() {
        pointsLabel.setText("Points: " + points);
    }
}
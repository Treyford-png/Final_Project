package bsu.edu.cs.cs222.games.roulette;

import bsu.edu.cs.cs222.characters.User;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class ControllerRoulette {

    @FXML private ImageView wheelImage;
    @FXML private Label pointsLabel;
    @FXML private Label resultLabel;
    @FXML private Label betLabel;
    @FXML private Spinner<Integer> setPoints;

    private Roulette roulette;
    User user;
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

    public void setUser(User user) {
        this.user = user;
        points = user.getPoints();
        setPoints.setPromptText(String.valueOf(points));
    }

    @FXML
    public void initialize() {

        roulette = new Roulette(new User("You", "test", 0));

        Image image = new Image(
                getClass().getResource("/images/wheel_pixel.jpg").toExternalForm()
        );
        wheelImage.setImage(image);

        //updatePoints();
    }

    @FXML
    private void betRed() {
        colorGuess = "red";
    }

    @FXML
    private void betBlack() {
        colorGuess = "black";
    }

    @FXML
    private void colorOnlyBet() {
        betMode = BetMode.COLOR_ONLY;
        numberGuess = "x";
    }

    public void setBetToButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String text = clickedButton.getText();
        setTextColor(betLabel, text);
        betLabel.setText(text);
    }

    public void getBet(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String text = clickedButton.getText();
        if (text.equals("Red") || text.equals("Black")) {
            colorGuess = text.toLowerCase();
        } else {
            numberGuess = text;
        }
    }

    @FXML
    private void handleSpin() {

        betAmount = 50;
        betMode = BetMode.COLOR_ONLY;
        colorGuess = "red";
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


            resultLabel.setText(number);

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

    public void setTextColor(Label label, String text) {
        // Handles colors
        if (text.equals("Red")) {
            label.setTextFill(Color.web("#BD203F"));
            return;
        } else if (text.equals("Black")) {
            label.setTextFill(BLACK);
            return;
        }

        // Handles numbers
        boolean isRed = false;
        for (String str : redNumbers) {
            if (str.equals(text)) {
                isRed = true;
                break;
            }
        }
        if (isRed) {
            label.setTextFill(Color.web("#BD203F"));
        } else {
            label.setTextFill(BLACK);
        }
    }
}
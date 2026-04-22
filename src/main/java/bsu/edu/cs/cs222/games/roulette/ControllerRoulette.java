package bsu.edu.cs.cs222.games.roulette;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static javafx.scene.paint.Color.BLACK;

public class ControllerRoulette {

    @FXML private ImageView wheelImage;
    @FXML private Label pointsLabel;
    @FXML private Label resultLabel;
    @FXML private Label betLabel;
    @FXML private Spinner<Integer> setPoints;
    @FXML private Label winsLabel;
    @FXML private Label outcomeLabel;

    private Roulette roulette;
    User user;
    private int points = 1000;
    private int betAmount = 0;
    private String betText;
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
        HelpersFX.setFactory(setPoints, user.getPoints());
        pointsLabel.setText(user.getUsername() + ": " + user.getPoints());
    }

    @FXML
    public void initialize() {
        roulette = new Roulette(new User("placeholder", "placeholder", 0));

        Image image = new Image(
                getClass().getResource("/images/wheel_pixel.jpg").toExternalForm()
        );
        wheelImage.setImage(image);
        betText = "empty";
    }

    public void setBetToButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        betText = clickedButton.getText();
        System.out.println(clickedButton.getText());
        setTextColor(betLabel, betText);
        getBet();
        betLabel.setText(betAmount + "p on " + betText);
    }

    public void getBet() {
        betAmount = setPoints.getValue();
        if (betText.equals("Black") || betText.equals("Red")) {
            numberGuess = "x";
            colorGuess = betText.toLowerCase();
        } else {
            numberGuess = betText;
            colorGuess = "x";
        }

        System.out.println(betText);
    }

    @FXML
    private void handleSpin() {
        getBet();
        System.out.println(numberGuess + " " + colorGuess);

        RotateTransition rotate = new RotateTransition(Duration.seconds(4), wheelImage);
        rotate.setByAngle(1440);

        rotate.setOnFinished(e -> {

            String number = roulette.spinWheel();
            setTextColor(resultLabel, number);
            resultLabel.setText(number);

            int points = roulette.calcResults(number, numberGuess, colorGuess, betAmount);
            System.out.println(points);

            if (points <= 0) {
                outcomeLabel.setText("LOSS");
                winsLabel.setText(String.valueOf(points));
            } else {
                if (points == betAmount * 2) {
                    outcomeLabel.setText("WIN (x2)");;
                } else {
                    outcomeLabel.setText("BIG WIN (x7)");
                }
                winsLabel.setText("+" + points);
            }

            updatePoints(points);

            betAmount = 0;
            colorGuess = "";
            numberGuess = "x";
            betMode = null;
        });

        rotate.play();
    }

    private void updatePoints(int points) {
        user.addPoints(points);
        pointsLabel.setText(user.getUsername() + ": " + user.getPoints());
    }

    public void setTextColor(Label label, String text) {
        // Handles colors
        if (text.equals("Red")) {
            label.setTextFill(Color.web("#BD203F"));
            return;
        } else if (text.equals("Black")) {
            label.setTextFill(BLACK);
            return;
        } else if (text.equals("0") || text.equals("00")) {
            label.setTextFill(Color.web("#178A69"));
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

    public void exit() throws IOException {
        // Create confirmation panel
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("exit");
        alert.setHeaderText("Do you want to exit roulette?");
        alert.setHeaderText("All data will be saved?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            HelpersFX.gotoMainMenu(user, (Stage) winsLabel.getScene().getWindow());
        }
    }
}
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
import java.util.Objects;

import static javafx.scene.paint.Color.BLACK;

/**
 * Runs JavaFX for Roulette game
 *
 * @author Treyford Mercer
 * @author Holden Hankins
 */
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
    private int points;
    private int betAmount = 0;
    private String betText;
    private String colorGuess = "";
    private String numberGuess = "x";

    private final String[] redNumbers = {
            "1","3","5","7","9","12","14","16","18","19",
            "21","23","25","27","30","32","34","36"
    };

    private final String[] blackNumbers = {
            "2","4","6","8","10","11","13","15","17","20",
            "22","24","26","28","29","31","33","35"
    };

    /**
     * Once user is set, set us bottom bar and points spinner
     * @param user player
     */
    public void setUser(User user) {
        this.user = user;
        points = user.getPoints();
        HelpersFX.setFactory(setPoints, user.getPoints());
        pointsLabel.setText(user.getUsername() + ": " + user.getPoints());
    }

    /**
     * Creates the wheel that will be spun later as well as a placeholder for the roulette game
     */
    @FXML
    public void initialize() {
        roulette = new Roulette(new User("placeholder", "placeholder", 0));

        Image image = new Image(
                Objects.requireNonNull(getClass().getResource("/images/wheel_pixel.jpg")).toExternalForm()
        );
        wheelImage.setImage(image);
        betText = "empty";
    }

    /**
     * Sets bet to whatever button is clicked
     * @param event which button did the user click
     */
    public void setBetToButton(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        betText = clickedButton.getText();
        setTextColor(betLabel, betText); // Dynamic color setting
        getBet();
        betLabel.setText(betAmount + "p on " + betText);
    }

    /**
     * Gets the bet from input section and sets betAmount, numberGuess, and colorGuess
     * If bet is a number, number is set and color is wiped
     * If bet is a color, color is set and number is wiped
     */
    public void getBet() {
        betAmount = setPoints.getValue();
        if (betText.equals("Black") || betText.equals("Red")) { // Color bet
            numberGuess = "x";
            colorGuess = betText.toLowerCase();
        } else { // Number bet
            numberGuess = betText;
            colorGuess = "x";
        }
    }

    /**
     * Spins the wheel
     */
    @FXML
    private void handleSpin() {
        getBet();

        // rotation effect
        RotateTransition rotate = new RotateTransition(Duration.seconds(4), wheelImage);
        rotate.setByAngle(1440);

        rotate.setOnFinished(e -> { // Runs while wheel is rotating

            // Sets label
            String number = roulette.spinWheel();
            setTextColor(resultLabel, number);
            resultLabel.setText(number);

            // Calculates points and informs player
            int points = roulette.calcResults(number, numberGuess, colorGuess, betAmount);
            displayResult(points);
            updatePoints(points);

            // Resets for another spin
            betAmount = 0;
            colorGuess = "";
            numberGuess = "x";
        });

        // Shows wheel rotation
        rotate.play();
    }

    /**
     * Saves points to user
     * @param points new points to add
     */
    private void updatePoints(int points) {
        user.addPoints(points);
        pointsLabel.setText(user.getUsername() + ": " + user.getPoints());
    }

    /**
     * Compares points to bet to see what type user won, then tells user
     * @param points points won or loss
     */
    public void displayResult(int points) {
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
    }

    /**
     * Dynamically sets color of text to either red or black
     * @param label label to color
     * @param text text to set
     */
    public void setTextColor(Label label, String text) {
        // Handles colors
        switch (text) {
            case "Red" -> {
                label.setTextFill(Color.web("#BD203F"));
                return;
            }
            case "Black" -> {
                label.setTextFill(BLACK);
                return;
            }
            case "0", "00" -> {
                label.setTextFill(Color.web("#178A69"));
                return;
            }
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

    /**
     * Closes the game and returns to main menu
     * @throws IOException catchall
     */
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
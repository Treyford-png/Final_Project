package bsu.edu.cs.cs222.games.liberty_bell;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.helpers.HelpersFX;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Handles the Liberty Bell slot machine graphical interface
 * PAYOUT RULES
 * 3 of a kind - Win the full point value matched symbol.
 * 2 of a kind - Win half the point value of the matched symbols
 * No match - No payout, try again.
 *
 * @author Laura Romero
 */
public class LibertyBellController {

    @FXML
    private Label reel1, reel2, reel3, resultLabel, usernamePointsLabel;

    private LibertyBellMachine machine;
    private User user;

    public LibertyBellController() {
    }

    public void setUser(User user) {
        this.user = user;
        setPointsLabel();
        machine = new LibertyBellMachine();
    }

    /**
     * Using the bet of the player, calculates earning after a wheel is spun
     */
    @FXML
    private void handleSpin() {
        int wager = 50; // Insert a nickle

        user.makeWager(wager);

        // Generates 3 symbols
        LibertyBellSymbols[] result = machine.spin();
        reel1.setText(getEmoji(result[0]));
        reel2.setText(getEmoji(result[1]));
        reel3.setText(getEmoji(result[2]));

        // Generates and adds payout to user
        int payout = machine.calculatePayout(result);

        // Displays payout to user
        if (payout > 0) { // Won
            user.addPoints(payout * 2);
            resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #d2691e; -fx-font-weight: bold;");
            resultLabel.setText("You won " + payout + " points!");
        } else { // Lost
            resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #c2abab; -fx-font-weight: bold;");
            resultLabel.setText(" No luck this time partner!");
        }
        setPointsLabel();
    }

    /**
     * Turns an enum Symbol into the corresponding emoji/icon for the display
     *
     * @param symbol enum
     * @return emoji
     */
    private String getEmoji(LibertyBellSymbols symbol) {
        return switch (symbol) {
            case LIBERTY_BELL -> "🔔";
            case HORSESHOE -> "🧲";
            case DIAMOND -> "💎";
            case SPADE -> "♠️";
            case HEART -> "❤️";
        };
    }

    /**
     * Sets the label at the bottom to show current points
     */
    private void setPointsLabel() {
        usernamePointsLabel.setText(user.getUsername() + " - " + user.getPoints());
    }

    public void back() throws IOException {
        boolean confirm = HelpersFX.confirmExit("the Liberty Bell slot machine?");
        if (confirm) {
            HelpersFX.gotoMainMenu(user, (Stage) reel1.getScene().getWindow());
        }
    }
}
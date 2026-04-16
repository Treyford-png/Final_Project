package bsu.edu.cs.cs222.games.liberty_bell;
import bsu.edu.cs.cs222.characters.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LibertyBellController {

        @FXML private Label reel1;
        @FXML private Label reel2;
        @FXML private Label reel3;
        @FXML private TextField wagerField;
        @FXML private Button spinButton;
        @FXML private Label resultLabel;

        private LibertyBellMachine machine;
        private User user;



        @FXML
        private void handleSpin() {
            String wagerText = wagerField.getText().trim();
            if (wagerText.isEmpty()) {
                resultLabel.setText(" Place your wager partner: ");
                return;
            }

            int wager;
            try {
                wager = Integer.parseInt(wagerText);
            } catch (NumberFormatException e) {
                resultLabel.setText("️ Wager must be a number : ");
                return;
            }

            if (wager <= 0 || wager > user.getPoints()) {
                resultLabel.setText(" Invalid wager amount ");
                return;
            }

            user.makeWager(wager);
            LibertyBellSymbols[] result = machine.spin();

            reel1.setText(getEmoji(result[0]));
            reel2.setText(getEmoji(result[1]));
            reel3.setText(getEmoji(result[2]));

            int payout = machine.calculatePayout(result);
            if (payout > 0) {
                user.addPoints(payout * 2);
                resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #d2691e; -fx-font-weight: bold;");
                resultLabel.setText("You won " + payout + " points!");
            } else {
                resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #c2abab; -fx-font-weight: bold;");
                resultLabel.setText(" No luck this time partner!");
            }
        }

        private String getEmoji(LibertyBellSymbols symbol) {
            return switch (symbol) {
                case LIBERTY_BELL -> "🔔";
                case HORSESHOE -> "🧲";
                case DIAMOND -> "💎";
                case SPADE -> "♠️";
                case HEART -> "❤️";
            };
        }
    }


package bsu.edu.cs.cs222.games.vingt_un;

import bsu.edu.cs.cs222.characters.User;
import bsu.edu.cs.cs222.libraries.cards.CardDeck;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static bsu.edu.cs.cs222.games.vingt_un.HandStatus.NATURAL_21;

public class VUController {
    private User user;
    private VingtUn vu;
    private  VingtUnPlayer player, npc1, npc2;
    private record NPCLabels(Label name, Label total, Label bet, Label cards) {}

    // Bottom panel
    @FXML public Button buttonHit, buttonStand;
    @FXML public Label userHandValue, userPointsLabel;
    // Player
    @FXML public Label userCards, userBetAmount;
    // Left side NPC
    @FXML public Label npcOneTotal, npcOneName, npcOneBet, npcOneCards, handValue1;
    // Right side NPC
    @FXML public Label npcTwoTotal, npcTwoName, npcTwoBet, npcTwoCards, handValue2;

    public VUController() {
        user = new User("placeholder", "placeholder", 0);
    }

    public void setUserVU(User user) throws IOException {
        this.user = user;
        launchBetScreen();
    }

    public void gameTurn() {
        vu.clearHands();
        resetLabels();
        vu.startGame();
        userCards.setText(player.getHand().getOutput());
        userHandValue.setText(String.valueOf(player.getHand().getHandValue()));
        npcOneCards.setText("[?] [?]");
        npcOneBet.setText("(" + npc1.getWager() + "p)");
        npcOneTotal.setText(npc1.getPoints() + "p");

        npcTwoCards.setText("[?] [?]");
        npcTwoBet.setText("(" + npc2.getWager() + "p)");
        npcTwoTotal.setText(npc2.getPoints() + "p");
        unlockButtons();
    }

    public void start() {
        vu = new VingtUn(user);
        setNPCs();
        resetLabels();
        updateBottomBar();
    }

    private void setNPCs() {
        player = vu.getMainPlayer();
        npc1 = vu.getPlayer2();
        npc2 = vu.getPlayer3();
        initNPC(npc1, new NPCLabels(npcOneName, npcOneTotal, npcOneBet, npcOneCards));
        initNPC(npc2, new NPCLabels(npcTwoName, npcTwoTotal, npcTwoBet, npcTwoCards));
    }

    private void initNPC(VingtUnPlayer npc, NPCLabels labels) {
        if (npc.isDealer()) {
            labels.name().setText("(D)" + npc.getName());
        } else {
            labels.name().setText(npc.getName());
        }
        labels.total().setText(npc.getPoints() + "p");
    }

    private void resetLabels() {
        npcOneBet.setText("");
        npcOneCards.setText("");
        npcTwoBet.setText("");
        npcTwoCards.setText("");
        userBetAmount.setText("");
        userCards.setText("");
        userHandValue.setText("00");
        handValue1.setText("");
        handValue2.setText("");

        npcOneTotal.setText(npc1.getPoints() + "p");
        npcTwoTotal.setText(npc2.getPoints() + "p");
    }

    private void updateBottomBar() {
        userPointsLabel.setText(user.getUsername() + " - " + player.getPoints());
    }

    public void playerHit() throws IOException {
        player.hit(vu.getDeck());
        userCards.setText(player.getHand().getOutput());
        int value = player.getHand().getHandValue();
        userHandValue.setText(String.valueOf(value));
        if (value > 21) {
            player.getHand().setValueToZero();
            playerStand();
        }
    }

    public void playerStand() throws IOException {
        lockButtons();
        aiTurns();
        vu.scoreGame();
        endGame();
    }

    public void aiTurns() {
        aiTurn(npc1, npcOneCards, vu.getDeck(), 16);
        aiTurn(npc2, npcTwoCards, vu.getDeck(), 17);
    }

    public void aiTurn(VingtUnPlayer player, Label cards, CardDeck deck, int riskFactor) {
        Hand hand = player.getHand();
        if (hand.getHandValue() == 21) {
            return;
        }

        while (hand.value() < riskFactor && hand.value() != 0) {
            // Deals new card to player to reach riskFactor
            player.hit(deck);
            cards.setText(cards.getText() + " [?]");
            // Handles AI's hand exceeding 21
            if (hand.value() > 21) {
                if (hand.checkForBust()) {
                    hand.setValueToZero();
                    return;
                }
            }
        } // end while
    }

    public void endGame() throws IOException {
        npcOneCards.setText(npc1.getHand().getOutput());
        npcTwoCards.setText(npc2.getHand().getOutput());
        handValue1.setText(String.valueOf(npc1.getHand().getHandValue()));
        handValue2.setText(String.valueOf(npc2.getHand().getHandValue()));
        updateBottomBar();
        npcOneTotal.setText(npc1.getPoints() + "p");
        npcTwoTotal.setText(npc2.getPoints() + "p");
        launchBetScreen();
    }

    public void launchBetScreen() throws IOException {
        FXMLLoader betLoader = new FXMLLoader(getClass().getResource("/fxmls/vu_set_bet.fxml"));
        Parent root = betLoader.load();
        Stage stage2 = new Stage();
        stage2.setTitle("Set Bet");
        stage2.setScene(new Scene(root));
        stage2.show();
        VUBetController betController = betLoader.getController();
        betController.setUser(user);
        betController.setVUController(this);
    }

    public void placeBet(int bet) {
        player.setWager(bet);
        userBetAmount.setText("(" + bet + "p)");
        userPointsLabel.setText(String.valueOf(player.getPoints()));
    }

    private void unlockButtons() {
        buttonHit.setDisable(false);
        buttonStand.setDisable(false);
        userHandValue.setVisible(true);
    }

    private void lockButtons() {
        buttonHit.setDisable(true);
        buttonStand.setDisable(true);
    }

    public void closeGame() {
        vu.endGame();
        Stage stage = (Stage) buttonHit.getScene().getWindow();
        stage.close();
    }
}
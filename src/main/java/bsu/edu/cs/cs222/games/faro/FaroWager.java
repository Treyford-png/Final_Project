package bsu.edu.cs.cs222.games.faro;

public class FaroWager {
    private String card;
    private int amount;

    public FaroWager(String card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public String getCard() {
        return card;
    }

    public int getAmount() {
        return amount;
    }

    public boolean setAmount(int wagerToPlace, FaroPlayer player) {
        if (wagerToPlace >= player.getPoints()) {
            return false;
        }
        amount = wagerToPlace;
        return true;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setAll(String card, int amount) {
        this.card = card;
        this.amount = amount;
    }
}

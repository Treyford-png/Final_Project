package bsu.edu.cs.cs222.games.faro;

public class FaroWager {
    private String card;
    private int pointsPlaced;

    public FaroWager(String card, int pointsPlaced) {
        this.card = card;
        this.pointsPlaced = pointsPlaced;
    }

    public String getCard() {
        return card;
    }

    public int getPointsPlaced() {
        return pointsPlaced;
    }

    public boolean setWager(int wagerToPlace, FaroPlayer player) {
        if (wagerToPlace >= player.getPoints()) {
            return false;
        }
        pointsPlaced = wagerToPlace;
        return true;
    }

    public void setCard(String card) {
        this.card = card;
    }
}

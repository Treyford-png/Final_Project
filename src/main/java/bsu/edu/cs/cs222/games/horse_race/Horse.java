package bsu.edu.cs.cs222.games.horse_race;

public class Horse {
    private final int name;
    private final int flipChance; // Chance of 'flipping a coin' and advancing
    private int position; // location on track
    public static final int TRACK_LEN = 10;

    public Horse(int name, int flipChance) {
        this.name = name;
        this.flipChance = flipChance;
        this.position = 9;
    }

    public int getName() {
        return name;
    }

    public void advance() {
        position--;
    }

    public void moveToStart() {
        position = 0;
    }

    public boolean hasWon() {
        return position == -1;
    }

    /**
     * Prints 10 space track and horse's position on it
     */
    public void printLane() {
        StringBuilder lane = new StringBuilder("[");

        for (int i = 0; i < TRACK_LEN; i++) {
            if (i == position) {
                lane.append(name).append(" ");
            } else {
                lane.append(". ");
            }
        }

        System.out.println(lane + "]");

        if (position >= TRACK_LEN) {
            System.out.println("HORSE #" + name + " WINS!!!");
        }
    }

    /**
     * Generates if the horse moves forward or not
     * If the number exceeds the horse's chance, it moves forward
     * @param coinFlipNumber number 0-100
     */
    public void turn(int coinFlipNumber) {
        if (coinFlipNumber <= flipChance) {
            advance();
        }
        printLane();
    }

    public static int getTrackLen() {
        return TRACK_LEN;
    }

    public int getPosition() {
        return position;
    }
}
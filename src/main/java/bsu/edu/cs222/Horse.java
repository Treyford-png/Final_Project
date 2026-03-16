package bsu.edu.cs222;

public class Horse {
    private final int name;
    private final int flipChance;
    private int position;

    public static final int TRACK_LEN = 10;

    public Horse(int name, int flipChance) {
        this.name = name;
        this.flipChance = flipChance;
        this.position = 0;
    }

    public int getName() {
        return name;
    }

    public void advance() {
        position++;
    }

    public void moveToStart() {
        position = 0;
    }
    public boolean hasWon() {
        return position >= TRACK_LEN;
    }

    public void printLane() {
        String lane = "[";

        for (int i = 0; i < TRACK_LEN; i++) {
            if (i == position) {
                lane += name + " ";
            } else {
                lane += ". ";
            }
        }

        System.out.println(lane + "]");

        if (position >= TRACK_LEN) {
            System.out.println("HORSE #" + name + " WINS!!!");
        }
    }

    public void turn(int coinFlipNumber) {
        if (coinFlipNumber <= flipChance) {
            advance();
        }
        printLane();
    }

    public static int getTrackLen() {
        return TRACK_LEN;
    }
}

import java.util.Random;
public class SlotMachine {

        private final Symbol[] reel = Symbol.values();
        private final Random rand = new Random();
        private int totalPoints = 0;
            return new Symbol[] {
                    reel[rand.nextInt(reel.length)],
                    reel[rand.nextInt(reel.length)],
                    reel[rand.nextInt(reel.length)]
            };

        }

        public int calculatePayout(Symbol[] result) {
            if (result[0] == result[1] && result[1] == result[2]) {
                return result[0].getPoints();

            } else if (result[0] == result[1] || result[1] == result[2]
                    || result[0] == result[2]) {
                return  result[0].getPoints() /2;


            }
            return 0;

        }

        public void addPoints(int pts) {totalPoints += pts; }
        public int getTotalPoints() { return totalPoints; }

void main() {
}

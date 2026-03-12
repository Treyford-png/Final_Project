public enum Symbol {

        LIBERTY_BELL("🔔Liberty Bell",  10000),
        HORSESHOE("🧲Horseshoe",        5000),
        DIAMOND("♢Diamond",            20000),
        SPADE("♤Spade",                1000),
        HEART("♡Heart",                  500);

        private final String displayName;
        private final int points;

        Symbol(String displayName, int points) {
            this.displayName = displayName;
            this.points = points;
        }

        public String getDisplayName() { return displayName; }

        public int getPoints() { return points;}
    }
Author : Laura Romero
Howdy partner, buckle up! Welcome to our virtual game center Frontier Games.
This Liberty Bell Machine is part of a bigger game component of a Wild West Themed game center called "Frontier Games".
Frontier Games has crafted a virtual version of a Liberty Bell, inspired by the grandfather of game machinery Charles Fey, the liberty bell was invented by piecing new systems for spinning reels and returning a payout. It was named Liberty Bell to better connect with American Pride and optimism, which is what builds our nations identity.


Liberty Bell Slot machine game built in Java with JavaFX, where you will spin the reels, manage your points and test your fortune in the wild west!

In this game, we use three spinning reels and unique symbols like the horseshoe, bell, diamond, spade and heart.
How to Play : User will be prompted to create a onetime username and password. Place your points bet and if three matching symbols land your payout will be the jackpot.

Symbols and Point Amounts; SymbolPoints (3-of-a-kind) 
🔔 Liberty Bell  10,000 
🧲 Horseshoe      5,000 
♦ Diamond         2,000 
♤ Spade           1,000 
♥ Heart             500

Payout rules ; 3 of a kind - Win the full point value matched symbol. 2 of a kind - Win half the point value of the matched symbols No match - No payout, try again.

User bet is deducted from each spin, so user must make sure they have enough points before making a wager or the liberty bell machine won't work.

```
 SET UP AND HOW TO RUN :
```

NEEDED : Java (latest version) JavaFX SDK IntellijIDEA Maven or gradle support

How to run project 
1.Clone the repository 
2.Open project in IDE and let it index.
3.Make sure JavaFX is configured in your IDE settings. 
4. Run the main entrance and launch liberty bell module directly.



Project structure
src/
└── main/
└── java/
└── bsu/edu/cs/cs222/
├── characters/
│   └── User.java               Shared user/player state
└── games/
└── liberty_bell/
├── LibertyBellMachine.java      Core game logic
└── LibertyBellSymbols.java (ENUM)    Symbol definitions & point values
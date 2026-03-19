# FROTNIER GAMES v0.1.0
## Introduction
Frontier Games v0.1.0 is a virtual, interactive museum housing recreations of many recreational games from the American Wild West (1865-1895), rebuilt using Java SE 25. This is meant to serve as a fun learning tool and a window into the lives of those from our past. Embarch into a journey and see the American Frontier as you have never seen it before.

*NOTE: this project contains games with refrences to wagering mechanics and saloons. This game in no way encourages or endorses those subjects, but mearly wishes to show the West as it was.*

## SETUP
NEEDED : Java (latest version) JavaFX SDK IntellijIDEA Maven or gradle support

How to run project 
1. Clone the repository 
2. Open project in IDE and let it index. 
3. Make sure JavaFX is configured in your IDE settings. 
4. Run the main entrance to sign up or login
5. Access the many games listed

## Games

### Liberty Bell Slot Machine

_Author: Laura Romero_

The first of her kinds, the Liberty Bell was a device built in 1894 by Charles Fey. Nicknamed the "One-Armed Bandit" because of her bad odds, she worked as a virtual replacement for cards. Despite being a small footnote at the end of the Wild West, her effects would be the most profound of any game on display


Symbols and Point Amounts; SymbolPoints (3-of-a-kind) 🔔 Liberty Bell 1,000 🧲 Horseshoe 500 ♦ Diamond 200 ♤ Spade 100 ♥ Heart 50

Payout rules ; 3 of a kind - Win the full point value matched symbol. 2 of a kind - Win half the point value of the matched symbols No match - No payout, try again.

User bet is deducted from each spin, so user must make sure they have enough points before making a wager or the liberty bell machine won't work.


### Roulette

_Author: Treyford Mercer_

With this game you will bet on a color and then a number. If you just want to bet with a color you type 'no' after you choose your number. This game is not skill based it's fully based on randomness so try your luck!

The user will be prompted on how much they want to bet at the beginning. They will then choose a color (red or black) and then a number (1-37) or just type 'no' for just betting on a color. After this is done, the wheel will spin and will choose randomly a number and it's corresponding color. If you just bet on a color and win, you will gain 2x your bet, if you guess the exact number you will win 7x.

The user's bet is deducted from each run through the game, so points is stored in a separate user class that will be accessed per game.


### Vingt-Un

_Author: Holden Hankins_

A direct predecessor to Blackjack (which spawned out of the Klondike Gold Rush just one year after the Frontier closed) is an import from Britain, which was in turn an import from France. The game plays very similarly to Blackjack with a few notible differences. While multiple variations exist, the version from Hoyle's Games Improved from London 1800 is typically considered the accepted varient.

Players start by drawing cards. The first player to draw an ace is dealer. Each player is dealt 2 cards face down. They are then prompted to hold or stand. Once all players flip, they are compared to the dealer. Players who win with 21 recieve 2:1 payouts. Those who stand while the dealer has 21 alone pay 2:1. All players who fold or go below the dealer pay 1:1. Those that beat the dealer recieve 1:1.

Cards are discarded and only reshuffled when the deck is empty. If a natural 21 occurs, that player becomes the dealer and the deck is reshuffled.


### Faro

Considered "The King of All Games" was the premier game in the American West. Born from the Spanish Montebank style of games (that the 3-Card-Monte scam is named for), the game was simple.

Players put wagers on which card they believe will be drawn next. At the start, 1/52 cards is burned off, called the 'soda'. Then, two cards are delt face up. The first card is the Dealer's Card. All wagers on that card are lost. The second, the Player's Card. All wagers on it pay back 1:1. When 3 cards remain, players guess the order of the last 3 cards. If they guess correctly, they recieve 4:1 rewards.


### Horse Racing

Just like today, horse racing was incredibly popular in the American West. However, unlike the polished events of today, it was normally work horses raced on dirt and sand tracks. Our version of the game works as follows.

Each horse is given certain odds of progressing down the track, saved in memory as a 'coinflip'. The player places a wager on which horse they think will reach the end of a 10 space track first. After that, the horses are off! The first one to finish is crowned the winner. Rewards are payed back in 5:1 odds.

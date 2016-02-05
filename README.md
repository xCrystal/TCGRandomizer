## TCGRandomizer
A basic randomizer of Pokemon TCG (GBC), written in Java. Work in progress.

It randomizes the following ROM:
* Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd

#### <b>Current features</b>

Currently, it randomizes the following data inherent to Pokemon cards (all options are selectable):
* HP
* Weaknesses and resistances
* Retreat cost
* The moves across Pokemon cards of the same type are shuffled with an algorithm that accounts for energy requirements. Empty moves and Pokemon powers are not shuffled. For the rest, a 1-energy move will become another 1-energy move, a 2-energy move will become another 2-energy move, and a 3- or 4-energy move will become another 3- or 4-energy move (always between cards of the same type).

The minimum and maximum HP, retreat cost, and number of weaknesses and resistances is selectable. The HP and retreat cost ranges are selectable for 6 types of evolution classes or stages.

#### <b>Current bugs or flaws</b>

* (Bug) If the shuffle moves option is selected, most Pokemon names in the move descriptions will be wrong as they will still refer to the original card.
* (Flaw) If the shuffle moves option is selected, there is a chance that a card ends up with no damage-dealing move (more common for cards with a single move slot).
* (Flaw) If the shuffle moves option is selected, Call for Family and similar effects are inconsistent with the card Pokemon species.

#### <b>Planned features or tweaks (in no particular order)</b>

* Fix wrong Pokemon names showing in the descriptions of the shuffled moves.
* Selectable "energy mappings" in the shuffle moves option.
* Option to force a damaing move on every card.
* Further randomization of moves, beyond just shuffling the effects, and randomizing energy requirements and damage. Randomizing the move effects themselves may be a possibility, but further reverse engineering of the Pokemon TCG duel engine will be necessary first.
* Randomization of types. This is related to the randomization of energy requirements and would require adjusting the decks to keep them type-consistent. 
* Randomization of other misc Pokemon card data.
* Option to make the randomization repeatable so that different people can generate the same changes.
* Being able to load the Pokemon TCG ROM from the file system.

Contributions aren't expected but would always be welcome.

#### <b>Screenshots</b>

![window](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/window.png)

![1](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/1.bmp)
![2](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/2.bmp)
![3](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/3.bmp)
![4](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/4.bmp)
![5](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/5.bmp)
![6](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/6.bmp)
![7](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/7.bmp)
![8](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/8.bmp)
![9](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/9.bmp)
![10](https://raw.githubusercontent.com/xCrystal/TCGRandomizer/master/screenshots/10.bmp)

#### <b>Download</b>

[TCGRandomizer.jar](TCGRandomizer.jar?raw=true)

At the moment, the Pokemon TCG ROM detailed above must be in the same directory as the .jar file. Make sure you have a recent version of Java installed on your computer. Execute the .jar file and choose your settings. Finally, click on the "Randomize!" button to generate a randomized ROM named tcgrandomized.gbc.

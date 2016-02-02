## TCGRandomizer
A basic randomizer of Pokemon TCG (GBC), written in Java. Work in progress.

It randomizes the following ROM:
* Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd

#### <b>Current features</b>

Currently, it randomizes the following (selectable):
* HP
* Weaknesses and resistances
* Retreat cost
* The moves across Pokemon cards of the same type are shuffled with an algorithm that accounts for energy requirements. Blank moves and Pokemon powers are not shuffled

It distinguishes between 6 types of evolution classes or stages to determine the possible values of HP and retreat cost.

#### <b>Planned features or tweaks</b>

* Fix wrong Pokemon names showing in the descriptions of the shuffled moves
* Further randomization of moves, beyond just shuffling the effects, and randomizing energy requirements and damage. Randomizing the move effects themselves may be a possibility, but further reverse engineering of the Pokemon TCG duel engine will be necessary first.
* Randomization of types. This is related to the randomization of energy requirements and would require adjusting the decks to keep them type-consistent. 
* Randomization of other misc Pokemon card data.
* Some customizable settings including the option to make the randomization repeatable so that different people can generate the same changes.

Contributions aren't expected but would always be welcome.

#### <b>Screenshots</b>

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

At the moment, the Pokemon TCG ROM detailed above must be in the same directory as the .jar file. Executing the .jar file and choose your settings. Finally, click on the "Randomize!" button to generate a randomized ROM named tcgrandomized.gbc.

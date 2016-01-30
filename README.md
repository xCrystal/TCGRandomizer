## TCGRandomizer
A very basic randomizer of Pokemon TCG (GBC). Work in progress.

It randomizes the following ROM:
* Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd

Currently, it randomizes the following:
* HP
* Weaknesses and resistances
* Retreat cost

It distinguishes between 6 types of evolution classes or stages to determine the possible values of HP and retreat cost.

At the moment, the Pokemon TCG ROM detailed above must be in the same directory as the .jar file.

<b>Planned features or tweaks</b>:
* Fix checksum
* Remove broken tutorial
* GUI
* Randomization of moves, including shuffling the effects and randomizing energy requirements and damage. Further reverse engineering of the Pokemon TCG duel engine may be necessary first.
* Randomization of types. This is related to the randomizaion of energy requirements and would require adjusting the decks
* Randomization of other misc card data
* Some customizable settings

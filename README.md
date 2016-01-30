## TCGRandomizer
A very basic randomizer of Pokemon TCG (GBC). Work in progress.

It randomizes the following ROM:
* Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd

Currently, it randomizes the following:
* HP
* Weaknesses and resistances
* Retreat cost

It distinguishes between 6 types of evolution classes or stages to determine the possible values of HP and retreat cost.

At the moment, the Pokemon TCG ROM detailed above must be in the same directory as the .jar file. Upon executing the .jar file, tcgrandomized.gbc will be generated.

<b>Planned features or tweaks</b>
* GUI
* Randomization of moves, including shuffling the effects (between same type moves), and randomizing energy requirements and damage. Randomizing the move effects themselves may be a possibility, but turther reverse engineering of the Pokemon TCG duel engine will be necessary first.
* Randomization of types. This is related to the randomization of energy requirements and would require adjusting the decks to keep them type-consistent. 
* Randomization of other misc Pokemon card data.
* Some customizable randomization settings.

Contributions aren't expected but would always be welcome.

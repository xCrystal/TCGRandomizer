//TODO
//Fix Pokemon names in descriptions
//Make randomization repeatable
//Maybe randomize types (and type of moves) and other data
//...

package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import constants.Constants;
import settings.Settings;
import utils.Utils;

public class MainLogic {

	public static void main () {
		
		long startTime = System.nanoTime();
				
		ByteBuffer bbRead = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		ByteBuffer bbWrite = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		
		try (
				RandomAccessFile fin = new RandomAccessFile(Constants.FILE_NAME_IN,  "r" );
				RandomAccessFile fout = new RandomAccessFile(Constants.FILE_NAME_OUT,  "rw" );
				FileChannel chin  = fin.getChannel();
				FileChannel chout = fout.getChannel();
		) 	{

			if (ProgramLogic.verifyRom(chin) == false)
				throw new FileNotFoundException();
			
			ProgramLogic.createRomCopy(chin, chout);
			ProgramLogic.readPokemonCardsData(chin, bbRead, bbWrite);
			if (Settings.settings.isMatchEnergiesSelected() == true) ProgramLogic.matchAttackEnergiesToType(bbRead);		
			ProgramLogic.doRandomization(bbRead, bbWrite);
			ProgramLogic.saveChangesToRom(chout, bbWrite);
			ProgramLogic.disablePracticeMode(fout);
			ProgramLogic.fixGlobalChecksum(chout);
			
			long endTime = System.nanoTime();
			Utils.print("tcgrandomized.gbc has been successfully generated. Took "+ (double) (endTime - startTime)/1000000 + " ms.");
			
		} catch (FileNotFoundException e) {
			Utils.print(Constants.FILE_NAME_IN + " was not found in the directory "
					+ "or is not a valid Pokemon TCG ROM.\n"
					+ "Required ROM:\n"
					+ "Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd\n");
			
		} catch (IOException e) {
			Utils.print("An unexpected error has occurred. Try again maybe?");
		}			
	}

}

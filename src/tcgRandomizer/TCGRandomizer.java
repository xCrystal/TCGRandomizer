//TODO
//Tutorial is broken
//Checksum
//Swap moves between same type cards
//UI
//...

package tcgRandomizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import constants.Constants;
import engine.Engine1;
import engine.Utils;

public class TCGRandomizer {
	
	private static final Engine1 engine1 = new Engine1();

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
				
		ByteBuffer bbRead = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		ByteBuffer bbWrite = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		
		try (
				FileChannel chin  = (new RandomAccessFile(Constants.FILE_NAME_IN,  "r" )).getChannel();
				FileChannel chout = (new RandomAccessFile(Constants.FILE_NAME_OUT, "rw")).getChannel();
		) 	{

			if (engine1.verifyRom(chin) == false)
				throw new FileNotFoundException();
			
			engine1.copyRom(chin, chout);
			engine1.readPokemonCardsData(chin, bbRead, bbWrite);
			engine1.doRandomization(bbRead, bbWrite);
			engine1.saveToRom(chout, bbWrite);
			
			long endTime = System.nanoTime();
			Utils.print("tcgrandomized.gbc has been successfully generated. Took "+ (endTime - startTime) + " ns.");
			
		} catch (FileNotFoundException e) {
			Utils.print(Constants.FILE_NAME_IN + " was not found in the directory "
					+ "or is not a valid Pokemon tcg ROM.\n"
					+ "Required ROM information:\n"
					+ "Pokémon Trading Card Game (U) [C][!].gbc md5: 219b2cc64e5a052003015d4bd4c622cd\n");
			
		} catch (IOException e) {
			Utils.print("An unexpected error has occurred. Try again maybe?");
		}			
	}

}

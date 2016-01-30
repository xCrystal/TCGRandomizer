//TODO
//Tutorial is broken
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

	public static void main (String[] args) {
		
		long startTime = System.nanoTime();
				
		ByteBuffer bbRead = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		ByteBuffer bbWrite = ByteBuffer.allocate(Constants.PKMN_CARD_DATA_LENGTH * Constants.NUM_POKEMON_CARDS);
		
		try (
				RandomAccessFile fin = new RandomAccessFile(Constants.FILE_NAME_IN,  "r" );
				RandomAccessFile fout = new RandomAccessFile(Constants.FILE_NAME_OUT,  "rw" );
				FileChannel chin  = fin.getChannel();
				FileChannel chout = fout.getChannel();
		) 	{

			if (engine1.verifyRom(chin) == false)
				throw new FileNotFoundException();
			
			engine1.copyRom(chin, chout);
			engine1.readPokemonCardsData(chin, bbRead, bbWrite);
			engine1.doRandomization(bbRead, bbWrite);
			engine1.saveToRom(chout, bbWrite);
			engine1.removePracticeMode(fout);
			engine1.globalChecksum(chout);
			
			long endTime = System.nanoTime();
			Utils.print("tcgrandomized.gbc has been successfully generated. Took "+ (double) (endTime - startTime)/1000000 + " ms.");
			
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

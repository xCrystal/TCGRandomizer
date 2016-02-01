package engine;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import constants.Cards;
import constants.Constants;
import constants.EvoTypes;

public class Engine1 {
		
	public Engine1() {}
	
	/** Makes sure that tcg.gbc is actually a tcg ROM 
	  * @return true if header data matches, false otherwise */
	public boolean verifyRom (FileChannel chin) throws IOException {
		
		byte[] header = {0x50, 0x4F, 0x4B, 0x45, 0x43, 0x41, 0x52, 0x44, 0x00, 0x00, 0x00, 0x41, 0x58, 0x51, 0x45, 
				(byte) 0x80, 0x30, 0x31, 0x03, 0x1B, 0x05, 0x03, 0x01, 0x33, 0x00, 0x34, 0x26, (byte) 0xA6};
		
		ByteBuffer headerin = ByteBuffer.allocate(header.length);
		chin.read(headerin, 0x134);
		
		if (((ByteBuffer) headerin.rewind()).compareTo(ByteBuffer.wrap(header)) == 0)
			return true;
		else
			return false;
	}
	
	/** Creates a copy of tcg.gbc named tcgrandomized.gbc */
	public void createRomCopy (FileChannel chin, FileChannel chout) throws IOException {
		
		chin.transferTo(0, chin.size(), chout);
	}
	
	/** Copies data of all Pokemon cards to two byte buffers */
	public void readPokemonCardsData (FileChannel ch, ByteBuffer bbRead, ByteBuffer bbWrite) throws IOException {
		
		Utils.init(ch);
		ch.read(bbRead);
		Utils.init(ch);
		ch.read(bbWrite);
	}
	
	/** Applies the randomization in the second byte buffer */
	public void doRandomization (ByteBuffer bbRead, ByteBuffer bbWrite) throws IOException {
		
		Engine2 engine2 = new Engine2();
		
		for (int i = 0; i < Constants.NUM_POKEMON_CARDS; i++) {
			
			EvoTypes et = EvoTypes.values()[Cards.values()[i].getEvoType()];
			
			engine2.randomizeHP(bbWrite, i, et);          /* HP                    */
			engine2.randomizeWR(bbWrite, i);              /* Weakness & Resistance */
			engine2.randomizeRetreatCost(bbWrite, i, et); /* Retreat Cost          */
		}
		
		/* Moves */
		int[] grassArray     = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Bulbasaur,  Cards.Pinsir));
		int[] fireArray      = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Charmander, Cards.Moltres2));
		int[] waterArray     = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Squirtle,   Cards.Articuno2));
		int[] lightingArray  = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Pikachu1,   Cards.Zapdos3));
		int[] fightingArray  = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Sandshrew,  Cards.Aerodactyl));
		int[] psychicArray   = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Abra,       Cards.Mew3));
		int[] colorlessArray = engine2.shuffleMoveArray(engine2.getMovesAsIndexArray(bbRead, Cards.Pidgey,     Cards.Dragonite2));
		
		engine2.applyMoveArrayOrder (bbRead, bbWrite, grassArray,     Cards.Bulbasaur);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, fireArray,      Cards.Charmander);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, waterArray,     Cards.Squirtle);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, lightingArray,  Cards.Pikachu1);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, fightingArray,  Cards.Sandshrew);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, psychicArray,   Cards.Abra);
		engine2.applyMoveArrayOrder (bbRead, bbWrite, colorlessArray, Cards.Pidgey);
	}
	
	/** Saves all changes to tcgrandomized.gbc */
	public void saveChangesToRom (FileChannel ch, ByteBuffer bbWrite) throws IOException {
		
		Utils.init(ch);
		bbWrite.rewind();
		ch.write(bbWrite);
	}
	
	/** Turns the tutorial into a regular duel to prevent the player from possibly getting stuck */
	public void disablePracticeMode (RandomAccessFile f) throws IOException {
		
		f.seek(0x2b86);
		f.writeByte(0xaf);
	}
	
	/** Fixes the global checksum */
	public void fixGlobalChecksum (FileChannel ch) throws IOException {
		
		ch.position(0);
		ByteBuffer rom = ByteBuffer.allocate(0x100000);
		ch.read(rom);
		
		int checksum = 0;
		for (byte b : rom.array())
			checksum += ((int) b) & 0xff;
		checksum -= 0xcc;
		
		ch.position(0x14e);
		byte[] cs = new byte[2];
		cs[0] = (byte) ((checksum >> 8) & 0xff);
		cs[1] = (byte) (checksum & 0xff);
		ch.write(ByteBuffer.wrap(cs));
	}
	
}

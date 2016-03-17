package logic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static constants.Cards.*;
import constants.Cards;
import constants.Constants;
import constants.Fields.CardFields;
import gui.GUIController;
import settings.EvoTypes;
import settings.Settings.Options;
import utils.Utils;

class ProgramLogic {
		
	private static final GUIController gui = GUIController.getGuiController();

	ProgramLogic() {}
	
	/** Makes sure that tcg.gbc is actually a tcg ROM 
	  * @return true if header data matches, false otherwise */
	static boolean verifyRom (FileChannel chin) throws IOException {
		
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
	static void createRomCopy (FileChannel chin, FileChannel chout) throws IOException {
		
		chin.transferTo(0, chin.size(), chout);
	}
	
	/** Copies data of all Pokemon cards to two byte buffers */
	static void readPokemonCardsData (FileChannel ch, ByteBuffer bbRead, ByteBuffer bbWrite) throws IOException {
		
		Utils.init(ch);
		ch.read(bbRead);
		Utils.init(ch);
		ch.read(bbWrite);
	}
	
	static void matchAttackEnergiesToType (ByteBuffer bb) throws IOException {

		byte[] grass_1 = {0x01, 0x00, 0x00, 0x00};
		byte[] water_1 = {0x00, 0x01, 0x00, 0x00};
		byte[] light_2 = {0x00, 0x20, 0x00, 0x00};
		
		Utils.initTo(bb, Cards.Exeggcute.ordinal(), CardFields.MOVE1);
		bb.put(grass_1); /* Exeggcute's Hypnosis */
		
		Utils.initTo(bb, Cards.Exeggutor.ordinal(), CardFields.MOVE1);
		bb.put(grass_1); /* Exeggutor's Teleport */
		
		Utils.initTo(bb, Cards.Psyduck.ordinal(), CardFields.MOVE1);
		bb.put(water_1); /* Psyduck's Headache */
		
		Utils.initTo(bb, Cards.Golduck.ordinal(), CardFields.MOVE1);
		bb.put(water_1); /* Golduck's Psyshock */
		
		Utils.initTo(bb, Cards.SurfingPikachu1.ordinal(), CardFields.MOVE1);
		bb.put(light_2); /* Surfing Pikachu 1's Surf */
		
		Utils.initTo(bb, Cards.SurfingPikachu2.ordinal(), CardFields.MOVE1);
		bb.put(light_2); /* Surfing Pikachu 2's Surf */
	}
	
	/** Applies the randomization in the second byte buffer */
	static void doRandomization (ByteBuffer bbRead, ByteBuffer bbWrite) throws IOException {
		
		for (int i = 0; i < Constants.NUM_POKEMON_CARDS; i++) {
			
			EvoTypes et = EvoTypes.values()[Cards.values()[i].getEvoType()];
			
			if (gui.getOption(Options.HP.ordinal())) RandomizerLogic.randomizeHP(bbWrite, i, et);          /* HP */
			if (gui.getOption(Options.WR.ordinal())) RandomizerLogic.randomizeWR(bbWrite, i);              /* Weakness & Resistance */
			if (gui.getOption(Options.RC.ordinal())) RandomizerLogic.randomizeRetreatCost(bbWrite, i, et); /* Retreat Cost          */
		}
		
		/* Moves */
		if (gui.getOption(Options.MOVES.ordinal())) {
			
			int[] grassArray     = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Bulbasaur,  Pinsir));
			int[] fireArray      = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Charmander, Moltres2));
			int[] waterArray     = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Squirtle,   Articuno2));
			int[] lightingArray  = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Pikachu1,   Zapdos3));
			int[] fightingArray  = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Sandshrew,  Aerodactyl));
			int[] psychicArray   = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Abra,       Mew3));
			int[] colorlessArray = RandomizerLogic.shuffleMoveArray(RandomizerLogic.getMovesAsIndexArray(bbRead, Pidgey,     Dragonite2));
		
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, grassArray,     Bulbasaur);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, fireArray,      Charmander);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, waterArray,     Squirtle);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, lightingArray,  Pikachu1);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, fightingArray,  Sandshrew);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, psychicArray,   Abra);
			RandomizerLogic.applyMoveArrayOrder (bbRead, bbWrite, colorlessArray, Pidgey);
		}
	}
	
	/** Saves all changes to tcgrandomized.gbc */
	static void saveChangesToRom (FileChannel ch, ByteBuffer bbWrite) throws IOException {
		
		Utils.init(ch);
		bbWrite.rewind();
		ch.write(bbWrite);
	}
	
	/** Turns the tutorial into a regular duel to prevent the player from possibly getting stuck */
	static void disablePracticeMode (RandomAccessFile f) throws IOException {
		
		f.seek(0x2b86);
		f.writeByte(0xaf);
	}
	
	/** Fixes the global checksum */
	static void fixGlobalChecksum (FileChannel ch) throws IOException {
		
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

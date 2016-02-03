package utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JOptionPane;

import constants.Constants;
import constants.Fields.CardFields;
import constants.Fields.MoveFields;

public class Utils {

	public Utils() {}
	
	public static void print (String str) {
		
		JOptionPane.showMessageDialog(null, str);
	}
	
	/** Sets FileChannel position to start of Pokemon cards data */
	public static void init (FileChannel ch) throws IOException {
		
		ch.position(Constants.POKEMON_CARDS);
	}
	
	/** Resets position of two ByteBuffers */
	public static void reset (ByteBuffer bb1, ByteBuffer bb2) throws IOException {
		
		bb1.rewind();
		bb2.rewind();
	}
	
	/** Applies movevent [move] to ByteBuffer */
	public static void move (ByteBuffer bb, int move) throws IOException {
		
		bb.position(bb.position() + move);
	}
	
	/** Inits ByteBuffer to position corresponding to Pokemon card i's field f */
	public static void initTo (ByteBuffer bb, int i, CardFields cf) throws IOException {
		
		bb.rewind();
		move(bb, Constants.PKMN_CARD_DATA_LENGTH * i + cf.getOffset());
	}
	
	/** Inits ByteBuffer to position corresponding to Pokemon card i's move field f of move number mn*/
	public static void initTo (ByteBuffer bb, int i, MoveFields mf, int mn) throws IOException {
		
		bb.rewind();
		move(bb, Constants.PKMN_CARD_DATA_LENGTH * i + CardFields.MOVE1.getOffset() + 
				Constants.PKMN_MOVE_DATA_LENGTH * mn + mf.getOffset());
	}
	
	/** @return the sum of the values of the 8 nybbles in i */
	public static int addNybbles (int i) {
		
		int sum = 0;
		sum += i       & 0xF;
		sum += i >>  4 & 0xF;
		sum += i >>  8 & 0xF;
		sum += i >> 12 & 0xF;
		sum += i >> 16 & 0xF;
		sum += i >> 20 & 0xF;
		sum += i >> 24 & 0xF;
		sum += i >> 28 & 0xF;
		return sum;		
	}
	
}

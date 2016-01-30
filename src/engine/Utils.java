package engine;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JOptionPane;

import constants.Constants;
import constants.Fields;

public class Utils {

	public Utils() {}
	
	public static void print (String str) {
		
		JOptionPane.showMessageDialog(null, str);
	}
	
	public static void print (int i) {
		
		System.out.println(i);
	}
	
	public static void print (byte b) {
		
		System.out.println(b);
	}
	
	public static void printh (int i) {
		
		System.out.printf("$%02x ", i);
	}
	
	public static void printh (byte b) {
		
		System.out.printf("$%02x ", b);
	}
	
	public static void printh (ByteBuffer bytes) {
		
		for (byte b : bytes.array()) {
			printh(b);
		}
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
	public static void initTo (ByteBuffer bb, int i, Fields f) throws IOException {
		
		bb.rewind();
		move(bb, Constants.PKMN_CARD_DATA_LENGTH * i + f.getOffset());
	}	
	
	/** Resets first ByteBuffer and inits second to position corresponding to Pokemon card i's field f */
	public static void initTo (ByteBuffer bb1, ByteBuffer bb2, int i, Fields f) throws IOException {
		
		reset(bb1, bb2);
		move(bb2, Constants.PKMN_CARD_DATA_LENGTH * i + f.getOffset());
	}
	
	/** Generates ROM address from ROM pointer and ROM bank */
	public static int romptrToAddress (int bank, int ptrLow, int ptrHigh) {
		
		return (bank * 0x4000 + ((ptrHigh << 8) - 0x4000) + ptrLow);
	}	
	
}

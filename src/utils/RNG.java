package utils;

import java.util.Random;

public class RNG {
	
	private static final Random rnd = new Random();

	public RNG() {}
		
	/** @return a random number between min and max inclusive */
	public static byte randomRange (int min, int max) {
		
		if (min > max) {
			int temp = min;
			min = max;
			max = temp;
		}
		return (byte) (min + rnd.nextInt(max - min + 1));
	}

	/** @return a random number multiple of delta between min and max inclusive */
	public static byte randomRangeScale (int min, int max, int delta) {
		
		return (byte) (delta * randomRange(min/delta, max/delta));
	}
	
	/** @return random valid weakness and resistance values, making sure they don't overlap */
	public static byte[] randomWR (int minW, int maxW, int minR, int maxR) {
		
		int numW = randomRange(minW, maxW);
		int numR = randomRange(minR, maxR);
		int w = 0, r = 0;
		
		do {
			
			if (numW != 0) {
				do {
					w = rnd.nextInt(64) << 2;
				} while (Integer.bitCount(w) != numW);
			}
		
			if (numR != 0) {
				do {
					r = rnd.nextInt(64) << 2;
				} while (Integer.bitCount(r) != numR);
			}
				
			} while ((w & r) != 0);
		
		byte[] b = {(byte) w, (byte) r};
		
		return b;
	}	

}

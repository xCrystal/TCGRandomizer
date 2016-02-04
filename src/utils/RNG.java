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
	
	/** @return random valid weakness and resistance values */
	public static byte[] randomWR() {
		
		int w1, w2 = 0, w3 = 0;
		
		w1 = 1 << randomRange(2, 7);
		
		if (rnd.nextInt(2) == 0) {
			do {
				w2 = 1 << randomRange(2, 7);
			} while (w1 == w2);
		}
		
		if (rnd.nextInt(2) == 0) {
			do {
				w3 = 1 << randomRange(2, 7);
			} while ((w3 == w1) || (w3 == w2));
		}
		
		byte[] b = {(byte) (w1 | w2), (byte) w3};
		
		return b;
	}	

}

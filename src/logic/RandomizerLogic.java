package logic;

import java.io.IOException;
import java.nio.ByteBuffer;

import constants.Cards;
import constants.Constants;
import constants.Fields.CardFields;
import constants.Fields.MoveFields;
import settings.EvoTypes;
import utils.RNG;
import utils.Utils;

class RandomizerLogic {

	RandomizerLogic() {}
	
	/** Evolution 1 of 1 --> Between 50 and  80 HP<br>
	 *  Evolution 1 of 2 --> Between 30 and  60 HP<br>
	 *  Evolution 2 of 2 --> Between 60 and  90 HP<br>
	 *  Evolution 1 of 3 --> Between 30 and  50 HP<br>
	 *  Evolution 2 of 3 --> Between 50 and  70 HP<br>
	 *  Evolution 3 of 3 --> Between 70 and 120 HP<br>  */
	static void randomizeHP (ByteBuffer bbWrite, int i, EvoTypes et) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.HP);
		bbWrite.put(RNG.randomRangeScale(et.getMinHP(), et.getMaxHP(), 10));
	}
	
	/** 1 or 2 weaknesses (50% each), and 0 or 1 resistance (50% each) */
	static void randomizeWR (ByteBuffer bbWrite, int i) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.WEAKNESS);
		bbWrite.put(RNG.randomWR());
	}
	
	/** Evolution 1 of 1 --> Between 1 and 3 retreat cost<br>
	 *  Evolution 1 of 2 --> Between 0 and 1 retreat cost<br>
	 *  Evolution 2 of 2 --> Between 1 and 3 retreat cost<br>
	 *  Evolution 1 of 3 --> Between 0 and 1 retreat cost<br>
	 *  Evolution 2 of 3 --> Between 1 and 2 retreat cost<br>
	 *  Evolution 3 of 3 --> Between 2 and 3 retreat cost<br>  */
	static void randomizeRetreatCost (ByteBuffer bbWrite, int i, EvoTypes et) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.RETREAT_COST);	
		bbWrite.put(RNG.randomRange(et.getMinRC(), et.getMaxRC()));
	}
	
	/** @return the number of energies required to use move in position mn of Pokemon card i starting from typeOffset */
	static int howManyEnergies (ByteBuffer bbRead, int i, int mn) throws IOException {
		
		Utils.initTo(bbRead, i, MoveFields.ENERGY, mn);		
		return Utils.addNybbles(bbRead.getInt());
	}
	
	/** Maps the moves from Pokemon cards between first and last into an integer array:<br>
	 *  - 0 if move is a Pokemon power or is null.<br>
	 *  Else:<br>
	 *  - Bits 0-7: Move index<br>
	 *  - Bits 8-9: Number of energies */
	static int[] getMovesAsIndexArray (ByteBuffer bbRead, Cards first, Cards last) throws IOException {
		
		Utils.initTo(bbRead, first.ordinal(), CardFields.START);
		int[] indexArray = new int[2 * (last.ordinal() + 1 - first.ordinal())];
		
		for (int pos = 0, curCard = 0, moveNumber = 0 ; pos < indexArray.length ; pos ++) {
			
			indexArray[pos] = pos;
			
			switch (howManyEnergies (bbRead, first.ordinal() + curCard, moveNumber)) {
			
			case 0:
				indexArray[pos]  = 0;
				break;
			case 1:
				indexArray[pos] |= 1 << 8;
				break;
			case 2:
				indexArray[pos] |= 2 << 8;
				break;
			default:
				indexArray[pos] |= 3 << 8;
			}
			
			curCard += moveNumber;
			moveNumber ^= 1;
		}
		
		return indexArray;
	}
	
	/** Shuffles the array of move indexes across same type Pokemon cards accounting for energy requirements */
	static int[] shuffleMoveArray (int[] indexArray) {
		
		for (int curIndex = 0, randomIndex = 0, temp = 0 ; curIndex < indexArray.length ; 
				randomIndex = RNG.randomRange(0, indexArray.length - 1)) {
				
			if ((indexArray[curIndex] & 0xf00) == (indexArray[randomIndex] & 0xf00)) {			
				if (curIndex != randomIndex) {
			
					temp = indexArray[curIndex];
					indexArray[curIndex] = indexArray[randomIndex];
					indexArray[randomIndex] = temp;
					curIndex ++;
				}
			}		
		}
		return indexArray;
	}
	
	/** Applies the result of ShuffleMoveArray to the actual Pokemon card move data in the ByteBuffer */
	static void applyMoveArrayOrder (ByteBuffer bbRead, ByteBuffer bbWrite, int[] indexArray, Cards start) throws IOException {
		
		for (int i = 0 ; i < indexArray.length ; i ++) {
			
			if (indexArray[i] != 0) {
			
				indexArray[i] = indexArray[i] & 0xff;
				CardFields moveField;
			
				if ((i & 1) == 0)
					moveField = CardFields.MOVE1;
				else
					moveField = CardFields.MOVE2;			
				Utils.initTo(bbWrite, start.ordinal() + i/2, moveField);
			
				if ((indexArray[i] & 1) == 0)
					moveField = CardFields.MOVE1;
				else
					moveField = CardFields.MOVE2;			
				Utils.initTo(bbRead, start.ordinal() + indexArray[i]/2, moveField);
			
				bbWrite.put(bbRead.array(), bbRead.position(), Constants.PKMN_MOVE_DATA_LENGTH);
			}
		}
	}

}

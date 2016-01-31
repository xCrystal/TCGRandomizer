package engine;

import java.io.IOException;
import java.nio.ByteBuffer;

import constants.EvoTypes;
import constants.Fields.CardFields;
import constants.Fields.MoveFields;

public class Engine2 {

	public Engine2() {}
	
	/** Evolution 1 of 1 --> Between 50 and  80 HP<br>
	 *  Evolution 1 of 2 --> Between 30 and  60 HP<br>
	 *  Evolution 2 of 2 --> Between 60 and  90 HP<br>
	 *  Evolution 1 of 3 --> Between 30 and  50 HP<br>
	 *  Evolution 2 of 3 --> Between 50 and  70 HP<br>
	 *  Evolution 3 of 3 --> Between 70 and 120 HP<br>  */
	public void randomizeHP (ByteBuffer bbWrite, int i, EvoTypes et) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.HP);
		bbWrite.put(Rng.randomRangeScale(et.getMinHP(), et.getMaxHP(), 10));
	}
	
	/** 1 or 2 weaknesses (50% each), and 0 or 1 resistance (50% each) */
	public void randomizeWR (ByteBuffer bbWrite, int i) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.WEAKNESS);
		bbWrite.put(Rng.randomWR());
	}
	
	/** Evolution 1 of 1 --> Between 1 and 3 retreat cost<br>
	 *  Evolution 1 of 2 --> Between 0 and 1 retreat cost<br>
	 *  Evolution 2 of 2 --> Between 1 and 3 retreat cost<br>
	 *  Evolution 1 of 3 --> Between 0 and 1 retreat cost<br>
	 *  Evolution 2 of 3 --> Between 1 and 2 retreat cost<br>
	 *  Evolution 3 of 3 --> Between 2 and 3 retreat cost<br>  */
	public void randomizeRetreatCost (ByteBuffer bbWrite, int i, EvoTypes et) throws IOException {
		
		Utils.initTo(bbWrite, i, CardFields.RETREAT_COST);	
		bbWrite.put(Rng.randomRange(et.getMinRC(), et.getMaxRC()));
	}
	
	/** @return the number of energies required to use move in position mn of Pokemon card i*/
	public int howManyEnergies (ByteBuffer bbRead, int i, int mn) throws IOException {
		
		Utils.initTo(bbRead, i, MoveFields.ENERGY, mn);		
		return Utils.addNybbles(bbRead.getInt());
	}

}

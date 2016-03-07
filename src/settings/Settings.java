package settings;

public class Settings {

	public static final int NUM_OPTIONS = 5;
	
	public enum Options {
		HP, WR, RC, MOVES, FILL;
	}
	
	public static final Settings settings = new Settings(1, 1, 0, 1);
	
	public Settings (int minWeaknesses, int maxWeaknesses, int minResistances, int maxResistances) {
		this.minWeaknesses = minWeaknesses;
		this.maxWeaknesses = maxWeaknesses;
		this.minResistances = minResistances;
		this.maxResistances = maxResistances;
	}
	
	private int minWeaknesses;
	private int maxWeaknesses;
	private int minResistances;
	private int maxResistances;
	
	public int getMinWeaknesses() {
		return minWeaknesses;
	}
	
	public void setMinWeaknesses(int minWeaknesses) {
		this.minWeaknesses = minWeaknesses;
	}
	
	public int getMaxWeaknesses() {
		return maxWeaknesses;
	}
	
	public void setMaxWeaknesses(int maxWeaknesses) {
		this.maxWeaknesses = maxWeaknesses;
	}
	
	public int getMinResistances() {
		return minResistances;
	}
	
	public void setMinResistances(int minResistances) {
		this.minResistances = minResistances;
	}
	
	public int getMaxResistances() {
		return maxResistances;
	}
	
	public void setMaxResistances(int maxResistances) {
		this.maxResistances = maxResistances;
	}
	
	private boolean fillEmptyMoveslots;
	
	public boolean isFillEmptySelected() {
		return fillEmptyMoveslots;
	}

	public void setFillEmptySelected(boolean fillEmptyMoveslots) {
		this.fillEmptyMoveslots = fillEmptyMoveslots;
	}
	
}

package constants;

public class Constants {

	public static final String FILE_NAME_IN  = "tcg.gbc";
	public static final String FILE_NAME_OUT = "tcgrandomized.gbc";
	
	public static final int POKEMON_CARDS = 0x30e28;
	
	public static final int ENERGY_ID  = 0x01;
	public static final int POKEMON_ID = 0x08;
	public static final int TRAINER_ID = 0xc3;

	public static final int NUM_CARDS         = 0xe4;
	public static final int NUM_POKEMON_CARDS = TRAINER_ID - POKEMON_ID;
	
	public static final int PKMN_CARD_DATA_LENGTH   = 0x41;
	public static final int PKMN_MOVE_DATA_LENGTH   = 0x13;
	public static final int TRN_CARD_DATA_LENGTH    = 0x0e;
	public static final int ENERGY_CARD_DATA_LENGTH = 0x0e;
}

package constants;

public class Fields {

	public enum CardFields {
	
		START         (0),
		TYPE          (0),
		GFX           (1),
		NAME          (3),
		RARITY        (5),
		SET           (6),
		ID            (7),
		HP            (8),
		STAGE         (9),
		PRE_EVO_NAME (10),
		MOVE1        (12),
		MOVE2        (31),
		RETREAT_COST (50),
		WEAKNESS     (51),
		RESISTANCE   (52),
		KIND         (53),
		POKEDEX      (55),
		DUMMY        (56),
		LEVEL        (57),
		LENGTH       (58),
		WEIGHT       (60),
		DESCRIPTION  (62),
		UNKNOWN      (64),
		END          (65);
	
		private final int offset;
	
		CardFields (int offset) {
			this.offset = offset;
		}

		public int getOffset() {
			return offset;
		}

	}
	
	public enum MoveFields {
		
		START         (0),
		ENERGY        (0),
		NAME          (4),
		DESCRIPTION   (6),
		DAMAGE       (10),
		CATEGORY     (11),
		EFFECT_CMDS  (12),
		FLAGS1       (14),
		FLAGS2       (15),
		FLAGS3       (16),
		UNKNOWN1     (17),
		UNKNOWN2     (18),
		END          (19);
	
		private final int offset;
	
		MoveFields (int offset) {
			this.offset = offset;
		}

		public int getOffset() {
			return offset;
		}

	}	

}

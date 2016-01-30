package constants;

public enum Fields {
	
	TYPE         (0),
	GFX          (1),
	NAME         (3),
	RARITY       (5),
	SET          (6),
	ID           (7),
	HP           (8),
	STAGE        (9),
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
	UNKNOWN      (64);
	
	private final int offset;
	
	Fields (int offset) {
		this.offset = offset;
	}

	public int getOffset() {
		return offset;
	}

}

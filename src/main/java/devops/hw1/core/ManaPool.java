package devops.hw1.core;

/**
 * Represents the mana pool for both players
 *
 */
public enum ManaPool {
	WHITE1,
	BLUE1,
	BLACK1,
	RED1,
	GREEN1,
	COLORLESS1,
	WHITE2,
	BLUE2,
	BLACK2,
	RED2,
	GREEN2,
	COLORLESS2;
	
	int amount;
	/**
	 * Returns the total amount of mana in the pool
	 * @return amount of mana in the pool
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Adds mana to the mana pool
	 * @param i amount of mana to be added
	 */
	public void add(int i) {
		amount = amount + i;
	}

	/**
	 * Removes mana from the mana pool
	 * @param i amount of mana to be removed
	 */
	public void remove(int i) {
		amount = amount - i;
	}

	/**
	 * Empty the mana pool
	 */
	public void empty() {
		amount = 0;
	}
	
	/**
	 * Empties the mana from all mana pools
	 */
	public static void emptyMana(){
		ManaPool.WHITE1.empty();
		ManaPool.BLUE1.empty();
		ManaPool.BLACK1.empty();
		ManaPool.RED1.empty();
		ManaPool.GREEN1.empty();
		ManaPool.COLORLESS1.empty();
		ManaPool.WHITE2.empty();
		ManaPool.BLUE2.empty();
		ManaPool.BLACK2.empty();
		ManaPool.RED2.empty();
		ManaPool.GREEN2.empty();
		ManaPool.COLORLESS2.empty();
	}
}

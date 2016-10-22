package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-10-14
 */

public class TravelingEnemy extends Enemy {

	/**
	 * These enemies have a standard worth
	 */
	private static final int WORTH = 400;
	private static final int HEALTH = 2;
	private static final int SIZE = 10;

	public TravelingEnemy(TileType[][] shape) {
		super(shape, SIZE, HEALTH, WORTH, 0);
	}

	// Simply move down the screen until we reach the bottom
	@Override
	public boolean move() {
		yCoord += 2;

		// Fall until we reach the bottom of the board, then delete ourselves
		if (outOfBounds()) {
			hp = 0;
		}
		return true;
	}
}

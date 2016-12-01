package master_of_space;

/**
 * User: Erik
 * Date: 2016-10-14
 * Special enemies traveling down the game board in every area once the player reach a certain level.
 */

public class TravelingEnemy extends Enemy {

	private static final int WORTH = 400;
	private static final int HEALTH = 2;
	private static final int SIZE = 10;

	public TravelingEnemy(TileType[][] shape) {
		super(shape, SIZE, HEALTH, WORTH, 0);
	}

	// Simply move down the screen until we reach the bottom
	@Override
	protected boolean move() {
		yCoord += 2;

		// Fall until we reach the bottom of the board, then delete ourselves
		if (outOfBounds()) {
			hp = 0;
		}
		return true;
	}
}

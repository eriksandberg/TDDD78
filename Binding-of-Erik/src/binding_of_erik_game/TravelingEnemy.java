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
	private static final int HP = 2;

	public TravelingEnemy(TileType[][] shape) {
		super(shape);
		this.worth = WORTH;
		this.hp = HP;
		this.size = 10;
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

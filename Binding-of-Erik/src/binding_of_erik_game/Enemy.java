package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic enemy inheriting from Agent.
 * Once we get to creating different kinds of enemies they should inherit from this class
 * if they require additional functionality
 */

public class Enemy extends Agent {

	protected int worth = 1;    // Score/xp you get from killing an enemy

	public Enemy(TileType[][] shape, int size, int hp) {
		// We're evil, we have hp health and don't really care where we spawn
		super(shape, size, true, hp, 0, 0);
	}


	public int getWorth() {
		return worth;
	}

	public void setWorth(int worth) {
		this.worth = worth;
	}

	// The standard enemy does not move
	@Override
	public boolean move() {
		return outOfBounds();
	}
}

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

    public Enemy(TileType[][] shape) {
		super(shape);
		this.isEnemy = true;	// Born evil.
	    this.yCoord = 0;        // All enemies spawn at the top of the room
		move('N');
    }

	// The standard enemy does not move
	@Override
	public boolean move() {
		return outOfBounds();
	}
}

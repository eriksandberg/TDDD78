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
		move('N');
    }
}

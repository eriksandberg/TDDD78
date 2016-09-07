package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic enemy inheriting from Char.
 * Once we get to creating different kinds of enemies they should inherit from this class
 */

public class Enemy extends Char {

	public Enemy(TileType[][] shape, int width, int height) {
		super(shape, width, height);
	}

    /*// Constructor spawning an enemy in a specified position and with a specific amount of hp
    public Enemy(int xcoord, int ycoord, int health) {

        //setXcoord(xcoord);
        //setYcoord(ycoord);

        setHealth(health);
    }*/

	// Born evil.
	public boolean isEnemy() {
		return true;
	}
}

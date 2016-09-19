package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * The highest class in the object hirarchy.
 * All characters and objects featuring in a room inherit from this class.
 */

public class GameObject {

	private TileType[][] shape;
	protected int size;
	//private final int width;
	//private final int height;	// In case we want to make non square objects

	protected int xCoord;
	protected int yCoord;

	// No one is born evil!
	protected boolean isEnemy = false;

	public GameObject(TileType[][] shape, int width, int height) {
		this.shape = shape;
		//this.width = width;
		//this.height = height;
	}

	// Check if another object collide with this.
	// An object could move fast enough to "jump" over another object,
	// avoid by not having them move to many pixels each tick
	public boolean collision(GameObject other) {
		if ((Math.abs(other.xCoord - this.xCoord) * 2) < (other.size + this.size) &&
				(Math.abs(other.yCoord - this.yCoord) * 2) < (other.size + this.size)) {
			System.out.println("Collision!");
			return true;
		}
		return false;
	}

	// Return true if the shot have left the board
	protected boolean outOfBounds() {
		if ((xCoord - size < Room.getAdjEdge()) || (yCoord - size < Room.getAdjEdge()) ||
			(xCoord + 2 * size > Room.getFarEdge()) || (yCoord + 2 * size > Room.getFarEdge())) {
			return true;
		}
		return false;
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	// Return the shape for the paint component to draw
	public TileType getTile(int x, int y) {
		try {
			return shape[xCoord - x + 9][yCoord - y + 9];
		} catch (RuntimeException e) {
			return null;
		}
	}
}

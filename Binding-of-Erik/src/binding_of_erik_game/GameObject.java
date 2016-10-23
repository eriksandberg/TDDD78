package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * The highest class in the object hierarchy.
 * All characters and objects featuring in a room inherit from this class.
 */

public class GameObject {

	// Every object have a shape, a size and a position in the Room
	private TileType[][] shape;
	protected int size;
	protected int xCoord;
	protected int yCoord;

	// No one is born evil!
	protected boolean isEnemy = false;

	// Constructor
	// Let the inheriting objects set position and size as they are not 100% required
	public GameObject(TileType[][] shape, int size, boolean enemy, int x, int y) {
		this.shape = shape;
		this.size = size;
		this.isEnemy = enemy;

		xCoord = x;
		yCoord = y;
	}

	// Objects that's not simply "falling" down the screen will implement their own movement
	protected boolean move() {
		yCoord += 5;
		return outOfBounds();
	}

	// Check if another object collide with this.
	// An object could move fast enough to "jump" over another object,
	// avoid by not having them move to many pixels each tick
	protected boolean collision(GameObject other) {
		//this one checks shots only. Shots are maximum "4" big.
		if ((this.size < 5) && (other.xCoord >= this.xCoord) && (this.xCoord >= other.xCoord - other.size) && (other.yCoord >= this.yCoord) &&
				(this.yCoord >= other.yCoord - other.size)) {
			//the above if statement could be refined if we had a function call to getOffset depending on size
			return true;
		} else return ((this.size > 5) && (Math.abs(other.xCoord - this.xCoord) * 2) < (other.size + this.size) &&
				(Math.abs(other.yCoord - this.yCoord) * 2) < (other.size + this.size));
		//This one handles bodycollisions. If we have no shot or body collision false is returned.
	}

	// Return true if the object has left the room
	protected boolean outOfBounds() {
		//if statement can be simplified to a simple return (logical operation), but this is more readable.
		return ((xCoord - size < Room.getAdjEdge()) || (yCoord - size < Room.getAdjEdge()) ||
				(xCoord + 2 * size > Room.getFarEdge()) || (yCoord + 2 * size > Room.getFarEdge()));
	}

	//This is by coding standards not a pretty solution, but it would require us to rework a large part of the project.
	//It's however very easy to understand and modify. Can easily be used by player and enemies. ONLY does graphical work.
	//The actual direction is set elsewhere.
	protected void rotate(Direction newDirection, Direction oldDirection) {
		//two-layer nested switch, 4x4 = 16 possible outcomes, 12 of those change the graphics.
		//old is where we were facing before.
		switch (oldDirection) {
			case NORTH:
				rotateNorth(newDirection);
				break;
			case SOUTH:
				rotateSouth(newDirection);
				break;
			case EAST:
				rotateEast(newDirection);
				break;
			case WEST:
				rotateWest(newDirection);
				break;
			case OTHER:     // Not used by this class
				break;
			default:
				break;
		}
	}

	private void rotateNorth(Direction direction) {
		switch (direction) {
			case NORTH:
				//do nothing
				break;
			case EAST:
				if (xCoord == Room.getFarEdgeSpawn()) {
					break;
				}
				rotateThisMany(3);
				break;
			case SOUTH:
				rotateThisMany(2);
				break;
			case WEST:
				if (xCoord == 0) {
					break;
				}
				rotateThisMany(1);
				break;
			case OTHER:     // Not used by this class
				break;
			default:
				break;
		}
	}

	private void rotateSouth(Direction direction) {
		switch (direction) {
			case NORTH:
				rotateThisMany(2);
				break;
			case EAST:
				if (xCoord == Room.getFarEdgeSpawn()) {
					break;
				}
				rotateThisMany(1);
				break;
			case SOUTH:
				//do nothing
				break;
			case WEST:
				if (xCoord == 0) {
					break;
				}
				rotateThisMany(3);
				break;
			case OTHER:     // Not used by this class
				break;
			default:
				break;
		}
	}

	private void rotateEast(Direction direction) {
		switch (direction) {
			case NORTH:
				if (yCoord == 0) {
					break;
				}
				rotateThisMany(1);
				break;
			case EAST:
				//do nothing
				break;
			case SOUTH:
				if (yCoord == Room.getFarEdgeSpawn()) {
					break;
				}
				rotateThisMany(3);
				break;
			case WEST:
				rotateThisMany(2);
				break;
			case OTHER:     // Not used by this class
				break;
			default:
				break;
		}
	}

	private void rotateWest(Direction direction) {
		switch (direction) {
			case NORTH:
				if (yCoord == 0) {
					break;
				}
				rotateThisMany(3);
				break;
			case EAST:
				rotateThisMany(2);
				break;
			case SOUTH:
				if (yCoord == Room.getFarEdgeSpawn()) {
					break;
				}
				rotateThisMany(1);
				break;
			case WEST:
				//do nothing
				break;
			case OTHER:     // Not used by this class
				break;
			default:
				break;
		}
	}

	protected void rotateThisMany(int times) {
		for (int i = 0; i < times; i++) {
			//right now we assume that all objects have square dimensions
			final int m = this.size;
			final int n = this.size;
			TileType[][] rotatedShape = new TileType[n][m];
			for (int r = 0; r < m; r++) {
				for (int c = 0; c < n; c++) {
					rotatedShape[c][m - 1 - r] = this.shape[r][c];
				}
			}
			this.shape = rotatedShape;
		}
	}

	// True == enemy
	@SuppressWarnings("SuspiciousGetterSetter")
	// Warning because isEnemy is standard name for getter for both enemy and isEnemy
	protected boolean isEnemy() {
		return isEnemy;
	}

	protected int getSize() {
		return size;
	} //returns size of an actual game object, specified in every sub class.

	// Return the shape for the paint component to draw
	protected TileType getTile(int x, int y) {
		try {
			return shape[xCoord - x + 9][yCoord - y + 9];
		} catch (RuntimeException ignored) {
			return null;
		}
	}
}

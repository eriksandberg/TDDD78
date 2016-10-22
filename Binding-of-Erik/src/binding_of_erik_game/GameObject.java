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
	public boolean move() {
		yCoord += 5;
		return outOfBounds();
	}

	// Check if another object collide with this.
	// An object could move fast enough to "jump" over another object,
	// avoid by not having them move to many pixels each tick
	public boolean collision(GameObject other) {
		//this one checks shots only. Shots are maximum "4" big.
		if ((this.size < 5) && (other.xCoord >= this.xCoord) && (this.xCoord >= other.xCoord - other.size) && (other.yCoord >= this.yCoord) &&
				(this.yCoord >= other.yCoord - other.size)) {
			//the above if statement could be refined if we had a function call to getOffset depending on size
			return true;
		} else if ((this.size > 5) && (Math.abs(other.xCoord - this.xCoord) * 2) < (other.size + this.size) &&
				(Math.abs(other.yCoord - this.yCoord) * 2) < (other.size + this.size)) {
			//For both these if-cases we could have a simple "return (statement)", but then we can't print.
			//We print for debuging purposes at the moment. Easily changed later.
			//Might have to refactor this particular code.
			return true;
		}
		return false;
	}

	// Return true if the object have left the room
	protected boolean outOfBounds() {
		//if statement can be simplified to a simple return (logical operation), but this is more readable.
		return ((xCoord - size < Room.getAdjEdge()) || (yCoord - size < Room.getAdjEdge()) ||
				(xCoord + 2 * size > Room.getFarEdge()) || (yCoord + 2 * size > Room.getFarEdge()));
	}

	public void rotate(Direction newDirection, Direction oldDirection) {
		//two-layer nested switch, 4x4 = 16 possible outcomes.
		//old is where we were facing before.
		switch (oldDirection) {
			case NORTH:
				switch (newDirection) {
					case NORTH:
						//do nothing
						break;
					case EAST:
						if (xCoord == 190) {     //Room.getFarEdge() - size()?
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
					default:
						break;
				}
				break;
			case EAST:
				switch (newDirection) {
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
						if (yCoord == 190) {
							break;
						}
						rotateThisMany(3);
						break;
					case WEST:
						rotateThisMany(2);
						break;
					default:
						break;
				}
				break;
			case SOUTH:
				switch (newDirection) {
					case NORTH:
						rotateThisMany(2);
						break;
					case EAST:
						if (xCoord == 190) {
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
					default:
						break;
				}
				break;
			case WEST:
				switch (newDirection) {
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
						if (yCoord == 190) {
							break;
						}
						rotateThisMany(1);
						break;
					case WEST:
						//do nothing
						break;
					default:
						break;
				}
				break;
			default:
				break;
		}
	}

	public void rotateThisMany(int times) {
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
	public boolean isEnemy() {
		return isEnemy;
	}

	public int getSize() {
		return size;
	} //returns size of an actual game object, specified in every sub class.

	// Return the shape for the paint component to draw
	public TileType getTile(int x, int y) {
		try {
			return shape[xCoord - x + 9][yCoord - y + 9];
		} catch (RuntimeException ignored) {
			return null;
		}
	}
}

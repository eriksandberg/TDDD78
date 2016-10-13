package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * The highest class in the object hirarchy.
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
    public GameObject(TileType[][] shape) {
	    this.shape = shape;
	}

	// Move the object, if the object touch a border it will bounce back 1 unit
	public void move(char direction) {
		switch (direction){
			case 'N':
				yCoord -= 1;
				if (outOfBounds()) {
					yCoord += 1;
				}
				break;
			case 'S':
				yCoord += 1;
				if (outOfBounds()) {
					yCoord -= 1;
				}
				break;
			case 'E':
				xCoord += 1;
				if (outOfBounds()) {
					yCoord -= 1;
				}
				break;
			case 'W':
				yCoord -= 1;
				if (outOfBounds()) {
					yCoord += 1;
				}
				break;
			default: break;
		}
	}

    // Check if another object collide with this.
    // An object could move fast enough to "jump" over another object,
    // avoid by not having them move to many pixels each tick
	//TODO: Implement better collision detection, right now has an offset because it aims for player bottom right pixel
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

	// True == enemy
    @SuppressWarnings("SuspiciousGetterSetter")  // Warning because isEnemy is standard name for getter for both enemy and isEnemy
    public boolean isEnemy() {
	    return isEnemy;
    }

	// Return the shape for the paint component to draw
    public TileType getTile(int x, int y) {
		try {
		    return shape[xCoord - x + 9][yCoord - y + 9];
		} catch (RuntimeException ignored) {
		    return null;
		}
    }
}

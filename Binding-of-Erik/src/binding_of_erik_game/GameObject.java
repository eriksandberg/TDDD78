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
    public GameObject(TileType[][] shape) {
	this.shape = shape;
    }

	// Objects that's not simply "falling" down the screen will implement their own movement
	public boolean move() {
		yCoord -= 5;

		return outOfBounds();
	}

    // Check if another object collide with this.
    // An object could move fast enough to "jump" over another object,
    // avoid by not having them move to many pixels each tick
    //TODO: Implement better collision detection, right now has an offset because it aims for player bottom right pixel
    public boolean collision(GameObject other) {
	if ((Math.abs(other.xCoord - this.xCoord) * 2) < (other.size + this.size) &&
	    (Math.abs(other.yCoord - this.yCoord) * 2) < (other.size + this.size)) {
	/*if 	(this.xCoord < (other.xCoord + other.size) &&
		(this.xCoord + this.size) > other.xCoord &&
		 this.yCoord < (other.yCoord + other.size) &&
		(this.yCoord + this.size) > other.yCoord) {*/
		System.out.println("Collision!");
	    return true;
		}
	return false;
    }

    public void rotate(char newDirection, char oldDirection){
	//two-layer nested switch, 4x4 = 16 possible outcomes.
	//old is where we were facing before.
	switch(oldDirection){
	    case 'N':
		switch(newDirection){
		    case 'N':
			//do nothing
			break;
		    case 'E':
			rotateThisMany(3);
			break;
		    case 'S':
			rotateThisMany(2);
			break;
		    case 'W':
			rotateThisMany(1);
			break;
		    default: break;
		}
		break;
	    case 'E':
		switch(newDirection){
		    case 'N':
			rotateThisMany(1);
			break;
		    case 'E':
			//do nothing
			break;
		    case 'S':
			rotateThisMany(3);
			break;
		    case 'W':
			rotateThisMany(2);
			break;
		    default: break;
		}
		break;
	    case 'S':
		switch(newDirection){
		    case 'N':
			rotateThisMany(2);
			break;
		    case 'E':
			rotateThisMany(1);
			break;
		    case 'S':
			//do nothing
			break;
		    case 'W':
			rotateThisMany(3);
			break;
		    default: break;
		}
		break;
	    case 'W':
		switch(newDirection){
		    case 'N':
			rotateThisMany(3);
			break;
		    case 'E':
			rotateThisMany(2);
			break;
		    case 'S':
			rotateThisMany(1);
			break;
		    case 'W':
			//do nothing
			break;
		    default: break;
		}
		break;
	    default: break;
	}
    }

    public void rotateThisMany(int times){
	for (int i = 0; i < times; i++){
	    //right now we assume that all objects have square dimensions
	    final int M = this.size;
	    final int N = this.size;
	    TileType rotatedShape[][] = new TileType[N][M];
	    for (int r = 0; r < M; r++) {
		for (int c = 0; c < N; c++) {
		    rotatedShape[c][M - 1 - r] = this.shape[r][c];
		}
	    }
	    this.shape = rotatedShape;
	}
    }

    // Return true if the shot have left the board
    protected boolean outOfBounds() {
	//if statement can be simplified to a simple return (logical operation), but this is more readable.
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

package Game;

/**
 * User: Erik
 * Date: 2016-09-02
 */
public class Shot extends GameObject{

	private int shotspeed = 5;  // When generating enemies in the future, generate this

	private float xAngle;
	private float yAngle;

	public Shot(TileType[][] shape, int width, int height) {
		super(shape, width, height);
		//noinspection AssignmentToSuperclassField
		this.size = 2;
	}

	// Where x, y is the position the shot is traveling towards
	public void calcAngle(int x, int y) {
		// The shots spawning position in relation to the room
		// Magic number 4 is because of the size of the char fireing the shot
		int deltaX = (x - 4 - xCoord);
		int deltaY = (y - 4 - yCoord);

		float hypotenuse = (float)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		xAngle = (float)Math.toDegrees(Math.asin(deltaX/hypotenuse)/100);
		yAngle = (float)Math.toDegrees(Math.asin(deltaY/hypotenuse)/100);
	}

	// Move the shot according to position, angle and speed, return false if the shot have left the board
	public boolean move() {
		xCoordFloat += shotspeed * xAngle;
		xCoord = Math.round(xCoordFloat);

		yCoordFloat += shotspeed * yAngle;
		yCoord = Math.round(yCoordFloat);

		return outOfBounds();
	}

	// Return false if the shot have left the board
	private boolean outOfBounds() {
		if (xCoord < Room.getAdjEdge() || yCoord < Room.getAdjEdge() ||
			xCoord > Room.getFarEdge() || yCoord > Room.getFarEdge()) return true;
		return false;
	}


	// Currently all shots are hazardous to everyone, enemy and friend alike
	// Or rather, not hazardous at all
}

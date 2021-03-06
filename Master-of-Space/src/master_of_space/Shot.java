package master_of_space;

/**
 * User: Erik
 * Date: 2016-09-02
 * Shot fired by most enemies. Fired aimed at a target and then keeps that trajectory.
 */
public class Shot extends GameObject {

	protected int shotspeed = 5;
	private static final int SIZE = 2;

	protected float xCoordFloat = 0;
	protected float yCoordFloat = 0;
	protected float xAngle = 0;
	protected float yAngle = 0;

	public Shot(TileType[][] shape) {
		super(shape, SIZE, false, 0, 0);
	}

	// Where x, y is the position the shot is traveling towards
	protected void calcAngle(int x, int y) {
		// The shots spawning position in relation to the room
		// Magic number 4 is because of the size of the char fireing the shot
		int deltaX = (x - 4 - xCoord);
		int deltaY = (y - 4 - yCoord);

		float hypotenuse = (float) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

		xAngle = (float) Math.toDegrees(Math.asin(deltaX / hypotenuse) / 100);
		yAngle = (float) Math.toDegrees(Math.asin(deltaY / hypotenuse) / 100);
	}

	// Move the shot according to position, angle and speed, return false if the shot have left the board
	@Override
	protected boolean move() {
		xCoordFloat += shotspeed * xAngle;
		xCoord = Math.round(xCoordFloat);

		yCoordFloat += shotspeed * yAngle;
		yCoord = Math.round(yCoordFloat);

		return outOfBounds();
	}

	@SuppressWarnings("SuspiciousGetterSetter")
	protected void setAlignment(boolean alignment) {
		this.isEnemy = alignment;
	}
}

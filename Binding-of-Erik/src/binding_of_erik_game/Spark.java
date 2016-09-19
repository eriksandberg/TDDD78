package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-09-17
 */
public class Spark extends Shot {

	private int lifetime = 10;

	public Spark(TileType[][] shape, int width, int height) {
		super(shape, width, height);
		shotspeed = 3;
	}

	@Override
	public boolean move() {
		lifetime -= 1;

		xCoordFloat += shotspeed * xAngle;
		xCoord = Math.round(xCoordFloat);

		yCoordFloat += shotspeed * yAngle;
		yCoord = Math.round(yCoordFloat) + 8;

		return outOfBounds();
	}


	// Return true if the shot have left the board
	// Also return true if the spark have lived it's intended time, this way it will be removed
	@Override
	protected boolean outOfBounds() {
		if ((xCoord - size < Room.getAdjEdge()) || (yCoord - size < Room.getAdjEdge()) ||
				(xCoord + 2 * size > Room.getFarEdge()) || (yCoord + 2 * size > Room.getFarEdge())) {
			return true;
		}
		if (lifetime <= 0) { return true;}
		return false;
	}
}

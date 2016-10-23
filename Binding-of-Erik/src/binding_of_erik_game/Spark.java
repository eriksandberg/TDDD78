package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-09-17
 * Graphical candy for when something collide. Extending Shot because it need complex movement.
 */
public class Spark extends Shot {

	private int lifetime = 6;

	public Spark(TileType[][] shape) {
		super(shape);
		//noinspection AssignmentToSuperclassField
		shotspeed = 3;
	}

	@Override
	protected boolean move() {
		lifetime -= 1;

		xCoordFloat += shotspeed * xAngle;
		xCoord = Math.round(xCoordFloat);

		yCoordFloat += shotspeed * yAngle;
		yCoord = Math.round(yCoordFloat);

		return outOfBounds();
	}

	// Return true if the shot have left the board
	// Also return true if the spark have lived it's intended time, this way it will be removed
	@Override
	protected boolean outOfBounds() {
		return ((xCoord - size < Room.getAdjEdge()) || (yCoord - size < Room.getAdjEdge()) ||
				(xCoord + 2 * size > Room.getFarEdge()) || (yCoord + 2 * size > Room.getFarEdge()) ||
				(lifetime <= 0));
	}
}

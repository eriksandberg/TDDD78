package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-09-12
 * A simple shot traveling at a straight line along either the x- or y-axis.
 */
public class StraightShot extends Shot {

	private Direction direction = Direction.OTHER;

	public StraightShot(TileType[][] shape) {
		super(shape);
	}

	@Override
	protected boolean move() {
		switch (direction) {
			case NORTH:
				yCoord -= shotspeed;
				break;
			case SOUTH:
				yCoord += shotspeed;
				break;
			case EAST:
				xCoord += shotspeed;
				break;
			case WEST:
				xCoord -= shotspeed;
				break;
			case OTHER:
				break;
			default:
				break;
		}
		return outOfBounds();
	}

	protected void setDirection(Direction direction) {
		this.direction = direction;
	}
}

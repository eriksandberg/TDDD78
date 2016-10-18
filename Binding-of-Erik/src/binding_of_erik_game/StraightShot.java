package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-09-12
 */
public class StraightShot extends Shot {

	private Direction direction = Direction.OTHER;

	public StraightShot(TileType[][] shape) {
		super(shape);
		this.size = 2;
	}

	@Override
	public boolean move() {
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

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}

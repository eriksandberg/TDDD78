package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-09-12
 */
public class StraightShot extends Shot {

	private char direction = ' ';

	public StraightShot(TileType[][] shape) {
		super(shape);
	}

	@Override
	public boolean move() {
		switch(direction) {
			case 'N':
				this.yCoord -= shotspeed;
				break;
			case 'S':
				yCoord += shotspeed;
				break;
			case 'E':
				xCoord += shotspeed;
				break;
			case 'W':
				xCoord -= shotspeed;
				break;
			default: break;
		}
		return outOfBounds();
	}

	public void setDirection(char d) {
		if ((d == 'N' || d == 'S' || d == 'E' || d == 'W')) {
			direction = d;
		} else {
			System.out.println("Error: Shot direction set to invalid char!"); // In case we screw up
		}
	}

}

package master_of_space;

/**
 * Created by wassing on 2016-04-04.
 * The player character. Unique movement and shooting.
 */

public class Player extends Agent {

	private int skill;    // Used to determine how hard a new room will be
	private static final int SIZE = 10;

	private Direction direction = Direction.NORTH;   // To be used when fireing shots and to display the correct graphics

	public Player(TileType[][] shape, int x, int y) {
		super(shape, SIZE, false, 10, x, y);
		this.skill = 1;
	}

	// Return false if we could not move for some reason
	protected boolean move(Direction direction) {
		if (isDead()) {    // Can't move if we're dead
			return false;
		}
		switch (direction) {
			case OTHER: //don't move, everybody don't move, it's a don't move dance!
				//don't move!
				break;
			case NORTH: //go north
				yCoord -= 2;
				if (outOfBounds()) {
					yCoord += 2;
					return false;
				} else {
					this.direction = Direction.NORTH;
				}
				break;
			case SOUTH: //go south
				yCoord += 2;
				if (outOfBounds()) {
					yCoord -= 2;
					return false;
				} else {
					this.direction = Direction.SOUTH;
				}
				break;
			case EAST: //go east
				xCoord += 2;
				if (outOfBounds()) {
					xCoord -= 2;
					return false;
				} else {
					this.direction = Direction.EAST;
				}
				break;
			case WEST: //go west
				xCoord -= 2;
				if (outOfBounds()) {
					xCoord += 2;
					return false;
				} else {
					this.direction = Direction.WEST;
				}
				break;
			default:
				return false;
		}
		return true;
	}

	// Return a char indicating what direction the player is facing
	protected Direction getDirection() {
		return direction;
	}

	protected void incSkill() {
		this.skill += 1;
	}

	protected int getSkill() {
		return skill;
	}

	@SuppressWarnings("unused") // Currently never used since GameOver does not restart game
	protected void resetSkill() {
		this.skill = 1;
	}

	protected void resetHP() {
		this.hp = 10;
	}
}
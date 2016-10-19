package binding_of_erik_game;

/**
 * Created by wassing on 2016-04-04.
 */

public class Player extends Agent {

	private int skill;    // Used to determine how hard a new room will be

	private Direction direction = Direction.NORTH;   // To be used when fireing shots and to display the correct graphics

	public Player(TileType[][] shape, int x, int y) {
		super(shape, false, 10, x, y);
		this.size = 10;
		this.skill = 1;
	}

	@Override   // Return false if we could not move for some reason
	public boolean move(Direction direction) {
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


	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	// Return a char indicating what direction the player is facing
	public Direction getDirection() {
		return direction;
	}

	public void incSkill() {
		this.skill += 1;
	}

	public int getSkill() {
		return skill;
	}

	//never used.
	public void resetSkill() {
		this.skill = 1;
	}

	public void resetHP() {
		this.hp = 10;
	}
}
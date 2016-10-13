package binding_of_erik_game;

/**
 * Created by wassing on 2016-04-04.
 */

public class Player extends Agent {

    private int skill;	// Used to determine how hard a new room will be
    private char direction = 'N';   // To be used when fireing shots and to display the correct graphics

    public Player(TileType[][] shape) {
	super(shape);
	this.size = 10;
	this.skill = 1;
	this.hp = 1000;  // For testing purposes we don't want to die
    }

	@Override
    public void move(char direction) {
	if (isDead()) {	// Can't move if we're dead
	  return;
	}
	switch (direction) {
	case 'N': //go north
	    yCoord -= 1;
	    if (outOfBounds()) {
		yCoord += 1;
	    } else {
		this.direction = 'N';
	    }
	    break;
	case 'S': //go south
	    yCoord += 1;
	    if (outOfBounds()) {
		yCoord -= 1;
	    } else {
		this.direction = 'S';
	    }
	    break;
	case 'E': //go east
	    xCoord += 1;
	    if (outOfBounds()) {
		xCoord -= 1;
	    } else {
		this.direction = 'E';
	    }
	    break;
	case 'W': //go west
	    xCoord -= 1;
	    if (outOfBounds()) {
		xCoord += 1;
	    } else {
		this.direction = 'W';
	    }
	    break;
	default: break;
	}
    }

    public void setDirection(char d) {
	if ((d == 'N' || d == 'S' || d == 'E' || d == 'W')) {
	    direction = d;
	} else {
	    System.out.println("Error: Player direction set to invalid char!"); // In case we screw up
	}
    }

    // Return a char indicating what direction the player is facing
    public char getDirection() {
	    return direction;
    }

    public void incSkill() {
	    this.skill += 1;
    }

    public int getSkill() {
	    return skill;
    }

    public void resetSkill() {
	    this.skill = 1;
    }

    public void resetHP() {
		this.hp = 10;
	}
}
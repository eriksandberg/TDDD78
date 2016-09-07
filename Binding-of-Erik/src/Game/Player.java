package Game;

/**
 * Created by wassing on 2016-04-04.
 */

public class Player extends Char {

	private int skill;	// Used to determine how hard a new room will be
	private char direction = 'E';   // To be used when fireing shots and to display the correct graphics

    public Player(TileType[][] shape, int width, int height) {
		super(shape, width, height);
		this.skill = 1;
    }

	public void move(String direction) {
		if (this.isDead()) {	// Can't move if we're dead
			return;
		}
		switch (direction) {
			case "up": //go north
				this.yCoord -= 1;
				if (this.outOfBounds()) {
					this.yCoord += 1;
				} else {
					this.direction = 'N';
				}
				break;
			case "down": //go south
				this.yCoord += 1;
				if (this.outOfBounds()) {
					this.yCoord -= 1;
				} else {
					this.direction = 'S';
				}
				break;
			case "right": //go east
				this.xCoord += 1;
				if (this.outOfBounds()) {
					this.xCoord -= 1;
				} else {
					this.direction = 'E';
				}
				break;
			case "left": //go west
				this.xCoord -= 1;
				if (this.outOfBounds()) {
					this.xCoord += 1;
				} else {
					this.direction = 'W';
				}
				break;
		}
	}

	public void setDirection(char d) {
		if ((d == 'N' || d == 'S' || d == 'E' || d == 'W')) {
			this.direction = d;
		} else {
			System.out.println("Error: Player direction set to invalid char!"); // In case we screw up
		}
	}

	public char getDirection() {
		return direction;
	}

	public void incSkill() {
		this.skill =+ 1;
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
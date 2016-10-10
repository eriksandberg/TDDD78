package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic character inheriting from GameObject.
 * Provide some values and functions shared by all characters, both enemies and friends
 */

public class Agent extends GameObject {

    protected int shotCooldown = 10;   // This should not stay hardcoded forever
    protected int hp = 5;

    public Agent(TileType[][] shape) {
	super(shape);
	//noinspection AssignmentToSuperclassField //does what?
	this.size = 10;
    }

    public void move(Agent c, char direction) {
	switch (direction){
	    case 'N':
		yCoord -= 1;
		if (outOfBounds()) {
			yCoord += 1;
		}
		break;
	    case 'S':
		yCoord += 1;
		if (outOfBounds()) {
			yCoord -= 1;
		}
		break;
	    case 'E':
		xCoord += 1;
		if (outOfBounds()) {
			yCoord -= 1;
		}
		break;
	    case 'W':
		yCoord -= 1;
		if (outOfBounds()) {
			yCoord += 1;
		}
		break;
	    default: break;
	}
    }

    public boolean readyToShoot() {
	if (shotCooldown == 0) {
	    shotCooldown = 10;   // This should not stay hardcoded forever
	    return true;
	} else {
	    shotCooldown--;
	    return false;
	}
    }

    public void setShotCooldown(int shotCooldown) {
	    this.shotCooldown = shotCooldown;
    }

    public boolean isDead() {
	if (this.hp < 1) return true;
	return false;
    }
}
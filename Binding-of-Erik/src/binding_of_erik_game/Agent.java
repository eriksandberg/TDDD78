package binding_of_erik_game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic character inheriting from GameObject.
 * Provide some values and functions shared by all characters, both enemies and friends
 */

public class Agent extends GameObject {

    protected int shotCooldown;
    protected int currentShotCooldown = 10;   // Everyone will wait 10 ticks before firing their first shot
    protected int specialShotCooldown = 30; //used by bosses alternatively special enemies.
    protected int hp = 1;

    public Agent(TileType[][] shape) {
	    super(shape);
    }

    // Move the object, if the object touch a border it will bounce back 1 unit
    public void move(char direction) {
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
		    default:
			    break;
	    }
    }

    public boolean readyToShoot() {
	    if (currentShotCooldown == 0) {
		    currentShotCooldown = shotCooldown;
		    return true;
	    } else {
		    currentShotCooldown--;
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
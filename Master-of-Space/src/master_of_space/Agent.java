package master_of_space;

/**
 * User: Erik Date: 2016-04-04
 * <p>
 * A generic character inheriting from GameObject. Provide some values and functions shared by all characters, both enemies and
 * the player.
 */

public class Agent extends GameObject
{

    protected int shotCooldown;
    protected int currentShotCooldown = 10;   // Everyone will wait 10 ticks before firing their first shot
    protected int hp = 1;

    public Agent(TileType[][] shape, int size, boolean enemy, int hp, int x, int y) {
	super(shape, size, enemy, x, y);
	this.hp = hp;
    }

    protected boolean readyToShoot() {
	if (currentShotCooldown == 0) {
	    currentShotCooldown = shotCooldown;
	    return true;
	} else {
	    currentShotCooldown--;
	    return false;
	}
    }

    protected void setShotCooldown(int shotCooldown) {
	this.shotCooldown = shotCooldown;
    }

    protected boolean isDead() {
	return (this.hp < 1);
    }
}
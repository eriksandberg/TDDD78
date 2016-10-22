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
    protected int hp = 1;

    public Agent(TileType[][] shape, int size, boolean enemy, int hp, int x, int y) {
	    super(shape, size, enemy, x, y);
	    this.hp = hp;
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
		return (this.hp < 1);
    }
}
package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic character inheriting from GameObject.
 * Provide some values and functions shared by all characters, both enemies and friends
 * Most characters will use their own constructors so no custom is provided for this class
 */

public class Char extends GameObject {

	protected int shotCooldown = 10;   // This should not stay hardcoded forever

	public Char(TileType[][] shape, int width, int height) {
		super(shape, width, height);
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

	public boolean isEnemy() {
		return false;
	}

    /*// Default values for all characters
    private String name = "J Doe";

    private int health = 1;
    private int damage = 1;

    public int getHealth() {
        return health;
    }

    public int getDamage() {return damage;}

    // Used to set a characters hp to a specific value
    public void setHealth(int health) {
        this.health = health;
    }

    // Used to damage the character
    public boolean hurt(int dmg) {
        this.health =- dmg;

        // Return false if the character died
        if (isDead()) return false;
        return true;
    }

    // Check if the character is dead and return true if it is
    public boolean isDead() {
        if (health <= 0) return true;
        return false;
    }*/
}
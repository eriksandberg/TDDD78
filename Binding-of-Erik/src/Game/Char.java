package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic character inheriting from GameObject.
 * Provide some values and functions shared by all characters, both enemies and friends
 */

public class Char extends GameObject {

	protected int shotCooldown = 10;   // This should not stay hardcoded forever
	protected int hp = 10;

	public Char(TileType[][] shape, int width, int height) {
		super(shape, width, height);
		//noinspection AssignmentToSuperclassField
		this.size = 10;
	}

	public void move(Char c, char direction) { //moves entities at random. Smarter moving algorithm later.
		switch (direction){
			case 0:
				xCoord += 1;
				break;
			case 1:
				xCoord -= 1;
				break;
			case 2:
				yCoord += 1;
				break;
			case 3:
				yCoord -= 1;
				break;
			case 4: //remain in position
				break;
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

	// No one is born evil!
	public boolean isEnemy() {
		return false;
	}

	public boolean isDead() {
		if (this.hp < 1) return true;
		return false;
	}
}
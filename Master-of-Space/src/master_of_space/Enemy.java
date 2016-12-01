package master_of_space;

import java.util.ArrayList;
import java.util.Collection;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic enemy inheriting from Agent.
 * Different kind of enemies inherit from this class
 * if they require additional functionality
 */

public class Enemy extends Agent {

	protected int worth;    // Score/xp you get from killing an enemy
	protected int specialShotCooldown;

	public Enemy(TileType[][] shape, int size, int hp, int worth, int specialShotCooldown) {
		// We're evil, we have hp health and don't really care where we spawn
		super(shape, size, true, hp, 0, 0);
		this.worth = worth;
		this.specialShotCooldown = specialShotCooldown;
	}

	//protected void

	protected void setSpecialShotCooldown(int shotCooldown) {
		this.specialShotCooldown = shotCooldown;
	}

	protected int getWorth() {
		return worth;
	}

	protected Collection<Shot> shoot(Agent target) {
		Collection<Shot> shots = new ArrayList<>();

		shots.add(createShot(target, this.xCoord - this.getSize() / 2, this.yCoord - this.getSize() / 2));

		return shots;
	}

	@SuppressWarnings("NestedAssignment")   // 2 lines is better than 4
	protected Shot createShot(Agent target, int x, int y) {
		Shot newShot = GraphicsFactory.getInstance().getLightShot();

		newShot.xCoordFloat = newShot.xCoord = x;
		newShot.yCoordFloat = newShot.yCoord = y;
		newShot.calcAngle(target.xCoord, target.yCoord);

		// True = enemy
		newShot.setAlignment(true);
		return newShot;
	}

	// The standard enemy does not move
	@Override
	protected boolean move() {
		return outOfBounds();
	}
}

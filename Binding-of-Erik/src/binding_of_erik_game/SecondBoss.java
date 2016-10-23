package binding_of_erik_game;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wassing on 2016-10-18.
 * Second boss. Very durable and firing a deadly laser beam in adition to the usual shots.
 */
public class SecondBoss extends Enemy {
	private static final int WORTH = 10000;
	private static final int HEALTH = 250;
	private static final int SIZE = 20;
	private static final int SPECIAL_SHOT_DURATION = 50;    // Duration during which we fire lazer (number of shots)
	private static final int SPECIAL_SHOT_COOLDOWN = 10;    // Number of normal shots fired before we once again fire the lazer

	public SecondBoss(TileType[][] shape) {
		super(shape, SIZE, HEALTH, WORTH, SPECIAL_SHOT_DURATION);
	}

	protected boolean readyToShoot() {
		if (currentShotCooldown == 0) {
			currentShotCooldown = shotCooldown;
			return true;
		} else if (specialShotCooldown < SPECIAL_SHOT_DURATION) {
			return true;
		} else {
			currentShotCooldown--;
			return false;
		}
	}

	protected Collection<Shot> shoot(Agent target) {
		Collection<Shot> shots = new ArrayList<>();
		if (this.specialShotCooldown <= SPECIAL_SHOT_DURATION) {
			shots.add(spawnLazer(target));
		} else {
			shots.add(createShot(target, this.xCoord - this.getSize() / 2, this.yCoord - this.getSize() / 2));
		}
		if (specialShotCooldown == 0) {
			specialShotCooldown = SPECIAL_SHOT_DURATION + SPECIAL_SHOT_COOLDOWN;
		}
		specialShotCooldown--;
		return shots;
	}

	@SuppressWarnings("NestedAssignment")   // 2 lines is better than 4
	private Shot spawnLazer(Agent target) {
		Shot lazer = GraphicsFactory.getInstance().getLazer();

		lazer.xCoordFloat = lazer.xCoord = this.xCoord - this.getSize() / 2;
		lazer.yCoordFloat = lazer.yCoord = this.yCoord - this.getSize() / 2;

		lazer.calcAngle(target.xCoord, target.yCoord);
		if (this.specialShotCooldown == 0) {
			this.setSpecialShotCooldown(100);
		}

		// True = enemy
		lazer.setAlignment(true);
		return lazer;
	}
}
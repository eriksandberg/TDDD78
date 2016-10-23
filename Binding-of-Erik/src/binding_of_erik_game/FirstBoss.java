package binding_of_erik_game;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wassing on 2016-10-18.
 * The first boss. Durable and firing two shots at the time.
 */
public class FirstBoss extends Enemy {

	private static final int WORTH = 5000;
	private static final int HEALTH = 80;
	private static final int SIZE = 18;
	private static final int SPECIAL_SHOT_COOLDOWN = 30;

	public FirstBoss(TileType[][] shape) {
		super(shape, SIZE, HEALTH, WORTH, SPECIAL_SHOT_COOLDOWN);
	}

	protected Collection<Shot> shoot(Agent target) {
		Collection<Shot> shots = new ArrayList<>();

		// Spawn two shots, on at each side of the boss
		shots.add(createShot(target, this.xCoord, this.yCoord));
		shots.add(createShot(target, (this.xCoord - this.size), this.yCoord));

		return shots;
	}
}

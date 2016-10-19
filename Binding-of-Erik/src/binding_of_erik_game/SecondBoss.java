package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class SecondBoss extends Enemy {
	private static final int WORTH = 10000;
	private static final int HEALTH = 100;

	public SecondBoss(TileType[][] shape) {
		super(shape, HEALTH);
		this.size = 20;
		this.yCoord = 0;        // All enemies spawn at the top of the room
		this.worth = WORTH;
		this.specialShotCooldown = 100;
	}
}
package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class SecondBoss extends Enemy {
	private static final int WORTH = 10000;
	private static final int HEALTH = 100;
	private static final int SIZE = 20;

	public SecondBoss(TileType[][] shape) {
		super(shape, SIZE, HEALTH);
		this.yCoord = 0;        // All enemies spawn at the top of the room
		this.worth = WORTH;
		this.specialShotCooldown = 100;
	}
}
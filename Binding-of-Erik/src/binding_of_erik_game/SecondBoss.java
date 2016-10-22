package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class SecondBoss extends Enemy {
	private static final int WORTH = 10000;
	private static final int HEALTH = 100;
	private static final int SIZE = 20;
	private static final int SPECIAL_SHOT_COOLDOWN = 50;

	public SecondBoss(TileType[][] shape) {
		super(shape, SIZE, HEALTH, WORTH, SPECIAL_SHOT_COOLDOWN);
	}
}
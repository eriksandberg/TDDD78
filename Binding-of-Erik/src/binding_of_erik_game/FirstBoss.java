package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class FirstBoss extends Enemy {

	public static final int WORTH = 5000;
	private static final int HEALTH = 50;
	private static final int SIZE = 18;

	public FirstBoss(TileType[][] shape) {
		super(shape, SIZE, HEALTH);
		this.worth = WORTH;
		this.specialShotCooldown = 50;
	}
}

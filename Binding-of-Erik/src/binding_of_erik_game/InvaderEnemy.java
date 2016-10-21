package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */

public class InvaderEnemy extends Enemy {

	private static final int WORTH = 50;
	private static final int HEALTH = 3;

	public InvaderEnemy(TileType[][] shape) {
		super(shape, HEALTH);
		this.size = 10;
		this.worth = WORTH;
	}
}

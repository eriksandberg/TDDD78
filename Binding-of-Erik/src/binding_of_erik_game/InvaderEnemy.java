package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */

public class InvaderEnemy extends Enemy {
	public InvaderEnemy(TileType[][] shape) {
		super(shape);
		this.size = 10;
		this.isEnemy = true;    // Born evil.
		this.hp = HP;
		this.worth = WORTH;
	}

	private static final int WORTH = 50;
	private static final int HP = 3;
}

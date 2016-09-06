package Game;

/**
 * Created by wassing on 2016-04-04.
 */

public class Player extends Char{

	private int skill;	// Used to determine how hard a new room will be

    public Player(TileType[][] shape, int width, int height) {
		super(shape, width, height);
		this.skill = 1;
    }

	public void incSkill() {
		this.skill =+ 1;
	}

	public int getSkill() {
		return skill;
	}

	public void resetSkill() {
		this.skill = 1;
	}
}
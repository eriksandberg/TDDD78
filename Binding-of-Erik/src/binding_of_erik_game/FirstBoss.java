package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class FirstBoss extends Enemy
{
	public FirstBoss(TileType[][] shape) {
		super(shape);
	    	this.size = 18;
		this.isEnemy = true;	// Born evil.
		this.yCoord = 0;        // All enemies spawn at the top of the room
	    	this.hp = HP;
	    	this.worth = WORTH;
	}

    	public static final int WORTH = 5000;
    	private static final int HP = 50;
}

package binding_of_erik_game;

/**
 * Created by wassing on 2016-10-18.
 */
public class SecondBoss extends Enemy
{
	public SecondBoss(TileType[][] shape) {
		super(shape);
	    	this.size = 20;
		this.isEnemy = true;	// Born evil.
		this.yCoord = 0;        // All enemies spawn at the top of the room
	    	this.hp = HP;
	    	this.worth = WORTH;
	    	this.specialShotCooldown = 100;
	}

    	public static final int WORTH = 10000;
    	private static final int HP = 100;
}
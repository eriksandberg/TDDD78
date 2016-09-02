package Game;

/**
 * Created by wassing on 2016-04-12.
 */

public final class GraphicsFactory
{
    //graphics abbreviations
	// Better names for constants
    private static final TileType T = TileType.TRANSPARENT;
    private static final TileType R = TileType.R; // Red
    private static final TileType B = TileType.B; // Blue
    private static final TileType P = TileType.P; // Pink
    //add more as we continue to define different things

    private int squareWidth = 4; //could perhaps use a getter
    private int squareHeight = 4;

    //an entity is one square, but consists of "subsquares" inside the big square. Always an int, not a float.
    private final int ENTITY_WIDTH = squareWidth * 10;
    private final int ENTITY_HEIGHT = squareHeight * 10;

    public int getEntityWidth() {return ENTITY_WIDTH;}

    public int getEntityHeight() {return ENTITY_HEIGHT;}

    /**
     * Singleton patttern, implemented according bill pugh Private constructor so to ensure only one factory can be
     * instansiated
     */
    private GraphicsFactory() {}

    /**
     * Private class singleton holder, that holds the only instance to the factory
     */
    private static class SingletonHolder
    {
	private static GraphicsFactory INSTANCE = new GraphicsFactory();
    }

    /**
     * Static function that returns the only instance of Graphics factory class. The instance is held by the inner class called
     * SingletonHolder.
     *
     * @return the only instance of GraphicsFactory
     */
    public static GraphicsFactory getInstance() {
	return SingletonHolder.INSTANCE;
    }

    //player is right now a ring of blue pixels.
    private final TileType[][] player =
	    { 		{ T, T, T, B, B, B, B, T, T, T },
				{ T, T, B, T, T, T, T, B, T, T },
				{ T, B, T, T, T, T, T, T, B, T },
		    	{ B, T, T, T, T, T, T, T, T, B },
				{ B, T, T, T, T, T, T, T, T, B },
				{ B, T, T, T, T, T, T, T, T, B },
		    	{ B, T, T, T, T, T, T, T, T, B },
				{ T, B, T, T, T, T, T, T, B, T },
				{ T, T, B, T, T, T, T, B, T, T },
				{ T, T, T, B, B, B, B, T, T, T }};

    public Entity getPlayer() {return new Entity(player, ENTITY_WIDTH, ENTITY_HEIGHT);}


    //enemy is right now a ring of red pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType[][] normalEnemy =
	    { 		{ T, T, T, R, R, R, R, T, T, T },
				{ T, T, R, T, T, T, T, R, T, T },
				{ T, R, T, T, T, T, T, T, R, T },
		    	{ R, T, T, T, T, T, T, T, T, R },
				{ R, T, T, T, T, T, T, T, T, R },
				{ R, T, T, T, T, T, T, T, T, R },
				{ R, T, T, T, T, T, T, T, T, R },
				{ T, R, T, T, T, T, T, T, R, T },
				{ T, T, R, T, T, T, T, R, T, T },
				{ T, T, T, R, R, R, R, T, T, T } };

	// Looks like a space invader but spawn sideways, lmao
	private final TileType[][] invaderEnemy =
		{ 		{ T, T, T, T, T, T, T, T, T, T },
				{ T, T, R, T, T, T, T, R, T, T },
				{ T, T, T, R, T, T, R, T, T, T },
				{ T, T, R, R, R, R, R, R, T, T },
				{ T, R, R, T, R, R, T, R, R, T },
				{ R, R, R, R, R, R, R, R, R, R },
				{ R, T, R, R, R, R, R, R, T, R },
				{ R, T, R, T, T, T, T, R, T, R },
				{ T, T, T, R, T, T, R, T, T, T },
				{ T, T, T, T, T, T, T, T, T, T } };

    //clusterEnemy is a cross of red pixels. Will be shooting 3 projectiles at a time. See logic in <Class>
    private final TileType[][] clusterEnemy =
	    { 		{ R, T, T, T, T, T, T, T, T, R },
				{ T, R, T, T, T, T, T, T, R, T },
				{ T, T, R, T, T, T, T, R, T, T },
				{ T, T, T, R, T, T, R, T, T, T },
				{ T, T, T, T, R, R, T, T, T, R },
				{ T, T, T, T, R, R, T, T, T, T },
				{ T, T, T, R, T, T, R, T, T, T },
				{ T, T, R, T, T, T, T, R, T, T },
				{ T, R, T, T, T, T, T, T, R, T },
				{ R, T, T, T, T, T, T, T, T, R } };

	public Entity getNormalEnemy() {return new Entity(normalEnemy, ENTITY_WIDTH, ENTITY_HEIGHT);}

	public Entity getInvaderEnemy() {return new Entity(invaderEnemy, ENTITY_WIDTH, ENTITY_HEIGHT);}

    public Entity getClusterEnemy() {return new Entity(clusterEnemy, ENTITY_WIDTH, ENTITY_HEIGHT);}

    private final TileType[][] lightShot =
		{		{ P, P},
				{ P, P} };

    public Entity getLightShot() {return new Entity(lightShot, ENTITY_WIDTH, ENTITY_HEIGHT);}

	public Shot getLightShot2() {return new Shot(lightShot, ENTITY_WIDTH, ENTITY_HEIGHT);}

    //add more graphic "blocks" here.
}
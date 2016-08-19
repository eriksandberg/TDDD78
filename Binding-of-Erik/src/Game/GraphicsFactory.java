package Game;

/**
 * Created by wassing on 2016-04-12.
 */

public class GraphicsFactory
{
    //graphics abbreviations
    private static final TileType T = TileType.TRANSPARENT;
    private static final TileType R = TileType.R;
    private static final TileType B = TileType.B;
    private static final TileType P = TileType.P;
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

    //player is right now a ring of blue pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType[][] player =
	    { { T, T, T, B, B, B, B, T, T, T }, { T, T, B, T, T, T, T, B, T, T }, { T, B, T, T, T, T, T, T, B, T },
		    { B, T, T, T, T, T, T, T, T, B }, { B, T, T, T, T, T, T, T, T, B }, { B, T, T, T, T, T, T, T, T, B },
		    { B, T, T, T, T, T, T, T, T, B }, { T, B, T, T, T, T, T, T, B, T }, { T, T, B, T, T, T, T, B, T, T },
		    { T, T, T, B, B, B, B, T, T, T } };

    public Entity getPlayer() {return new Entity(player, ENTITY_WIDTH, ENTITY_HEIGHT);}

    //enemy is right now a ring of red pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType[][] enemy =
	    { { T, T, T, R, R, R, R, T, T, T }, { T, T, R, T, T, T, T, R, T, T }, { T, R, T, T, T, T, T, T, R, T },
		    { R, T, T, T, T, T, T, T, T, R }, { R, T, T, T, T, T, T, T, T, R }, { R, T, T, T, T, T, T, T, T, R },
		    { R, T, T, T, T, T, T, T, T, R }, { T, R, T, T, T, T, T, T, R, T }, { T, T, R, T, T, T, T, R, T, T },
		    { T, T, T, R, R, R, R, T, T, T } };

    public Entity getEnemy() {return new Entity(enemy, ENTITY_WIDTH, ENTITY_HEIGHT);}

    private final TileType[][] lightShot =
	    { { P, P}, { P, P} };

    public Entity getLightShot() {return new Entity(lightShot, ENTITY_WIDTH, ENTITY_HEIGHT);}

    //add more graphic "blocks" here.
}
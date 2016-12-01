package master_of_space;

/**
 * Created by wassing on 2016-04-12.
 */

@SuppressWarnings("ConstantNamingConvention")  // For the initial constants, long names look bad in the arrays
public final class GraphicsFactory
{
    //graphics abbreviations
    private static final TileType R = TileType.RED; // Red
    private static final TileType B = TileType.BLUE; // Blue
    private static final TileType T = TileType.BLACK; // Black / Transparent
    private static final TileType Y = TileType.YELLOW; // Yellow
    private static final TileType P = TileType.MAGENTA; // Pink
    private static final TileType O = TileType.ORANGE; //Orange
    private static final TileType G = TileType.GRAY; //Gray
    private static final TileType W = TileType.WHITE; //White
    private static final TileType Gr = TileType.GREEN; //Green
    // More available if wanted, see TileType
    private int bossNumber = 0;

    /**
     * Singleton patttern, implemented according bill pugh Private constructor so to ensure only one factory can be
     * instansiated
     */
    private GraphicsFactory() {}

    /**
     * Private class singleton holder, that holds the only instance to the factory
     */
    private static final class SingletonHolder
    {
	private static final GraphicsFactory INSTANCE = new GraphicsFactory();
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

    // All objects should preferably take up all of their allowed space for collisions to look better
    private final TileType[][] player =
		{{ T, T, T, G, G, T, T, T, T, T },
		 { T, T, W, G, G, G, G, G, T, T },
		 { T, T, W, T, T, T, T, T, T, T },
		 { T, W, W, W, T, T, T, T, T, T },
		 { O, O, G, G, W, W, W, W, W, W },
		 { O, O, G, G, W, W, W, W, W, W },
		 { T, W, W, W, T, T, T, T, T, T },
		 { T, T, W, T, T, T, T, T, T, T },
		 { T, T, W, G, G, G, G, G, T, T },
		 { T, T, T, G, G, T, T, T, T, T }};

    public Player getPlayer() {return new Player(player, 100, 10);}

    // What kind (kind) of enemy is decided by game logic in Room
    public Enemy getEnemy(int kind) {
	    switch (kind) {
		    case 1:
			    return new Enemy(normalEnemy, 10, 1, 100, 0);
		    case 2:
			    return new TravelingEnemy(travelingEnemy);
		    case 3:
			    return new InvaderEnemy(invaderEnemy);
		    case 4:
			    bossNumber++;
			    switch (bossNumber){
				    case 1:
					    return new FirstBoss(starDestroyer);
				    case 2:
					    return new SecondBoss(deathStar);
				    //add more bosses here
				    default:
					    return new Enemy(nullEnemy, 0, 0, 0, 0);
			    }
		    default:
		    return new Enemy(nullEnemy, 0, 0, 0, 0);
	    }
    }

    private final TileType[][] nullEnemy = {};

    //your normal average joe.
    private final TileType[][] normalEnemy =
	    {{ T, T, T, R, R, R, R, T, T, T },
		 { T, T, R, T, T, T, T, R, T, T },
		 { T, R, T, T, T, T, T, T, R, T },
		 { R, T, T, T, T, T, T, T, T, R },
		 { R, T, T, T, T, T, T, T, T, R },
		 { R, T, T, T, T, T, T, T, T, R },
		 { R, T, T, T, T, T, T, T, T, R },
		 { T, R, T, T, T, T, T, T, R, T },
		 { T, T, R, T, T, T, T, R, T, T },
		 { R, R, R, R, R, R, R, R, R, R }};

    //travels at the sides, has a short lifespan.
    private final TileType[][] travelingEnemy =
	    {{ T, T, T, R, R, R, R, T, T, T },
	     { T, T, R, T, T, T, T, R, T, T },
	     { T, R, T, T, T, T, T, T, R, T },
	     { R, B, B, B, B, B, B, B, B, R },
	     { R, T, T, T, T, T, T, T, T, R },
	     { R, T, T, T, T, T, T, T, T, R },
	     { R, B, B, B, B, B, B, B, B, R },
	     { T, R, T, T, T, T, T, T, R, T },
	     { T, T, R, T, T, T, T, R, T, T },
	     { T, T, T, R, R, R, R, T, T, T }};

    // Space invader from the classic arcade game. Not used.
    private final TileType[][] invaderEnemy =
	    {{ T, T, T, T, T, T, T, T, T, T },
	     { T, T, R, T, T, T, T, R, T, T },
	     { T, T, T, R, T, T, R, T, T, T },
	     { T, T, R, R, R, R, R, R, T, T },
	     { T, R, R, T, R, R, T, R, R, T },
	     { R, R, R, R, R, R, R, R, R, R },
	     { R, T, R, R, R, R, R, R, T, R },
	     { R, T, R, T, T, T, T, R, T, R },
	     { T, T, T, R, T, T, R, T, T, T },
	     { T, T, T, T, T, T, T, T, T, T }};

    private final TileType[][] lightShot =
		{{ P, P},
		 { P, P}};

    public Shot getLightShot() {return new Shot(lightShot);}

    private final TileType[][] lazer =
	    {{ Gr, Gr},
	     { Gr, Gr}};

    public Shot getLazer() {return new Shot(lazer);}

    private final TileType[][] playerShot =
		{{ Y, Y},
		 { Y, Y}};

    public StraightShot getPlayerShot() {return new StraightShot(playerShot);}

    private final TileType[][] spark =
		{{ Y }};

    public Spark getSpark() {return new Spark(spark);}

    //eye candy
    private final TileType[][] star =
	    {{ W, W} };

    public Star getStar() {return new Star(star);}

    //eye candy
    private final TileType[][] galaxy =
	    {{ T, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, Y, T, T, T, T },
	    { T, T, T, T, T, T, Y, T, T, T },
	    { T, T, W, W, T, T, Y, T, T, T },
	    { T, W, T, T, W, Y, T, T, T, T },
	    { T, T, T, T, Y, W, T, T, W, T },
	    { T, T, T, Y, T, T, W, W, T, T },
	    { T, T, T, Y, T, T, T, T, T, T },
	    { T, T, T, T, Y, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, T }};

    public Galaxy getGalaxy() {return new Galaxy(galaxy);}

    private final TileType[][] starDestroyer = //star destroyer, 20x20 array but only 18x18 is being used, therefore size = 20.
	    {{ T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, B, B, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, G, G, G, G, G, G, G, G, T, T, T, T, T, T },
	    { T, T, T, G, G, G, G, G, G, T, T, G, G, G, G, G, G, T, T, T },
	    { T, T, T, G, G, G, G, G, T, T, T, T, G, G, G, G, G, T, T, T },
	    { T, T, T, T, G, G, G, W, G, G, G, G, W, G, G, G, T, T, T, T },
	    { T, T, T, T, G, G, G, W, G, G, G, G, W, G, G, G, T, T, T, T },
	    { T, T, T, T, T, G, G, W, W, G, G, W, W, G, G, T, T, T, T, T },
	    { T, T, T, T, T, G, G, G, W, G, G, W, G, G, G, T, T, T, T, T },
	    { T, T, T, T, T, T, G, G, W, W, W, W, G, G, T, T, T, T, T, T },
	    { T, T, T, T, T, T, G, G, W, W, W, W, G, G, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, G, G, W, W, G, G, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, G, G, W, W, G, G, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, G, W, W, G, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, G, G, G, G, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, G, G, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, G, G, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T },
	    { T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T, T },};

    private final TileType[][] deathStar =
	    {{ T, T, T, T, T, T, T, G, G, G, G, G, G, T, T, T, T, T, T, T },
	    { T, T, T, T, T, G, G, G, G, G, G, G, G, G, G, T, T, T, T, T },
	    { T, T, T, T, G, G, G, G, G, G, G, G, G, G, G, G, T, T, T, T },
	    { T, T, T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T, T, T },
	    { T, T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T, T },
	    { T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T },
	    { T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G },
	    { T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T },
	    { T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T },
	    { T, T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T, T },
	    { T, T, T, G, G, G, G, G, G, G, G, G, G, G, G, G, G, T, T, T },
	    { T, T, T, T, G, G, G, G, G, G, G, G, G, G, G, G, T, T, T, T },
	    { T, T, T, T, T, G, G, G, G, G, G, G, G, G, G, T, T, T, T, T },
	    { T, T, T, T, T, T, T, G, G, G, G, G, G, T, T, T, T, T, T, T },};
}
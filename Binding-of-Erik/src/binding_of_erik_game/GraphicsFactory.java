package binding_of_erik_game;

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
    private static final TileType Gr = TileType.GRAY; //Gray
    private static final TileType W = TileType.WHITE; //White
    // More available if wanted, see TileType

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
		{{ T, T, T, Gr, Gr, T, T, T, T, T },
		 { T, T, W, Gr, Gr, Gr, Gr, Gr, T, T },
		 { T, T, W, T, T, T, T, T, T, T },
		 { T, W, W, W, T, T, T, T, T, T },
		 { O, O, Gr, Gr, W, W, W, W, W, W }, //north
		 { O, O, Gr, Gr, W, W, W, W, W, W }, //north
		 { T, W, W, W, T, T, T, T, T, T },
		 { T, T, W, T, T, T, T, T, T, T },
		 { T, T, W, Gr, Gr, Gr, Gr, Gr, T, T },
		 { T, T, T, Gr, Gr, T, T, T, T, T }};

    public Player getPlayer() {return new Player(player);}

	// What kind (kind) of enemy is decided by game logic in Room
	public Enemy getEnemy(int kind) {
		switch (kind) {
			case 1:
				return new Enemy(normalEnemy);
			case 2:
				return new TravelingEnemy(travelingEnemy);
			default:
			return new Enemy(nullEnemy);
		}
	}

	private final TileType[][] nullEnemy = {};

    //enemy is right now a ring of red pixels. transparent blocks will be detected so they don't overwrite ground.
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

	// Looks like a space invader but spawn sideways, lmao
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

    //clusterEnemy is a cross of red pixels. Will be shooting 3 projectiles at a time. See logic in <Class>
    private final TileType[][] clusterEnemy =
		{{ R, T, T, T, T, T, T, T, T, R },
		 { T, R, T, T, T, T, T, T, R, T },
		 { T, T, R, T, T, T, T, R, T, T },
		 { T, T, T, R, T, T, R, T, T, T },
		 { T, T, T, T, R, R, T, T, T, T },
		 { T, T, T, T, R, R, T, T, T, T },
		 { T, T, T, R, T, T, R, T, T, T },
		 { T, T, R, T, T, T, T, R, T, T },
		 { T, R, T, T, T, T, T, T, R, T },
		 { R, T, T, T, T, T, T, T, T, R }};

    private final TileType[][] lightShot =
		{{ P, P},
		 { P, P}};

    public Shot getLightShot() {return new Shot(lightShot);}

    private final TileType[][] playerShot =
		{{ Y, Y},
		 { Y, Y}};

    public StraightShot getPlayerShot() {return new StraightShot(playerShot);}

    private final TileType[][] spark =
		{{ Y }};

    public Spark getSpark() {return new Spark(spark);}

	private final TileType[][] star =
		{{ W, W, W, W, W, W, W, W, W, W}};

	public Star getStar() {return new Star(star);}

    //add more graphic "blocks" here.
}
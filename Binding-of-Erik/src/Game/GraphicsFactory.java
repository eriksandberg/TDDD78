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
    //add more as we continue to define different things

    //private final Room room; //this needs to be instantiated so that getters work.

    private int pixelWidth = 40; //use getter
    private int pixelHeight = 40; //use getter

    //an entity is one square, but consists of "subsquares" inside the big square. Always an int, not a float.
    private final int ENTITY_WIDTH = pixelWidth / 10;
    private final int ENTITY_HEIGHT = pixelHeight / 10;

    public int getEntityWidth(){return ENTITY_WIDTH;}
    public int getEntityHeight(){return ENTITY_HEIGHT;}

    //player is right now a ring of blue pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType[][] player = {{T,T,T,B,B,B,B,T,T,T},{T,T,B,T,T,T,T,B,T,T},{T,B,T,T,T,T,T,T,B,T},
	    {B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B},
	    {T,B,T,T,T,T,T,T,B,T},{T,T,B,T,T,T,T,B,T,T},{T,T,T,B,B,B,B,T,T,T}
    };

    public TileHandler getPlayer(){return new TileHandler(player, ENTITY_WIDTH, ENTITY_HEIGHT);}

    //enemy is right now a ring of red pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType[][] enemy = {{T,T,T,R,R,R,R,T,T,T},{T,T,R,T,T,T,T,R,T,T},{T,R,T,T,T,T,T,T,R,T},
	    {R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},
	    {T,R,T,T,T,T,T,T,R,T},{T,T,R,T,T,T,T,R,T,T},{T,T,T,R,R,R,R,T,T,T}
    };

    public TileHandler getEnemy(){return new TileHandler(enemy, ENTITY_WIDTH, ENTITY_HEIGHT);}


}

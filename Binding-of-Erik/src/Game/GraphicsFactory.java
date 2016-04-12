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
    private Room room;
    //add more as we continue to define different things

    private int pixelWidth = room.getPixelsPerWidth();
    private int pixelHeight = room.getPixelsPerHeight();

    //an entity is one square, but consists of "subsquares" inside the big square. Always an int, not a float.
    private final int ENTITYWIDTH = pixelWidth / 10;
    private final int ENTITYHEIGHT = pixelHeight / 10;

    public int getEntityWidth(){return ENTITYWIDTH;}
    public int getEntityHeight(){return ENTITYHEIGHT;}

    //player is right now a ring of blue pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType Player[][] = {{T,T,T,B,B,B,T,T,T},{T,T,B,T,T,T,T,B,T,T},{T,B,T,T,T,T,T,T,B,T},
	    {B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B},{B,T,T,T,T,T,T,T,T,B}, {T,B,T,T,T,T,T,T,B,T},
	    {T,T,B,T,T,T,T,B,T,T},{T,T,T,B,B,B,B,T,T,T}
    };

    public TileHandler getPlayer(){return new TileHandler(Player, ENTITYWIDTH, ENTITYHEIGHT);}

    //enemy is right now a ring of red pixels. transparent blocks will be detected so they don't overwrite ground.
    private final TileType Enemy[][] = {{T,T,T,R,R,R,R,T,T,T},{T,T,R,T,T,T,T,R,T,T},{T,R,T,T,T,T,T,T,R,T},
	    {R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},{R,T,T,T,T,T,T,T,T,R},
	    {T,R,T,T,T,T,T,T,R,T},{T,T,R,T,T,T,T,R,T,T},{T,T,T,R,R,R,R,T,T,T}
    };

    public TileHandler getEnemy(){return new TileHandler(Enemy, ENTITYWIDTH, ENTITYHEIGHT);}
}

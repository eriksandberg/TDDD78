package Game;

/**
 * Created by wassing on 2016-04-12.
 */
public class GraphicsFactory
{
    private final int ENTITYHEIGHT = 10; //all entities fit in one "board square", but they can move around somewhat freely.
    private final int ENTITYWIDTH = 10;
    private static final TileType T = TileType.TRANSPARENT;
    private static final TileType R = TileType.R;
    private static final TileType B = TileType.B;
    //add more as we continue to define different things

    public int getEntityHeight() {return ENTITYHEIGHT;}
    public int getEntityWidth(){return ENTITYWIDTH;}

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

package binding_of_erik_game;

import java.util.EnumMap;
import java.awt.Color;
/**
 * Created by wassing on 2016-04-04.
 */
public enum TileType { //right now a copypaste from tetris, but easy to modify depending on our needs.
    EMPTY, CYAN, BLUE, BLACK, ORANGE, YELLOW, GREEN, MAGENTA, RED, GRAY, WHITE, TRANSPARENT;

    private static final Color transparent = new Color(0,0,0,0); // will probably be used as an identifier to ensure "smooth" graphics

    static EnumMap eMap(){
	EnumMap<TileType, Color> map = new EnumMap<>(TileType.class);
	map.put(TileType.EMPTY, Color.white);
	map.put(TileType.CYAN, Color.cyan);
	map.put(TileType.BLUE, Color.blue);
	map.put(TileType.BLACK, Color.black);
	map.put(TileType.ORANGE, Color.orange);
	map.put(TileType.YELLOW, Color.yellow);
	map.put(TileType.GREEN, Color.green); //grass
	map.put(TileType.MAGENTA, Color.magenta); //purple
	map.put(TileType.RED, Color.red);
	map.put(TileType.GRAY, Color.gray);
	map.put(TileType.WHITE, Color.white);
	map.put(TileType.TRANSPARENT, transparent); //instead of using transparent we should use a getter or similar
	//using a getter helps us not overwrite what currently exists on the specified pixel.
	return map;
    }
}

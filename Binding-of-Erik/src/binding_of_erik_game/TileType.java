package binding_of_erik_game;

import java.util.EnumMap;
import java.awt.Color;

/**
 * Created by wassing on 2016-04-04.
 * EnumMap ontaining all colours in use, imported from awt.Color.
 */

@SuppressWarnings("JavaDoc") // To explain that CYAN means CYAN in addition to the info in the class JavaDoc feels redundant
public enum TileType {

	CYAN, BLUE, BLACK, ORANGE, YELLOW, GREEN, MAGENTA, RED, GRAY, WHITE, TRANSPARENT;

    private static final Color C_TRANSPARENT = new Color(0,0,0,0); // will probably be used as an identifier to ensure "smooth" graphics

    static EnumMap<TileType, Color> eMap(){
		EnumMap<TileType, Color> map = new EnumMap<>(TileType.class);
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
		map.put(TileType.TRANSPARENT, C_TRANSPARENT); //useful as temp, if needed.
		return map;
    }
}

package Game;

import java.util.EnumMap;
import java.awt.Color;
/**
 * Created by wassing on 2016-04-04.
 */
public enum TileType { //right now a copypaste from tetris, but easy to modify depending on our needs.
    EMPTY, CYAN, B, L, Y, G, P, R, OUTSIDE, TRANSPARENT;

    private static Color transparent = new Color(0,0,0,0); // will probably be used as an identifyer to ensure "smooth" graphics

    static EnumMap eMap(){
	EnumMap<TileType, Color> map = new EnumMap<TileType, Color>(TileType.class);
	map.put(TileType.EMPTY, Color.white);
	map.put(TileType.CYAN, Color.cyan);
	map.put(TileType.B, Color.blue);
	map.put(TileType.L, Color.orange);
	map.put(TileType.Y, Color.yellow);
	map.put(TileType.G, Color.green); //grass
	map.put(TileType.P, Color.magenta); //purple
	map.put(TileType.R, Color.red);
	map.put(TileType.OUTSIDE, Color.gray);
	map.put(TileType.TRANSPARENT, transparent);
	return map;
    }
}

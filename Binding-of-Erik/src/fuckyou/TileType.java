package fuckyou;

import java.util.EnumMap;
import java.awt.Color;
/**
 * Created by wassing on 2016-04-04.
 */
public enum TileType { //right now a copypaste from tetris, but easy to modify depending on our needs.
    I, J, L, O, S, T, Z, EMPTY, OUTSIDE;

    static EnumMap eMap(){
	EnumMap<TileType, Color> map = new EnumMap<TileType, Color>(TileType.class);
	map.put(TileType.EMPTY, Color.white);
	map.put(TileType.I, Color.cyan);
	map.put(TileType.J, Color.blue);
	map.put(TileType.L, Color.orange);
	map.put(TileType.O, Color.yellow);
	map.put(TileType.S, Color.green);
	map.put(TileType.T, Color.magenta);
	map.put(TileType.Z, Color.red);
	map.put(TileType.OUTSIDE, Color.gray);
	return map;
    }
}

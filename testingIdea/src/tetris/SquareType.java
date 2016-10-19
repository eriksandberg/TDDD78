package tetris;

import java.util.AbstractMap;
import java.util.EnumMap;
import java.awt.Color;
/**
 *
 * Enum type for the different colours our squares can have.
 */
@SuppressWarnings({ "JavaDoc", "EnumeratedConstantNamingConvention" })    // The single letters represent the shape of the block they represent
public enum SquareType {
    I, J, L, O, S, T, Z, EMPTY, OUTSIDE, TRANSPARENT;

    private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);

    static AbstractMap eMap(){
        AbstractMap<SquareType, Color> map = new EnumMap<>(SquareType.class);
            map.put(SquareType.EMPTY, Color.white);
            map.put(SquareType.I, Color.cyan);
            map.put(SquareType.J, Color.blue);
            map.put(SquareType.L, Color.orange);
            map.put(SquareType.O, Color.yellow);
            map.put(SquareType.S, Color.green);
            map.put(SquareType.T, Color.magenta);
            map.put(SquareType.Z, Color.red);
            map.put(SquareType.OUTSIDE, Color.gray);
	    map.put(SquareType.TRANSPARENT, COLOR_TRANSPARENT);
        return map;
    }
}
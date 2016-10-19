package tetris;

import java.util.EnumMap;
import java.awt.Color;
/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-09-30
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public enum SquareType {
    I, J, L, O, S, T, Z, EMPTY, OUTSIDE, TRANSPARENT;

    private static Color transparent = new Color(0,0,0,0);

    static EnumMap eMap(){
        EnumMap<SquareType, Color> map = new EnumMap<>(SquareType.class);
            map.put(SquareType.EMPTY, Color.white);
            map.put(SquareType.I, Color.cyan);
            map.put(SquareType.J, Color.blue);
            map.put(SquareType.L, Color.orange);
            map.put(SquareType.O, Color.yellow);
            map.put(SquareType.S, Color.green);
            map.put(SquareType.T, Color.magenta);
            map.put(SquareType.Z, Color.red);
            map.put(SquareType.OUTSIDE, Color.gray);
	    map.put(SquareType.TRANSPARENT, transparent);
        return map;
    }
}
package tetris;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-09-30
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */

public class TetrominoMaker {

    private final int[] polyHeight = { 4, 3, 3, 2, 3, 3, 3 };
    private final int[] polyWidth = { 4, 3, 3, 2, 3, 3, 3 };

    private final SquareType[][][]
	    polyTypes = {{{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}, {SquareType.I, SquareType.I, SquareType.I, SquareType.I},
            {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.EMPTY,SquareType.EMPTY, SquareType.J},{SquareType.J, SquareType.J, SquareType.J},
            {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.L, SquareType.L, SquareType.L}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.L},
            {SquareType.EMPTY,SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.O, SquareType.O},{SquareType.O, SquareType.O}},
            {{SquareType.EMPTY, SquareType.EMPTY, SquareType.S},{SquareType.EMPTY, SquareType.S, SquareType.S}, {SquareType.EMPTY, SquareType.S, SquareType.EMPTY}},
            {{SquareType.EMPTY, SquareType.EMPTY, SquareType.T},{SquareType.EMPTY, SquareType.T, SquareType.T}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.T}},
            {{SquareType.EMPTY, SquareType.Z, SquareType.EMPTY},{SquareType.EMPTY, SquareType.Z, SquareType.Z}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.Z}}};

    public Poly getPoly(int n){
        return new Poly(polyTypes[n], polyWidth[n], polyHeight[n]);
    }
}

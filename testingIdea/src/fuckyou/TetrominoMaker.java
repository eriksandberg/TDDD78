package fuckyou;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-09-30
 * Time: 18:38
 * To change this template use File | Settings | File Templates.
 */

public class TetrominoMaker {

    private final int blockTypes = 7;
    private final int PolyHeight[] = {4,3,3,2,3,3,3};
    private final int PolyWidth[] = {4,3,3,2,3,3,3};

    private final SquareType PolyTypes[][][] = {{{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}, {SquareType.I, SquareType.I, SquareType.I, SquareType.I},
            {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.EMPTY,SquareType.EMPTY, SquareType.J},{SquareType.J, SquareType.J, SquareType.J},
            {SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.L, SquareType.L, SquareType.L}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.L},
            {SquareType.EMPTY,SquareType.EMPTY, SquareType.EMPTY}},
            {{SquareType.O, SquareType.O},{SquareType.O, SquareType.O}},
            {{SquareType.EMPTY, SquareType.EMPTY, SquareType.S},{SquareType.EMPTY, SquareType.S, SquareType.S}, {SquareType.EMPTY, SquareType.S, SquareType.EMPTY}},
            {{SquareType.EMPTY, SquareType.EMPTY, SquareType.T},{SquareType.EMPTY, SquareType.T, SquareType.T}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.T}},
            {{SquareType.EMPTY, SquareType.Z, SquareType.EMPTY},{SquareType.EMPTY, SquareType.Z, SquareType.Z}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.Z}}};

    /*private SquareType PolyTypes[][][] = {{{SquareType.I, SquareType.I, SquareType.I, SquareType.I}},{{SquareType.EMPTY,SquareType.EMPTY, SquareType.J},{SquareType.J, SquareType.J, SquareType.J}},
            {{SquareType.L, SquareType.L, SquareType.L}, {SquareType.EMPTY, SquareType.EMPTY, SquareType.L}},{{SquareType.O, SquareType.O},{SquareType.O, SquareType.O}},
            {{SquareType.EMPTY, SquareType.S},{SquareType.S, SquareType.S}, {SquareType.S, SquareType.EMPTY}},
            {{SquareType.EMPTY, SquareType.T},{SquareType.T, SquareType.T}, {SquareType.EMPTY, SquareType.T}},
            {{SquareType.Z, SquareType.EMPTY},{SquareType.Z, SquareType.Z}, {SquareType.EMPTY, SquareType.Z}}};*/

    public Poly getPoly(int n){
        return new Poly(PolyTypes[n], PolyWidth[n], PolyHeight[n]);
    }

    public int getNumberOfTypes(){

        //Could be deleted, but could be good to have for randomize function if one ever were to use that.
        return blockTypes;
    }
}

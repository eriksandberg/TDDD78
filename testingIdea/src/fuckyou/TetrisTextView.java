package fuckyou;//package Labb2;

/**
 * User: Daniel
 * Date: 28/09/13
 */

import java.util.Random;

public class TetrisTextView {
    // {I, J, L, O, S, T, Z, EMPTY, OUTSIDE}

    public static String convertToText(Board board){

        //Was used in earlier assignment

        StringBuilder stringBuilder = new StringBuilder();
        SquareType square;

        for (int j = 0; j < board.getRows(); j++) {
            for (int i = 0; i < board.getColumns(); i++){

                if(board.getTetrisPiece() != null){
                    try{
                        square = board.getTetrisPiece().getShape()[i - board.getTetrisPieceX()][j - board.getTetrisPieceY()];
                        if(square.equals(SquareType.EMPTY)){
                            square = board.getSquare(i, j);
                        }
                    }catch(RuntimeException e){
                        square = board.getSquare(i, j);
                    }
                }else{
                    square = board.getSquare(i, j);
                }


                stringBuilder.append(squareTypeToSymbol(square));
                stringBuilder.append(' ');
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static Character squareTypeToSymbol(SquareType square){
        switch (square){
            case I:
                return '#';
            case J:
                return '/';
            case L:
                return '@';
            case O:
                return '*';
            case S:
                return '$';
            case T:
                return '<';
            case Z:
                return '>';
            case OUTSIDE:
                return 'O';
            case EMPTY:
                return '-';
            default:
                return '?';
        }
    }
}
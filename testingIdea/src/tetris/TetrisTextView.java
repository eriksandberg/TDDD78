package tetris;//package Labb2;

/**
 * User: Daniel
 * Date: 28/09/13
 * Was used in earlier assignment
 */

@SuppressWarnings("unused")     // Let's keep this around
public final class TetrisTextView {
    private TetrisTextView() {}

    public static String convertToText(Board board){

        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < board.getRows(); j++) {
            for (int i = 0; i < board.getColumns(); i++){

                SquareType square;
                if(board.getTetrisPiece() != null){
                    try{
                        square = board.getTetrisPiece().getShape()[i - board.getTetrisPieceX()][j - board.getTetrisPieceY()];
                        if(square.equals(SquareType.EMPTY)){
                            square = board.getSquare(i, j);
                        }
                    }catch(RuntimeException ignored){
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

    private static char squareTypeToSymbol(SquareType square){
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
            case TRANSPARENT:
                return '=';
            default:
                return '?';
        }
    }
}
package tetris;

/**
 * User: Daniel
 * Date: 28/09/13
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Board {

    //private static final int default_width = 10;
    //private static final int default_height = 22;
    private int width = 0;
    private int height = 0;

    private Poly tetrisPiece = null;
    private int tetrisPieceX;
    private int tetrisPieceY;
    private int score;

    private final SquareType[][] squareArray;

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    private boolean gameOver = false;

    // Default constructor
    /*public Board(){
        this(default_width, default_height);
    }*/

    // Constructor for custom board
    public Board(int width, int height){
        this.height = height;
        this.width = width;

        squareArray = new SquareType[width][height];
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                squareArray[j][i] = SquareType.EMPTY;
            }
        }
    }

    public void tick(){
        if (gameOver){
            return;
        }

        tetrisPieceY += 1;

        if (tetrisPiece == null){
            if (!(gameOver)){
                spawnTetromino();
            }
            if (blockChecker(tetrisPiece)){
                gameOver = true;
                tetrisPiece = null;
            }
        }
        else if(blockChecker(tetrisPiece)){
            tetrisPieceY -= 1;
            insertPoly(tetrisPiece);
        }
        removeRows();
        notifyListeners();
    }

    public boolean blockChecker(Poly poly){
        if (poly == null){
            return false;
        }

        //Don't have to check for X, only for Y. X only hinders side-movement, Y is the final determinator if we can place the block or not.
        for (int i = 0; i < poly.getWidth(); i++){
            for (int j = 0; j < poly.getHeight(); j++){
                if (poly.getShape()[i][j] != SquareType.EMPTY && (tetrisPieceX+i >= getColumns() || tetrisPieceY+j >= getRows() || tetrisPieceX+i < 0 || tetrisPieceY+j < 0))
                    return true;
                else if (poly.getShape()[i][j] != SquareType.EMPTY && squareArray[(i+tetrisPieceX)][(j+tetrisPieceY)] != SquareType.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    public void insertPoly(Poly poly){
        //Store the piece in the actual board
        for (int i = 0; i < poly.getWidth(); i++){
            for (int j = 0; j < poly.getHeight(); j++){
                if (i+tetrisPieceX >= width || j+tetrisPieceY >= height){
                    //continue; //useless line and if statement
                }

                SquareType type = poly.getShape()[i][j];
                if (type != SquareType.EMPTY){
                    squareArray[i+tetrisPieceX][j+tetrisPieceY] = type;
                }
            }
        }
        tetrisPiece = null;
    }

    public void randomizeBoard()
        //Was used in earlier tasks
    {
        Random generator = new Random();
        SquareType[] variations = new SquareType[9];
        variations[0] = SquareType.I;
        variations[1] = SquareType.O;
        variations[2] = SquareType.T;
        variations[3] = SquareType.S;
        variations[4] = SquareType.Z;
        variations[5] = SquareType.J;
        variations[6] = SquareType.L;
        variations[7] = SquareType.EMPTY;
        variations[8] = SquareType.OUTSIDE;

        for (int i=0; i<height; i++)
        {
            for (int j=0; j<width; j++)
            {
                squareArray[j][i] = variations[generator.nextInt(9)];
            }
        }
    }

    public void spawnTetromino(){
        Random rand = new Random();
        TetrominoMaker newTetro = new TetrominoMaker();
        this.tetrisPiece = newTetro.getPoly(rand.nextInt(7));
        this.tetrisPieceX = (width/2)-1;
        this.tetrisPieceY = 0;
        notifyListeners();
    }

    public int getColumns(){
        return width;
    }

    public int getRows(){
        return height;
    }

    public Poly getTetrisPiece() {
        return tetrisPiece;
    }

    public int getTetrisPieceX() {
        return tetrisPieceX;
    }

    public int getTetrisPieceY() {
        return tetrisPieceY;
    }

    /*public SquareType[][] getSquareArray(){
        return squareArray;
    }*/

    public SquareType getSquare(int x, int y){

        SquareType square;
        if(this.getTetrisPiece() != null){
            try{
                square = this.getTetrisPiece().getShape()[x - this.getTetrisPieceX()][y - this.getTetrisPieceY()];
                if(square.equals(SquareType.EMPTY)){
                    square = squareArray[x][y];
                }
            }catch(RuntimeException e){
                square = squareArray[x][y];
            }
        }else{
            square = squareArray[x][y];
        }
        return square;

    }

    public void addBoardListener(BoardListener bl){
        boardListenerArray.add(bl);
    }

    private void notifyListeners(){
        for (BoardListener boardListener : boardListenerArray) {
            boardListener.BoardChanged();
        }
    }

    public void moveSideways(boolean left){
        if (gameOver){
            return;
        }
        if (left){
            tetrisPieceX -= 1;
            if (blockChecker(tetrisPiece)){
                tetrisPieceX += 1;
            }
        }else{
            tetrisPieceX += 1;
            if (blockChecker(tetrisPiece)){
                tetrisPieceX -= 1;
            }
        }

        notifyListeners();
    }

    public void rotate(boolean b){
        if (gameOver){
            return;
        }
        if(tetrisPiece != null){
            tetrisPiece.rotate(b);
            if(blockChecker(tetrisPiece)){
                tetrisPiece.rotate(!b);
            }
            notifyListeners();
        }
    }

    public void removeRows(){
	int rowsRemovedThisTick = 0;
        boolean remove = true;
        for(int y = 0; y < this.height; y++){
            for(int x = 0; x < this.width; x++){
                if(this.squareArray[x][y].equals(SquareType.EMPTY)){
                    remove = false;
                }
            }
            if(remove){
                rowsRemovedThisTick += 1;
                removeRow(y);
            }
            remove = true;
        }
	switch(rowsRemovedThisTick){
	    case 1:
		score += 100;
		break;
	    case 2:
		score += 300;
		break;
	    case 3:
		score += 500;
		break;
	    case 4:
		score += 800;
		break;
	    default: break;
	}
    }

    public void removeRow(int row){
        for(int y = row; y >= 0; y--){
            for(int x = 0; x < this.width; x++){
                if(y != 0){
                    this.squareArray[x][y] = this.squareArray[x][y-1];
                }else{
                    this.squareArray[x][y] = SquareType.EMPTY;
                }
            }
        }
    }
}
package tetris;

import javax.swing.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Random;

/**
 * User: Daniel Date: 28/09/13
 */

public class Board
{

    private int width = 0;
    private int height = 0;

    private Poly tetrisPiece = null;
    private int tetrisPieceX;
    private int tetrisPieceY;
    private int currentScore = 0;

    private CollisionHandler collisionHandler;
    private static final int FALLTHROUGH_CHANCE = 25;
    private static final int BONUS_CHANCE = 50;

    private final static int ONE_ROW_SCORE = 100;
    private final static int TWO_ROW_SCORE = 300;
    private final static int THREE_ROW_SCORE = 500;
    private final static int FOUR_ROW_SCORE = 800;

    private final SquareType[][] squareArray;

    private final Collection<BoardListener> boardListenerArray = new ArrayList<>();

    protected boolean gameOver = false;

    // Constructor for custom board
    public Board(int width, int height) {
	this.height = height;
	this.width = width;

	squareArray = new SquareType[width][height];
	this.collisionHandler = new DefaultCollisionHandler();
	makeEmptyBoard();
    }

    public void resetBoard() {
	String name = JOptionPane.showInputDialog("What's your name?");
	if (name == null) {
	    name = "";
	}
	Highscore highscore = new Highscore(name, currentScore);
	HighscoreList currentHighscores = HighscoreList.getInstance();
	currentHighscores.addScore(highscore);
	System.out.println(currentHighscores.getHighscoreList());
	gameOver = false;
	makeEmptyBoard();
	currentScore = 0;
    }

    private void makeEmptyBoard() {
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		squareArray[j][i] = SquareType.EMPTY; //fill-er up!
	    }
	}
    }

    public void tick() {
	if (gameOver) {
	    return;
	}

	tetrisPieceY += 1;

	if (tetrisPiece == null) {
	    //noinspection ConstantConditions // Will absolutely not always be true
	    if (!(gameOver)) {
		spawnTetromino();
	    }
	    if (collisionHandler.hasCollision(this)) {
		gameOver = true;
		System.out.println("Game Over!");
		tetrisPiece = null;
	    }
	} else if (collisionHandler.hasCollision(this)) {
	    tetrisPieceY -= 1;
	    insertPoly(tetrisPiece);
	}
	removeRows();
	notifyListeners();
    }

    private void insertPoly(Poly poly) {
	//Store the piece in the actual board
	for (int i = 0; i < poly.getWidth(); i++) {
	    for (int j = 0; j < poly.getHeight(); j++) {

		SquareType type = poly.getShape()[i][j];
		if (type != SquareType.EMPTY) {
		    squareArray[i + tetrisPieceX][j + tetrisPieceY] = type;
		}
	    }
	}
	tetrisPiece = null;
    }

    /**
     * Never used
     */
	/*public void randomizeBoard()
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
    }*/

    private void spawnTetromino() {
	Random rand = new Random();
	TetrominoMaker newTetro = new TetrominoMaker();
	this.tetrisPiece = newTetro.getPoly(rand.nextInt(7));
	this.tetrisPieceX = (width / 2) - 1;
	this.tetrisPieceY = 0;
	int randomPowerUpNumber = rand.nextInt(100);
	//check if we get any power up when we spawn a block.
	if (randomPowerUpNumber < FALLTHROUGH_CHANCE) {
	    collisionHandler = new Fallthrough();
	    System.out.println("Fallthrough block spawned.");
	} else if (randomPowerUpNumber < BONUS_CHANCE) {
	    collisionHandler = new Bonus();
	    System.out.println("Bonus block spawned.");
	} else {
	    collisionHandler = new DefaultCollisionHandler();
	    System.out.println("Normal block spawned.");
	}
	notifyListeners();
    }

    public SquareType getSquareType(int x, int y) {
	return squareArray[(x + tetrisPieceX)][(y + tetrisPieceY)];
    }

    @SuppressWarnings("SuspiciousGetterSetter") 	// tetrisPiece is a poly
    public Poly getPoly() {
	return tetrisPiece;
    }

    @SuppressWarnings("SuspiciousGetterSetter") 	// width = number of columns
    public int getColumns() {
	return width;
    }

    @SuppressWarnings("SuspiciousGetterSetter") 	// height = number of rows
    public int getRows() {
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

    public void awardPoints() {
	currentScore += 1000;
    }

    public void removeSquareAt(int x, int y) {
	squareArray[x][y] = SquareType.EMPTY;
	notifyListeners();
    }

    public SquareType getSquare(int x, int y) {

	SquareType square;
	if (tetrisPiece != null) {
	    try {
		square = tetrisPiece.getShape()[x - tetrisPieceX][y - tetrisPieceY];
		if (square.equals(SquareType.EMPTY)) {
		    square = squareArray[x][y];
		}
	    } catch (RuntimeException ignored) {
		square = squareArray[x][y];
	    }
	} else {
	    square = squareArray[x][y];
	}
	return square;

    }

    public void addBoardListener(BoardListener bl) {
	boardListenerArray.add(bl);
    }

    private void notifyListeners() {
	for (BoardListener boardListener : boardListenerArray) {
	    boardListener.boardChanged();
	}
    }

    public void moveSideways(boolean left) {
	if (gameOver) {
	    return;
	}
	if (left) {
	    tetrisPieceX -= 1;
	    if (collisionHandler.hasCollision(this)) {
		tetrisPieceX += 1;
	    }
	} else {
	    tetrisPieceX += 1;
	    if (collisionHandler.hasCollision(this)) {
		tetrisPieceX -= 1;
	    }
	}

	notifyListeners();
    }

    public void rotate(boolean b) {
	if (gameOver) {
	    return;
	}
	if (tetrisPiece != null) {
	    tetrisPiece.rotate(b);
	    if (collisionHandler.hasCollision(this)) {
		tetrisPiece.rotate(!b);
	    }
	    notifyListeners();
	}
    }

    private void removeRows() {
	int rowsRemovedThisTick = 0;
	boolean remove = true;
	for (int y = 0; y < this.height; y++) {
	    for (int x = 0; x < this.width; x++) {
		if (this.squareArray[x][y].equals(SquareType.EMPTY)) {
		    remove = false;
		}
	    }
	    if (remove) {
		rowsRemovedThisTick += 1;
		removeRow(y);
	    }
	    remove = true;
	}
	switch (rowsRemovedThisTick) {
	    case 1:
		currentScore += ONE_ROW_SCORE;
		break;
	    case 2:
		currentScore += TWO_ROW_SCORE;
		break;
	    case 3:
		currentScore += THREE_ROW_SCORE;
		break;
	    case 4:
		currentScore += FOUR_ROW_SCORE;
		break;
	    default:
		break;
	}
    }

    public int getCurrentScore() { //used by paintcomponent
	return currentScore;
    }

    private void removeRow(int row) {
	for (int y = row; y >= 0; y--) {
	    for (int x = 0; x < this.width; x++) {
		if (y != 0) {
		    this.squareArray[x][y] = this.squareArray[x][y - 1];
		} else {
		    this.squareArray[x][y] = SquareType.EMPTY;
		}
	    }
	}
    }
}
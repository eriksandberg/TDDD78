/**
 * Created by wassing on 2016-10-17.
 */
public class GameBoard
{

    private enum CellType
    {
        EMPTY(false), TREE(true), BUILDING(true), ROCK(true),
        POWERUP(false), BORDER(true);

        public final boolean isObstacle;

        CellType(final boolean isObstacle) {
            this.isObstacle = isObstacle;
        }
    }

    public enum Move {
        DOWN(0,1), UP(0,-1), RIGHT(1,0), LEFT(-1,0);

        public final int deltaX;
        public final int deltaY;

        Move(final int deltaX, final int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    private CellType[][] cells;
    private int currentX, currentY;

    public GameBoard(int width, int height) {
        this.cells = new CellType[height][width];
        this.currentX = width / 2;
        this.currentY = height / 2;
    }

    //move if we can move.
    public void move(Move distance){
	if (canMove(distance)){
	    currentX += distance.deltaX;
	    currentY += distance.deltaY;
	}
    }

    //bounds checker.
    public boolean canMove(Move distance){
	return !cells[currentY + distance.deltaY][currentX + distance.deltaX].isObstacle;
    }
}

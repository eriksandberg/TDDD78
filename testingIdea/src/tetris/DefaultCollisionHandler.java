package tetris;

/**
 * Created by wassing on 2016-10-18.
 */

public class DefaultCollisionHandler implements CollisionHandler {
	public boolean hasCollision(Board board) {

		Poly poly = board.getPoly();

		if (poly == null) {
			return false;
		}
		//Don't have to check for X, only for Y. X only hinders side-movement, Y is the final determinator if we can place the block or not.
		for (int i = 0; i < poly.getWidth(); i++) {      // Loop over width of a poly
			for (int j = 0; j < poly.getHeight(); j++) {     // Loop over height
				if ((poly.getShape()[i][j] != SquareType.EMPTY) &&  // Our current j + i combo is not empty space
					(board.getTetrisPieceX() + i >= board.getColumns() ||     // Utanför höger kant
					 board.getTetrisPieceY() + j >= board.getRows() ||         // Över taket
					 board.getTetrisPieceX() + i < 0 ||                        // Är vi utanför vänster kant
					 board.getTetrisPieceY() + j < 0))                         // Under golvet
					return true;
				else if (poly.getShape()[i][j] != SquareType.EMPTY &&
					board.getSquareType(i, j) != SquareType.EMPTY) {     // i and j get added to
					return true;
				}
			}
		}
		return false;
	}
}

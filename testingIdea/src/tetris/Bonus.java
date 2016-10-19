package tetris;

/**
 * Created by wassing on 2016-10-19.
 *
 */
public class Bonus implements CollisionHandler {

    	private CollisionHandler defaultHandler = new DefaultCollisionHandler();
	public boolean hasCollision(Board board) {

		Poly poly = board.getPoly();

		if (poly == null) {
			return false;
		}
	    	for (int i = 0; i < poly.getWidth(); i++) {      // Loop over width of a poly
			for (int j = 0; j < poly.getHeight(); j++) {     // Loop over height
				if ((poly.getShape()[i][j] != SquareType.EMPTY) &&  // Our current j + i combo is not empty space

								(board.getTetrisPieceX() + i >= board.getColumns() ||     // Utanför höger kant
								 board.getTetrisPieceY() + j >= board.getRows() ||         // Över taket
								 board.getTetrisPieceX() + i < 0)) {                       // Är vi utanför vänster kant
					return true;
				} else if (board.getTetrisPieceY() + j < 0) {
					board.awardPoints();
					return true;
				} else if (poly.getShape()[i][j] != SquareType.EMPTY &&
							board.getSquareType(i, j) != SquareType.EMPTY) {
					board.awardPoints();    // We hit something
					return true;
				}
			}
		}
		return false;
	}
}
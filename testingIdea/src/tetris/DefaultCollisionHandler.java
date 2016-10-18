package tetris;

/**
 * Created by wassing on 2016-10-18.
 */
public class DefaultCollisionHandler implements CollisionHandler {
	public boolean hasCollision(Board board) {

		Poly poly = board.getPoly();

		SquareType[][] squareArray = board.getSquareArray();

		if (poly == null){
			return false;
		}

		//Don't have to check for X, only for Y. X only hinders side-movement, Y is the final determinator if we can place the block or not.
		for (int i = 0; i < poly.getWidth(); i++){      // Loop over width of a poly
			for (int j = 0; j < poly.getHeight(); j++){     // Loop over height
				if ((poly.getShape()[i][j] != SquareType.EMPTY) &&  // Our current j + i combo is not empty space

								(board.getTetrisPieceX()+i >= board.getColumns() ||     // Utanför höger kant
								board.getTetrisPieceY()+j >= board.getRows() ||         // Över taket
								board.getTetrisPieceX()+i < 0 ||                        // Är vi utanför vänster kant
								board.getTetrisPieceY()+j < 0))                         // Under golvet
					return true;
				else if (poly.getShape()[i][j] != SquareType.EMPTY &&
						squareArray[(i+board.getTetrisPieceX())][(j+board.getTetrisPieceY())] != SquareType.EMPTY){     //???
					return true;
				}
			}
		}
		return false;










		/*System.out.println("asdf");
		for (int i = board.getTetrisPieceY(); i < board.getTetrisPieceY() + board.getTetrisPieceSize(); i++) {
			for (int j = board.getTetrisPieceX(); j < board.getTetrisPieceX() + board.getTetrisPieceSize(); j++) {
				SquareType boardType = board.getSquare(j, i);
				SquareType polyType = board.getTetrisPiece().getPolyTypeAt(j - board.getTetrisPieceX(), i - board.getTetrisPieceY());

				if (board.getTetrisPieceY() > 2) {
					if (boardType != SquareType.EMPTY && polyType != SquareType.EMPTY) {
						return true;
					}
				} else {
					if (boardType != SquareType.EMPTY && boardType != SquareType.OUTSIDE && polyType != SquareType.EMPTY) {
						return true;
					}
				}
			}
		}*/
	}
}

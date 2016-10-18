package tetris;

/**
 * Created by wassing on 2016-10-18.
 */
public class DefaultCollisionHandler implements CollisionHandler
{
    public boolean hasCollision(Board board) {
	if (board.getTetrisPiece() == null) {
	    return false;
	}
	for (int i = board.getTetrisPieceY(); i < board.getTetrisPieceY() + board.getTetrisPieceSize(); i++) {
	    for (int j = board.getTetrisPieceX(); j < board.getTetrisPieceX() + board.getTetrisPieceSize(); j++) {
		SquareType boardType = board.getSquare(j, i);
		SquareType polyType =
			board.getTetrisPiece().getPolyTypeAt(j - board.getTetrisPieceX(), i - board.getTetrisPieceY());
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
	}
	return false;
    }
}

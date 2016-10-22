package tetris;

/**
 * Created by wassing on 2016-10-18.
 * Interface for collision handlers. As long as they're implementing hasCollision they can replace each other.
 */
public interface CollisionHandler {
    boolean hasCollision(Board board);
}

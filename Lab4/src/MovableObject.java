/**
 * Created by wassing on 2016-10-17.
 */
public class MovableObject
{
    protected int x, y;

    public MovableObject(int x, int y){
	this.x = x;
	this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

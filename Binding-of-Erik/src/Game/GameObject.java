package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * The highest class in the object hirarchy.
 * All characters and objects featuring in a room inherit from this class.
 */
public class GameObject {

    private int xcoord = 0;
    private int ycoord = 0;

    /*
    * Getters & Setters
    */
    public int getXcoord() {
        return xcoord;
    }

    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    public int getYcoord() {
        return ycoord;
    }

    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }
}

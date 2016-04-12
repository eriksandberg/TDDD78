package Game;

/**
 * Created by wassing on 2016-04-04.
 */

public class PlaceholderPlayer extends Char{

    // PlaceholderPlayer constructor, currently identical with the constructor of generic enemies
    // Move to Char unless changed later
    public PlaceholderPlayer(int xcoord, int ycoord, int health) {

        setXcoord(xcoord);
        setYcoord(ycoord);
        setHealth(health);
    }

    //private static final int default_health = 100;
    //private static final int default_playerHeight = 10;
    //private static final int default_playerWidth = 10;

}

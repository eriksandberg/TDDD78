package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic enemy inheriting from Char.
 * Capable of creating slightly diverse enemies with strenght.
 */

public class PlaceholderEnemy extends Char {

    // Constructor spawning an enemy in a specified position and with a specific amount of hp
    public PlaceholderEnemy(int xcoord, int ycoord, int health) {

        //setXcoord(xcoord);
        //setYcoord(ycoord);

        setHealth(health);
    }
}

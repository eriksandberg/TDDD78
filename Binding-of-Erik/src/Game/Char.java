package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * A generic character inheriting from GameObject.
 * Provide hp, damage and movement for the player and enemies.
 */
public class Char extends GameObject {

    private int health = 1;
    private int damage = 1;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

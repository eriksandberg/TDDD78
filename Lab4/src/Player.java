/**
 * Created by wassing on 2016-10-17.
 */
public class Player
{
    //public static final int SPEED_SLOW = 0;
    //public static final int SPEED_MEDIUM = 1;
    //public static final int SPEED_FAST = 2;

    //public static final String MODE_NORMAL = "Normal";
    //public static final String MODE_GHOST = "Ghost";
    //public static final String MODE_INVULNERABLE = "Invulnerable";

    private int x, y;
    private Mode mode = Mode.MODE_NORMAL;
    private Speed speed = Speed.SPEED_SLOW;

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(final Speed speed) {
        this.speed = speed;
    }

    public Mode getMode(final Mode mode) {
        return mode;
    }

    public void setMode(final Mode mode) {
        this.mode = mode;
    }

    public void moveRight() {
        switch (speed) {
            case SPEED_SLOW:
                x += 5;
                break;
            case SPEED_MEDIUM:
                x += 10;
                break;
            case SPEED_FAST:
                x += 20;
                break;
        }
    }

    /**
     * Describe current speed and mode -- used for a status display
     */
    public String getDescription() {
        StringBuilder buf = new StringBuilder();
        buf.append("Player is ");
	buf.append(speed.toString()); //this is wrong, should somehow easily get whatever speed that is set.

        buf.append(" and ");
	buf.append(mode.toString()); //this is wrong

        return buf.toString();
    }

    public static void main(String[] args) {
        final Player player = new Player();
        System.out.println(player.getDescription());
    }
}

/**
 * Created by wassing on 2016-10-17.
 */
import java.awt.*;

public class InvulnerablePowerup implements Powerup
{
    public void paint(Graphics g, int x, int y) {
        g.fillOval(x, y, 10, 10);
    }

    public void playerHitMe(Player player){
    	player.setMode(Mode.MODE_INVULNERABLE);
    }

    @Override public String getDescription() {
        return "Makes a player invulnerable for a while";
    }
}

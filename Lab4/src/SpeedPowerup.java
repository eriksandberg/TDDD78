/**
 * Created by wassing on 2016-10-17.
 */
import java.awt.*;

public class SpeedPowerup implements Powerup
{
    @Override public String getDescription() {
        return "Makes a player faster";
    }

    public void playerHitMe(Player player){
	player.setSpeed(Speed.SPEED_FAST);
    }
    
    public void paint(Graphics g, int x, int y) {
        g.fillRect(x, y, 10, 10);
    }
}

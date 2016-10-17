/**
 * Created by wassing on 2016-10-17.
 */
import java.awt.*;

public interface Powerup
{
    String getDescription();

    public void playerHitMe(Player player);
    public void paint(Graphics g, int x, int y);
}

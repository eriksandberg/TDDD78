/**
 * Created by wassing on 2016-10-17.
 */
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private Player player = new Player();
    private List<Powerup> activePowerups = new ArrayList<>();

    public void playerHitPowerup(Powerup power) {
        activePowerups.add(power);
        if (power instanceof SpeedPowerup) {
            player.setSpeed(Player.SPEED_FAST);
        } else if (power instanceof GhostPowerup) {
            player.setMode(Player.MODE_GHOST);
        } else if (power instanceof InvulnerablePowerup) {
            player.setMode(Player.MODE_INVULNERABLE);
        } else {
            throw new IllegalArgumentException("Unknown powerup: " + power);
        }
    }
}

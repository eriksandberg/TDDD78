/**
 * Created by wassing on 2016-10-17.
 */
public class PlayerSprite extends MovableObject
{
    private final String name;

    public PlayerSprite(final String name, final int x, final int y) {

	super(x, y);
	this.name = name;
    }
}

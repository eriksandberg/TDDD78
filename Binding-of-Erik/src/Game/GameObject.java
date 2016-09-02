package Game;

/**
 * User: Erik
 * Date: 2016-04-04
 *
 * The highest class in the object hirarchy.
 * All characters and objects featuring in a room inherit from this class.
 */

public class GameObject {

	private TileType[][] shape;
	private final int width;
	private final int height;

	protected int xCoord;
	protected int yCoord;
	protected float xCoordFloat;
	protected float yCoordFloat;

	private int size;

	// Default constructor
	public GameObject() {
		this.width = 0;
		this.height = 0;
	}

	public GameObject(TileType[][] shape, int width, int height) {
		this.shape = shape;
		this.width = width;
		this.height = height;
	}

	public TileType getTile(int x, int y) {
		try {
			return shape[xCoord - x + 9][yCoord - y + 9];
		} catch (Exception e) {
			return null;
		}
	}
}

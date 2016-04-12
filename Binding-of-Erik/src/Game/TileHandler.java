package Game;

/**
 * Created by wassing on 2016-04-07.
 */
public class TileHandler //instanciates the enums and lets us work with them as we please.
{
    private TileType[][] shape;
    private final int width;
    private final int height;

    public TileHandler(TileType[][] shape, int width, int height) {
	super();
	this.shape = shape;
	this.width = width;
	this.height = height;
    }

    public int getWidth(){
            return width;
        }

    public int getHeight(){
            return height;
        }

    public TileType[][] getShape(){
        return shape;
    } //needed if we create entities that are non-square. See tetris poly class for block demo.
    //Will be used to make "slightly" funnier graphics than simple squares.
}

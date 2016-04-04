package Game;

/**
 * Created by wassing on 2016-04-04.
 */
public class Room
{
    private static final int default_width = 1000; //sample, think pixels
    private static final int default_height = 1000;

    private TileType[][] shape;
    private final int height;
    private final int width;

    public Room(TileType[][] shape, int width, int height){
        super();
        this.shape = shape;
        this.height = height;
        this.width = width;
    }

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public TileType[][] getShape() {return shape;}

    public void randomizeRoom(){
        //pick from a pre-defined set of rooms
    }
}

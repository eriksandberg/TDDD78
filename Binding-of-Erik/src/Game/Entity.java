package Game;

/**
 * Created by wassing on 2016-04-07.
 */
public class Entity //instanciates the enums and lets us work with them as we please.
{
    private TileType[][] shape;
    private final int width;
    private final int height;
    protected int entityXCoord; //protected variable, can be changed by inherited class and inside package
    protected int entityYCoord; //protected variable, can be changed by inherited class and inside package
    protected boolean isEnemy;
    protected int entityXTrajectory;
    protected int entityYTrajectory;

    public Entity(TileType[][] shape, int width, int height) {
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

    public TileType getTile(int x, int y){
	try{
	    return shape[entityXCoord-x+9][entityYCoord-y+9]; //offset, magic 9 is for the actual size of the entities.
	}catch(Exception e){
	    return null;
	}
    }
}

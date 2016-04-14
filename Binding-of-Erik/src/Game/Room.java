package Game;

/**
 * Created by wassing on 2016-04-04.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//public class Room extends JPanel {} //if we do jpanels in jframes, but wait with this.
public class Room {

    //////////////////////////////////////////////
    // Jag fattar inte Swing än...
    //////////////////////////////////////////////

    /*private Image testImg;

    public Room () {

        initRoom();
    }

    private void initRoom() {

        ImageIcon image = new ImageIcon("img1.jpg");
        testImg = image.getImage();

        int w = testImg.getWidth(this);
        int h = testImg.getHeight(this);
        setPreferredSize(new Dimension(w, h));
    }*/

    //////////////////////////////////////////////
    // Slut
    //////////////////////////////////////////////

    private static final int PIXELS_PER_WIDTH = 40; //sample, think pixels, might not be needed here but that's for later
    private static final int PIXELS_PER_HEIGHT = 40; //this would give us a 20x20 square.

    private TileType[][] board;
    private int playerXCoord = 0;
    private int playerYCoord = 0;
    private int enemyXCoord = 0; //might have to be array
    private int enemyYCoord = 0;
    private int height;
    private int width;
    public boolean gameOver = false;
    public TileHandler currentTile = null;

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    public int getPixelsPerWidth() {return PIXELS_PER_WIDTH;} //use later maybe

    public int getPixelsPerHeight() {return PIXELS_PER_HEIGHT;} //use later

    public TileType[][] getBoard() {return board;}

    public int getColumns(){
	return PIXELS_PER_WIDTH;
    }

    public int getRows(){
	return PIXELS_PER_HEIGHT;
    }

    public TileHandler getTileType(){return currentTile;}

    public TileType getSquare(int x, int y){

	TileType square;
	if(this.getTileType() != null){
	    try{
		square = this.getTileType().getShape()[x][y]; //might have to do more math on this, gotta run some tests;
		if(square.equals(TileType.EMPTY)){
		    square = board[x][y];
		}
	    }catch(RuntimeException e){
		square = board[x][y];
	    }
	}else{
	    square = board[x][y];
	}
	return square;
    }

    public void tick(){
        //always called by the clock, does all the "machine" work, will call functions which in turn call the paint-components.
    	//more to be implemented here.
	notifyListeners();
    }

    public void randomizeRoom(){
        //pick from a pre-defined set of rooms, to be used way later in the project when we got that far.
    }

    //custom constructor for a room, may not be used at all later. Depends how we implement.
    //BREAKPOINT HERE: WHY IS NOT THE WHOLE BOARD BEING COLORED? SEE EVENTHANDLER CLASS.
    public Room(int width, int height){
	this.width = width;
	this.height = height;
	System.out.println("Width: " + width);
	System.out.println("Height: " + height);
	board = new TileType[width][height];
	for (int tileX = 0; tileX < width; tileX++){ //will loop for every "square" and assign a tile to it.
	    for (int tileY = 0; tileY < height; tileY++){ //starts at max Y, this gives proper (x,y)
	    	board[tileX][tileY] = TileType.G; //grass
	    }
	}
	spawnPlayer(playerXCoord, playerYCoord);
    }

    public void spawnPlayer(int x, int y){ //should only have to be called ONCE per room. x,y, modifiers are needed.
	GraphicsFactory player = new GraphicsFactory();
	this.currentTile = player.getPlayer(); //could also be char
	this.playerXCoord = x;
	this.playerYCoord = y;
	notifyListeners();
    }

    public void spawnEnemy(){ //should be used to spawn several enemy objects, store in an array?
        Random generator = new Random();
        GraphicsFactory enemy = new GraphicsFactory();
	this.currentTile = enemy.getEnemy(); //could also be char
	this.enemyXCoord = generator.nextInt(20); //random up to 20
	this.enemyYCoord = generator.nextInt(20); //random up to 20
	notifyListeners();
    }

    public void addBoardListener(BoardListener bl){
	boardListenerArray.add(bl);
    }

    private void notifyListeners(){
        for (BoardListener boardListener : boardListenerArray) {
            boardListener.BoardChanged();
        }
    }

    private boolean canWeMove(String direction){
	//need to bordercheck. LATER.
	return true;
    }

    public void moveAnywhere(String direction){
        if (gameOver){
            return; //can't move if we lost, prevents us from doing stupid things.
        }
        switch (direction){
            case "up": //go up
		this.playerYCoord += 1;
                break;
            case "down": //go down
                break;
            case "right": //go right
		this.playerXCoord += 1;
                break;
            case "left": //go left
                break;
        }
	if (!canWeMove(direction)){
	    //reset us if we went out of bounds.
	}
        notifyListeners();
    }
}

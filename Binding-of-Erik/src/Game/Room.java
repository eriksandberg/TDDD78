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

    private static final int PIXELS_PER_WIDTH = 100; //sample, think pixels, might not be needed here but that's for later
    private static final int PIXELS_PER_HEIGHT = 100;

    private TileType[][] board;
    private int playerXCoord;
    private int playerYCoord;
    private int height = 0;
    private int width = 0;
    public boolean gameOver = false;
    public TileHandler currentTile = null;

    private final List<BoardListener> boardListenerArray = new ArrayList<BoardListener>();

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public TileType[][] getBoard() {return board;}

    public int getColumns(){
	return width;
    }

    public int getRows(){
	return height;
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
    }

    public void randomizeRoom(){
        //pick from a pre-defined set of rooms, to be used way later in the project when we got that far.
    }

    //custom constructor for a room, may not be used at all later. Depends how we implement.
    public Room(int height, int width){
	this.height = height;
	this.width = width;
	board = new TileType[width][height];
	for (int tileY = 0; tileY < PIXELS_PER_HEIGHT; tileY += height){ //for every y...
	    for (int tileX = 0; tileX < PIXELS_PER_WIDTH; tileX += width){ //for every x in y... (currently gives y,x representation)
		board[tileY][tileX] = TileType.EMPTY;
	    }
	}
	spawnPlayer();
    }

    public void spawnPlayer(){ //should only have to be called ONCE per room.
	GraphicsFactory player = new GraphicsFactory();
	this.currentTile = player.getPlayer();
	this.playerXCoord = 0;
	this.playerYCoord = 0;
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

    private boolean canWeMove(){
	return true;
    }

    public void moveAnywhere(String direction){
        if (gameOver){
            return; //can't move if we lost, prevents us from doing stupid things.
        }
        switch (direction){
            case "up": //go up
		if (canWeMove()){
		    playerYCoord += 1; //whatever direction we move in, still have to test things.
		}
                break;
            case "down": //go down
                break;
            case "right": //go right
                break;
            case "left": //go left
                break;
        }
        notifyListeners();
    }
}

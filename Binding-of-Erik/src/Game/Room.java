package Game;

/**
 * Created by wassing on 2016-04-04.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Room extends JPanel {

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

    private static final int PIXEL_WIDTH = 1000; //sample, think pixels
    private static final int PIXEL_HEIGHT = 1000;

    private TileType[][] board;
    private int height = 0;
    private int width = 0;

    /*public Room(TileType[][] board, int width, int height){
        super();
        this.board = board;
        this.height = height;
        this.width = width;
    }*/

    public int getHeight() {return height;}

    public int getWidth() {return width;}

    public TileType[][] getBoard() {return board;}

    public void randomizeRoom(){
        //pick from a pre-defined set of rooms
    }

    //custom constructor for a room, may not be used at all later. Depends how we implement.
    public Room(int height, int width){
	this.height = height;
	this.width = width;
	board = new TileType[width][height];
	for (int tileY = 0; tileY < PIXEL_HEIGHT; tileY += height){ //for every y...
	    for (int tileX = 0; tileX < PIXEL_WIDTH; tileX += width){ //for every x in y...

	    }
	}

    }
}

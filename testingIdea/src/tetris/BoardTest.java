package tetris;

import javax.swing.*;

/**
 * User: Daniel
 * Date: 28/09/13
 */

public final class BoardTest {

    private final static int HEIGHT = 22;
    private final static int WIDTH = 10;

    private BoardTest() {}

    public static void main(String[] args) {
	System.out.println("Tetris");
	Board myBoard = new Board(WIDTH, HEIGHT);
	TetrisFrame myFrame = new TetrisFrame(myBoard, "TETRIS");
	myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	myFrame.pack();
	myFrame.setVisible(true);
    }
}

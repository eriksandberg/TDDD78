package tetris;

import javax.swing.*;

/**
 * User: Daniel
 * Date: 28/09/13
 */

public class BoardTest {

    public static void main(String[] args) {
        System.out.println("Tetris");
        Board myBoard = new Board(10,22);

        //myBoard.randomizeBoard();
        TetrisFrame myFrame = new TetrisFrame(myBoard, "TETRIS");
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
    }
}

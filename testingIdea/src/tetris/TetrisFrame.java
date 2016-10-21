package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-10-02
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class TetrisFrame extends JFrame {

    private final static int DELAY = 250;

    private final Board board;

    public TetrisFrame(Board board, String myWindowTitle){

        super(myWindowTitle);

	this.board = board;
        TetrisComponent paintArea = new TetrisComponent(board);
        this.setLayout(new BorderLayout());
        this.add(paintArea, BorderLayout.CENTER);

        Timer clockTimer = new Timer(DELAY, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();

    }

    @SuppressWarnings("FieldCanBeLocal")    // Converting to local breaks the code
    private final Action doOneStep = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
	    if (!board.gameOver) {
		board.tick();
	    }
            //repaint();
            //board.randomizeBoard();
            //TetrisComponent paintArea = new TetrisComponent(board);
            //textArea.setText(TetrisTextView.convertToText(board));
        }

    };

    private final Action clickMe = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }

    };
}

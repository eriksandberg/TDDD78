package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.AbstractMap;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-10-03
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
public class TetrisComponent extends JComponent implements BoardListener {

    private static final int SQUARE_WIDTH = 300; //300
    private static final int SQUARE_HEIGHT = 660; //660
    private final static int FONT_SIZE = 20;
    private final static int RECT_SIDE_LENGTH = 30;


    private final Board board;
    private final AbstractMap<SquareType, Color> map = SquareType.eMap();

    public TetrisComponent(Board myBoard) {

        board = myBoard;
        this.setPreferredSize(getPreferredSize());
        myBoard.addBoardListener(this);

        getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressedSpace");
        getActionMap().put("pressedSpace", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		board.tick();
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("T"), "pressedT");
	getActionMap().put("pressedT", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		board.resetBoard();
	    }
	});

        getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "pressedUp");
        getActionMap().put("pressedUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.rotate(true);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "pressedDown");
        getActionMap().put("pressedDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.rotate(false);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
            "pressedRight");
        getActionMap().put("pressedRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveSideways(false);
            }
        });

        getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "pressedLeft");
        getActionMap().put("pressedLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveSideways(true);
            }
        });
    }

    public Dimension getPreferredSize(){
        return new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;
	for (int i = 0; i < board.getColumns(); i++){
            for (int j = 2; j < board.getRows(); j++){
		SquareType square = board.getSquare(i, j);
		g2.setColor(map.get(square));
                g2.fillRect(i * RECT_SIDE_LENGTH, j * RECT_SIDE_LENGTH, RECT_SIDE_LENGTH, RECT_SIDE_LENGTH);
            }
        }
	g2.setColor(Color.RED);
	g2.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
	g2.drawString("Current Score: " + Integer.toString(board.getCurrentScore()), board.getColumns(), board.getRows() + 2);
    }

    public void boardChanged(){
        repaint();
    }
}

package binding_of_erik_game;

//import javafx.scene.input.KeyEvent; //might be used later
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;

/**
 * Created by wassing on 2016-04-06.
 */
public class EventHandler extends JComponent implements BoardListener {

    private boolean testing = false;

    private static final int SQUARE_WIDTH = 800; //graphical size of the actual GameFrame
    private static final int SQUARE_HEIGHT = 800;
    private static final int TIMER_DELAY = 50;
    private Timer upTimer, downTimer, rightTimer, leftTimer;
    private final Room room;
    private final EnumMap<TileType, Color> map = TileType.eMap();

    public EventHandler(Room room) {
	this.room = room;
	this.setPreferredSize(getPreferredSize());
	room.addBoardListener(this);

	// All key binds in the game
	getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressedSpace");
	getActionMap().put("pressedSpace", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		EventHandler.this.room.fireShot();
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "pressedEscape");
	getActionMap().put("pressedEscape", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("P"), "pressedP");
	getActionMap().put("pressedP", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		GameFrame.togglePaused();
		System.out.println("Paused.");
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("T"), "pressedT");
	getActionMap().put("pressedT", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		testing = !testing;
		if (testing) {
		    System.out.println("Testing enabled.");
		} else {
		    System.out.println("Testing disabled.");
		}
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressedEnter");
	getActionMap().put("pressedEnter", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if (testing) {
		    EventHandler.this.room.newRoom();
		    System.out.println("New room spawned.");
		}
	    }
	});

	getInputMap().put(KeyStroke.getKeyStroke("R"), "pressedR");
	getActionMap().put("pressedR", new AbstractAction()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		if (testing) {
		    System.out.println("Room reset.");
		    EventHandler.this.room.resetRoom();
		}
	    }
	});
	KeyStroke fKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, false);
	getInputMap().put(fKeyPressed, "F pressed");
	getActionMap().put("F pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		EventHandler.this.room.movePlayer('R');
	    }
	});

	KeyStroke fKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, true);
	getInputMap().put(fKeyReleased, "F released");
	getActionMap().put("F released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		EventHandler.this.room.movePlayer('X');
	    }
	});

	KeyStroke aKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false);
	getInputMap().put(aKeyPressed, "A pressed");
	getActionMap().put("A pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		EventHandler.this.room.movePlayer('L');
	    }
	});

	KeyStroke aKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true);
	getInputMap().put(aKeyReleased, "A released");
	getActionMap().put("A released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		EventHandler.this.room.movePlayer('X');
	    }
	});

	KeyStroke upKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
	KeyStroke upKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
	KeyStroke downKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
	KeyStroke downKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
	KeyStroke rightKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
	KeyStroke rightKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
	KeyStroke leftKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
	KeyStroke leftKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);

	getInputMap().put(upKeyPressed, "up key pressed");
	getInputMap().put(upKeyReleased, "up key released");

	getActionMap().put("up key pressed", new upAction(false));
	getActionMap().put("up key released", new upAction(true));

	getInputMap().put(downKeyPressed, "down key pressed");
	getInputMap().put(downKeyReleased, "down key released");

	getActionMap().put("down key pressed", new downAction(false));
	getActionMap().put("down key released", new downAction(true));

	getInputMap().put(rightKeyPressed, "right key pressed");
	getInputMap().put(rightKeyReleased, "right key released");

	getActionMap().put("right key pressed", new rightAction(false));
	getActionMap().put("right key released", new rightAction(true));

	getInputMap().put(leftKeyPressed, "left key pressed");
	getInputMap().put(leftKeyReleased, "left key released");

	getActionMap().put("left key pressed", new leftAction(false));
	getActionMap().put("left key released", new leftAction(true));

    }

        private class upAction extends AbstractAction {
	    private boolean onKeyRelease;

	    public upAction(boolean onKeyRelease) {
		this.onKeyRelease = onKeyRelease;
	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
	  	if (!onKeyRelease) {
		    if (upTimer != null && upTimer.isRunning()) {
			return;
		    }
		    EventHandler.this.room.movePlayer('N');

		    upTimer = new Timer(TIMER_DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    EventHandler.this.room.movePlayer('N');
			}
		    });
		    upTimer.start();
		} else {
		    EventHandler.this.room.movePlayer('X');
		    if (upTimer != null && upTimer.isRunning()) {
		       upTimer.stop();
		       upTimer = null;
		    }
		}
	    }
        }

    	private class downAction extends AbstractAction {
	    private boolean onKeyRelease;

    	    public downAction(boolean onKeyRelease) {
    	       this.onKeyRelease = onKeyRelease;
    	    }

	    @Override
	    public void actionPerformed(ActionEvent evt) {
		if (!onKeyRelease) {
		    if (downTimer != null && downTimer.isRunning()) {
    		    	return;
		    }
		    EventHandler.this.room.movePlayer('S');

		    downTimer = new Timer(TIMER_DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			   EventHandler.this.room.movePlayer('S');
			}
		    });
    		downTimer.start();
		} else {
		    EventHandler.this.room.movePlayer('X');
		    if (downTimer != null && downTimer.isRunning()) {
		        downTimer.stop();
		        downTimer = null;
		    }
    	     	}
	    }
	}

    	private class rightAction extends AbstractAction {
    	    private boolean onKeyRelease;

    	    public rightAction(boolean onKeyRelease) {
    		this.onKeyRelease = onKeyRelease;
    	    }

    	    @Override
    	    public void actionPerformed(ActionEvent evt) {
    	  	if (!onKeyRelease) {
    		    if (rightTimer != null && rightTimer.isRunning()) {
    			return;
    		    }
    		    EventHandler.this.room.movePlayer('E');

    		    rightTimer = new Timer(TIMER_DELAY, new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    			    EventHandler.this.room.movePlayer('E');
    			}
    		    });
    		    rightTimer.start();
    		} else {
    		    EventHandler.this.room.movePlayer('X');
    		    if (rightTimer != null && rightTimer.isRunning()) {
    		       rightTimer.stop();
    		       rightTimer = null;
    		    }
    		}
    	    }
	}

    	private class leftAction extends AbstractAction {
    	    private boolean onKeyRelease;

	    public leftAction(boolean onKeyRelease) {
	       this.onKeyRelease = onKeyRelease;
	    }

    	    @Override
    	    public void actionPerformed(ActionEvent evt) {
    		if (!onKeyRelease) {
    		    if (leftTimer != null && leftTimer.isRunning()) {
			return;
    		    }
    		    EventHandler.this.room.movePlayer('W');

    		    leftTimer = new Timer(TIMER_DELAY, new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    			   EventHandler.this.room.movePlayer('W');
    			}
    		    });
		    leftTimer.start();
    		} else {
    		    EventHandler.this.room.movePlayer('X');
    		    if (leftTimer != null && leftTimer.isRunning()) {
    		        leftTimer.stop();
    		        leftTimer = null;
    		    }
		}
    	    }
    	}

    public Dimension getPreferredSize(){
	return new Dimension(SQUARE_WIDTH, SQUARE_HEIGHT);
    }

    public void paintComponent(Graphics g){
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < room.getWidth(); i++){
	        for (int j = 0; j < room.getHeight(); j++){
				TileType square = room.getSquare(i, j);
				if (square != TileType.TRANSPARENT) {
		            g2.setColor(map.get(square));
				} else {
		            g2.setColor(Color.green); //need a general solution for this to look good. Use TEMP
				}
				g2.fillRect(i * room.getPixelWidthPerTile(), j * room.getPixelHeightPerTile(),
				room.getPixelWidthPerTile(), room.getPixelHeightPerTile());
				//may need to re-define this later.
	        }
		}
    }

    public void boardChanged(){
		repaint();
    }
}
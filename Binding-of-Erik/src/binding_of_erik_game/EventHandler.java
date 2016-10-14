package binding_of_erik_game;

//import javafx.scene.input.KeyEvent; //might be used later
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

/**
 * Created by wassing on 2016-04-06.
 */
public class EventHandler extends JComponent implements BoardListener {

    private boolean testing = false;

    private static final int SQUARE_WIDTH = 800; //graphical size of the actual GameFrame
    private static final int SQUARE_HEIGHT = 800;
    private final Room room;
    private final EnumMap<TileType, Color> map = TileType.eMap();

    public EventHandler(Room room) {
	this.room = room;
	this.setPreferredSize(getPreferredSize());
	room.addBoardListener(this);

	// All key binds in the game
	getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressedSpace");
	getActionMap().put("pressedSpace", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
			EventHandler.this.room.fireShot();
		}
	});

	KeyStroke upKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);
	getInputMap().put(upKeyPressed, "up key pressed");
	getActionMap().put("up key pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('N');
		System.out.println("Pressed up");
				}
	});

	KeyStroke upKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
	getInputMap().put(upKeyReleased, "up key released");
	getActionMap().put("up key released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('X');
		System.out.println("Released up");
				}
	});

	KeyStroke downKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);
	getInputMap().put(downKeyPressed, "down key pressed");
	getActionMap().put("down key pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('S');
				}
	});

	KeyStroke downKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
	getInputMap().put(downKeyReleased, "down key released");
	getActionMap().put("down key released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('X');
				}
	});

	KeyStroke rightKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
	getInputMap().put(rightKeyPressed, "right key pressed");
	getActionMap().put("right key pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('E');
				}
	});

	KeyStroke rightKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
	getInputMap().put(rightKeyReleased, "right key released");
	getActionMap().put("right key released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('X');
				}
	});

	KeyStroke leftKeyPressed = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
	getInputMap().put(leftKeyPressed, "left key pressed");
	getActionMap().put("left key pressed", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('W');
				}
	});

	KeyStroke leftKeyReleased = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
	getInputMap().put(leftKeyReleased, "left key released");
	getActionMap().put("left key released", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					EventHandler.this.room.movePlayer('X');
				}
	});

	getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "pressedEscape");
	getActionMap().put("pressedEscape", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
					System.exit(0);
		}
	});
	getInputMap().put(KeyStroke.getKeyStroke("P"), "pressedP");
	getActionMap().put("pressedP", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		GameFrame.togglePaused();
		System.out.println("Paused.");
	    }
	});
	getInputMap().put(KeyStroke.getKeyStroke("T"), "pressedT");
	getActionMap().put("pressedT", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		testing = !testing;
		if (testing) {
		    System.out.println("Testing enabled.");}
		else {
		    System.out.println("Testing disabled.");
		}
	    }
	});
	getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressedEnter");
	getActionMap().put("pressedEnter", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (testing) {
		    EventHandler.this.room.newRoom();
		    System.out.println("New room spawned.");
		}
	    }
	});
	getInputMap().put(KeyStroke.getKeyStroke("R"), "pressedR");
	getActionMap().put("pressedR", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (testing) {
		    System.out.println("Room reset.");
		    EventHandler.this.room.resetRoom();
		}
	    }
	});
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
package Game;

import javax.swing.*;
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
    public EventHandler(Room thisRoom) {

	room = thisRoom;
	this.setPreferredSize(getPreferredSize());
	thisRoom.addBoardListener(this);

	getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                    "pressedSpace");
	getActionMap().put("pressedSpace", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                    room.tick();
		}
	});

	getInputMap().put(KeyStroke.getKeyStroke("UP"),
                    "pressedUp");
	getActionMap().put("pressedUp", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                    room.moveAnywhere("up");
                }
	});

	getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                    "pressedDown");
	getActionMap().put("pressedDown", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                    room.moveAnywhere("down");
                }
	});

	getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "pressedRight");
	getActionMap().put("pressedRight", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {room.moveAnywhere("right");
                }
	});

	getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                    "pressedLeft");
	getActionMap().put("pressedLeft", new AbstractAction() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                    room.moveAnywhere("left");
		}
	});
	getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
			"pressedEscape");
	getActionMap().put("pressedEscape", new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
					//GameFrame.close();
					System.exit(0);
		}
	});
	getInputMap().put(KeyStroke.getKeyStroke("T"),
			"pressedT");
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
	getInputMap().put(KeyStroke.getKeyStroke("ENTER"),
			"pressedEnter");
	getActionMap().put("pressedEnter", new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (testing) {
				room.newRoom();
				System.out.println("New room spawned.");
			}
		}
	});
	getInputMap().put(KeyStroke.getKeyStroke("RED"),
			"pressedR");
	getActionMap().put("pressedR", new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (testing) {
				System.out.println("Room reset.");
				room.resetRoom();
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
		}else{
		    g2.setColor(Color.green); //need a general solution for this to look good. Use TEMP
		}
		g2.fillRect(i * room.getPixelWidthPerTile(), j * room.getPixelHeightPerTile(),
			    room.getPixelWidthPerTile(), room.getPixelHeightPerTile());
		/*Need a change in how many tiles we have in the room. Base tile needs to be all possible moves.
		Meaning: room is made up of 20x20 blocks. Every block contains 10 tiles. Need to define pixelsize of tile.
		Make sure to draw every tile with this function. When working out rooms etc, use an oversized pattern.
		*/
	    }
	}
    }

    public void boardChanged(){
	repaint();
    }
}
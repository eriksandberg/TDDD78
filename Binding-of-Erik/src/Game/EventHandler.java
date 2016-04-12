package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

/**
 * Created by wassing on 2016-04-06.
 */
public class EventHandler extends JComponent implements BoardListener {

    private static final int squareWidth = 1000; //graphical size of the actual GameFrame
    private static final int squareHeight = 1000;
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
    }

    public Dimension getPreferredSize(){
            return new Dimension(squareWidth, squareHeight);
        }

    public void paintComponent(Graphics g){
	super.paintComponent(g);
	final Graphics2D g2 = (Graphics2D) g;
	TileType square;
	for (int i = 0; i < room.getColumns(); i++){
	    for (int j = 2; j < room.getRows(); j++){
		square = room.getSquare(i, j);
		g2.setColor(map.get(square));
		g2.fillRect(i*30, j*30, 30, 30);
	    }
	}
    }

    public void BoardChanged(){
            repaint();
        }
}
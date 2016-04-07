package Game;

/**
 * Created by wassing on 2016-04-06.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class GameFrame extends JFrame{ //this class does all the actual frame updating work. Will be instanced as an object for GameWindow.

    private final Room room;

    public GameFrame(Room room, String myWindowTitle){
 	super(myWindowTitle);

	this.room = room;
	EventHandler paintArea = new EventHandler(room);
	this.setLayout(new BorderLayout());
	this.add(paintArea, BorderLayout.CENTER);
	//this.createMenu(); extra for later, options for file etc.

	Timer clockTimer = new Timer(250, doOneStep);
	clockTimer.setCoalesce(true);
	clockTimer.start();
    }

    private final Action doOneStep = new AbstractAction(){
	public void actionPerformed(ActionEvent e){
	    room.tick();
	    //board.randomizeBoard(); lulz, could be fun for absolute chaos
	}

    };
}

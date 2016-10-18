package binding_of_erik_game;

/**
 * Created by wassing on 2016-04-06.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class GameFrame extends JFrame { //this class does all the actual frame updating work. Will be instanced as an object for GameWindow.

	private final Room room;
	private static boolean paused = false;

	public GameFrame(final Room room, String myWindowTitle) {
		super(myWindowTitle);

		this.room = room;
		EventHandler paintArea = new EventHandler(room);
		this.setLayout(new BorderLayout());
		this.add(paintArea, BorderLayout.CENTER);
		//this.createMenu(); extra for later, options for file etc.

		final Action doOneStep = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (!paused) {  // Since player movement is currently done outside of tick() this is not really a true pause
					room.tick();
				}
			}
		};
		Timer clockTimer = new Timer(100, doOneStep); //10 ticks per second atm, will increase framerate to about 30 later on.
		clockTimer.setCoalesce(true);
		clockTimer.start();
	}

	// Pause the game
	public static void togglePaused() {
		paused = !paused;
	}
}

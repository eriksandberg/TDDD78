package binding_of_erik_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by wassing on 2016-04-06.
 * This class is instanced as an object for GameWindow.
 * Handles updating the frame, calling tick() in Room.
 */

public class GameFrame extends JFrame {

	private static boolean paused = false;
	private static boolean gameOver = false;

	public GameFrame(final Room room, String myWindowTitle) {
		super(myWindowTitle);

		EventHandler paintArea = new EventHandler(room);
		this.setLayout(new BorderLayout());
		this.add(paintArea, BorderLayout.CENTER);

		final Action doOneStep = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// Since player movement is done outside of tick() this is not really a true pause nor game over
				// Pause is for testing and nothing happen once the game is over so it does not matter
				if (!paused && !gameOver) {
					room.tick();
				}
			}
		};
		Timer clockTimer = new Timer(100, doOneStep);
		clockTimer.setCoalesce(true);
		clockTimer.start();
	}

	// Pause the game
	public static void togglePaused() {
		paused = !paused;
	}

	public static void gameOver() {
		gameOver = true;
	}
}
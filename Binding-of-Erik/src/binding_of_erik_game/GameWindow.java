package binding_of_erik_game;

/**
 * Created by erik on 05/04/16.
 */

import javax.swing.JFrame;

/**
 * Start the game and open the game window
 */
public final class GameWindow {

	private static final int FRAMEWIDTH = 200; //pixels, also our entire coordinates system.
	private static final int FRAMEHEIGHT = 200;

	private GameWindow() {
	}

	public static void main(String[] args) {
	    System.out.println("hej hej hallå!");
		// Create player and the first room
		Player player = GraphicsFactory.getInstance().getPlayer();
		Room startingRoom = new Room(player, FRAMEWIDTH, FRAMEHEIGHT);

		// Set frame name to 'Binding of Erik', set size of frame,
		// set default to close the application when the window is closed,
		// center the frame on the screen and make the window visiable
		GameFrame window = new GameFrame(startingRoom, "Binding of Erik");
		//noinspection MagicConstant, IDEA recommend using EXIT_ON_CLOSE and no, the suppression is not redundant
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		System.out.println("Binding of Erik: Running!");
	}
}
package Game;

/**
 * Created by erik on 05/04/16.
 *
 *  Main file including initiatons of game loop and swing graphics
 */

import javax.swing.*;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

    private static final int FRAMEWIDTH = 800; //pixels, also our entire coordinates system.
    private static final int FRAMEHEIGHT = 800;

    // Main
    public static void main(String[] args) {

		// Set frame name to 'Binding of Erik', set size of frame,
		// set default to close the application when the window is closed,
		// center the frame on the screen and make the window visiable
		Room startingRoom = new Room(FRAMEWIDTH, FRAMEHEIGHT);
		GameFrame window = new GameFrame(startingRoom, "Binding of Erik");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		System.out.println("Binding of Erik: Running!");

		//window.setVisible(false);
		//window.dispose();
    }
}
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

    private static final int FRAMEWIDTH = 1000; //pixels
    private static final int FRAMEHEIGHT = 1000;

    public GameWindow() {
        windowInit();
    }

    // Launching the graphical interface (name frameInit is taken)
    private void windowInit() { //haven't gotten this to work yet

        // As of now we'll just create one room, swaping rooms come later
        //add(new Room());

        // Set frame name to 'Binding of Erik', set size of frame, center frame on the screen,
        // set default to close the application when the window is closed and
        // make the window visiable

	/*GameFrame StartingRoom = new GameFrame(startingRoom, "Binding of Erik");
        setTitle("Binding of Erik");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    // Main
    public static void main(String[] args) {

	System.out.println("Binding of Erik: Running!");
	Room startingRoom = new Room(FRAMEWIDTH, FRAMEHEIGHT);
	GameFrame window = new GameFrame(startingRoom, "Binding of Erik");
        //GameWindow window = new GameWindow(); //haven't gotten the init to work yet
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
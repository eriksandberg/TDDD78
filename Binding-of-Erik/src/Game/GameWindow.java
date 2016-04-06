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

    public GameWindow() {
        windowInit();
    }

    // Launching the graphical interface (name frameInit is taken)
    private void windowInit() {

        // As of now we'll just create one room, swaping rooms come later
        //add(new Room());

        // Set frame name to 'Binding of Erik', set size of frame, center frame on the screen,
        // set default to close the application when the window is closed and
        // make the window visiable
        setTitle("Binding of Erik");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ImageIcon image = new ImageIcon(imgpath);
        //JLabel label = new JLabel(image);

        //pack();

    }

    // Main
    public static void main(String[] args) {

        GameWindow window = new GameWindow();
        window.setVisible(true);
    }
}
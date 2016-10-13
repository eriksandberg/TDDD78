package fuckyou;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 2013-10-02
 * Time: 08:46
 * To change this template use File | Settings | File Templates.
 */
public class TetrisFrame extends JFrame {

    private final Board board;
    JTextArea textArea;  //Was used in earlier assignment

    public TetrisFrame(Board board, String myWindowTitle){

        super(myWindowTitle);

        this.board = board;
        //textArea = new JTextArea(board.getRows(), board.getColumns());
        //textArea.setText(fuckyou.TetrisTextView.convertToText(board));
        TetrisComponent paintArea = new TetrisComponent(board);
        this.setLayout(new BorderLayout());
        this.add(paintArea, BorderLayout.CENTER);
        //this.createMenu(); //currently not being used.

        Timer clockTimer = new Timer(250, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();

    }

    private final Action doOneStep = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            board.tick();
            //repaint();
            //board.randomizeBoard();
            //TetrisComponent paintArea = new TetrisComponent(board);
            //textArea.setText(fuckyou.TetrisTextView.convertToText(board));
        }

    };

    private final Action clickMe = new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }

    };

    private void createMenu(){

        JMenuItem exit = new JMenuItem("Exit", 'E');
        final JMenu file = new JMenu("File");
        file.add(exit);

        final JMenuBar bar = new JMenuBar();
        bar.add(file);
        exit.addActionListener(clickMe);
        this.setJMenuBar(bar);
    }
}

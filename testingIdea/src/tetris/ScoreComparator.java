package tetris;

/**
 * Created by wassing on 2016-10-18
 * Comparator for highscores. Used by HighscoreList for sorting highscores.
 */
import java.util.Comparator;

public class ScoreComparator implements Comparator<Highscore> {
    public int compare(Highscore score1, Highscore score2) {
	//works a lot like assembler's compare, BRNE/BREQ
        if (score1.getScore() > score2.getScore()) {
	    //swap if compared is higher, yada yada...
            return -1;
        } else {
            return 1;
        }
    }
}
package tetris;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wassing on 2016-10-18.
 *
 */
public final class HighscoreList {
    // Only create once, when class is loaded.
    private static final HighscoreList INSTANCE = new HighscoreList();
    private List<Highscore> highscoreList;

    // Private constructor.
    private HighscoreList() {
        this.highscoreList = new ArrayList<>();
    }

    // Access the shared object
    public static HighscoreList getInstance() {
        return INSTANCE;
    }

    public void addScore(Highscore highscore) {
	highscoreList.add(highscore);
	Collections.sort(highscoreList, new ScoreComparator());
    }

    public String getHighscoreList(){
	StringBuilder stringBuilder = new StringBuilder();

	for (int i = 0; i < highscoreList.size() && i < 10; i++) {
	    stringBuilder.append(highscoreList.get(i).getName());
	    stringBuilder.append(" : ");
	    stringBuilder.append(highscoreList.get(i).getScore());
	    stringBuilder.append("\n");
	}

	return stringBuilder.toString();
    }
}

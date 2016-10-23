package binding_of_erik_game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * User: Erik
 * Date: 2016-10-16
 * This class handles the players score, and reads/writes scoreList to file.
 * We write to file on game over and when we manually close the application, even if we're in the middle of a game
 */

public final class Score {

	// We're tracking 10 scores
	private int[] scoreList = new int[10];
	private int scoreListIndexLen = scoreList.length - 1;

	private int currentScore;

	private Path filePath = Paths.get("", "scoreList.txt");

	public Score() throws IOException {
		try {   // Try to open the file scoreList in the current directory
			filePath = Files.createFile(filePath);
		} catch (FileAlreadyExistsException ignored) {  // The file already exist, read from it.
			try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("US-ASCII"))) {
				for (int i = 0; i < scoreList.length; ++i) {   // Read the 10 first existing scores
					String line = reader.readLine();
					if (line == null) {
						break;  // Break if we read nothing
					}
					scoreList[i] = Integer.parseInt(line);    // First int we get from the file is put first in the array
				}
			}
		}
	}

	// Write highscore to file.
	public void writeHighscore() {
		try {
			try (BufferedWriter writer = Files.newBufferedWriter(filePath, Charset.forName("US-ASCII"))) {
				for (int score : scoreList) {
					String out = Integer.toString(score);
					writer.write(out, 0, out.length());
					writer.newLine();
				}
				writer.flush();
			}
		} catch (IOException ignored) {
		}
	}

	// Save current score locally
	public void saveScore() {
		if (currentScore > scoreList[scoreListIndexLen]) {   // No point in iterating if we won't make the list
			for (int i = scoreListIndexLen; i > 0; --i) {
				if (currentScore > scoreList[i]) {
					scoreList[i] = scoreList[i - 1];
				} else {
					scoreList[i + 1] = currentScore;
					break;
				}
			}
			// High-Highscore, what a day to be alive!
			if (currentScore > scoreList[0]) {
				scoreList[0] = currentScore;
			}
		}
	}

	// Add to score
	public void addToCurrentScore(int score) {
		this.currentScore += score;
	}

	public void subFromCurrentScore(int score) {
		if (!(currentScore - score < 0)) {
			this.currentScore -= score;
		}
	}

	public int getCurrentScore() {
		return currentScore;
	}
}

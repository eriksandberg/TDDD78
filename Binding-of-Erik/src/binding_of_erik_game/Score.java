package binding_of_erik_game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * User: Erik
 * Date: 2016-10-16
 * This class handle the players score, and reading/writing scoreList to file.
 * We write to file on game over and when we manually close the application, even if we're in the middle of a game
 */

public final class Score {

	// We're tracking 10 scores
	private int[] scoreList = new int[10];
	private int scoreListIndexLen = scoreList.length - 1;

	private int highscore;
	private int currentScore;

	private Path filePath = Paths.get("", "scoreList.txt");

	public Score() throws IOException {
		try {   // Try to open the file scoreList in the current directory
			filePath = Files.createFile(filePath);
		} catch (FileAlreadyExistsException ignored) {  // The file already exist, read from it.
			BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("US-ASCII"));
			for (int i = 0; i < scoreList.length; ++i) {   // Read the 10 first existing scores
				String line = reader.readLine();
				if (line == null) {
					break;  // Break if we read nothing
				}
				scoreList[i] = Integer.parseInt(line);    // First int we get from the file is put first in the array
				// TODO: Do something if we get garbage
			}
			highscore = scoreList[0];
			System.out.println(Arrays.toString(scoreList));
		}
	}

	// Write highscore to file.
	public void writeHighscore() {
		try {
			BufferedWriter writer = Files.newBufferedWriter(filePath, Charset.forName("US-ASCII"));
			for (int score : scoreList) {
				String out = Integer.toString(score);
				writer.write(out, 0, out.length());
				writer.newLine();
			}
			writer.flush();
		} catch (IOException ignored) {}
	}

	// Save current score locally
	public void saveScore() {
		if (currentScore > scoreList[scoreListIndexLen]) {   // No point in iterating if we won't make the list
			for (int i = scoreListIndexLen; i > 0; --i) {
				if (currentScore > scoreList[i]) {
					scoreList[i] = scoreList[i - 1];
				} else {
					System.out.println(i);
					scoreList[i] = currentScore;
					break;
				}
			}
			// High-Highscore, what a day to be alive!
			if (currentScore > scoreList[0]) {
				scoreList[0] = currentScore;
			}
		}
	}

	// Increase score
	public void addToCurrentScore(int currentScore) {
		this.currentScore += currentScore;
		// Highscore!
		if (currentScore > highscore) {
			highscore = currentScore;
			System.out.println(highscore);
		}
	}
}
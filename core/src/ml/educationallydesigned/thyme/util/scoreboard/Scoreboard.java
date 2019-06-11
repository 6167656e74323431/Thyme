/*
	Thyme is an educational game to assist teenagers in time management, and tracking.
	Copyright (C) 2019 Theodore Preduta, Larry Yuan

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU Affero General Public License as published
	by the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU Affero General Public License for more details.

	You should have received a copy of the GNU Affero General Public License
	along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package ml.educationallydesigned.thyme.util.scoreboard;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Main interface for the scoreboard, handles reading and writing.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 25 min</li>
 * <li>Larry - 1.5 hrs</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.1
 */

public class Scoreboard {
	/** the locaiton of the scores */
	private static String scoresLocation;
	/** the scores to be manipulated */
	private List<Score> scores;

	/**
	 * Initiates the scoreboard with the database and the id random source.
	 */
	public Scoreboard() throws IOException {
		// check if windows or linux to determine where to store the scores
		// on windows, we use appdata to store the scores
		// on linux, we use the home directory
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			scoresLocation = System.getenv("APPDATA") + "/.thyme_scores";
		} else {
			scoresLocation = System.getProperty("user.home") + "/.thyme_scores";
		}
		scores = getScores(Integer.MAX_VALUE);
	}

	/**
	 * Sort the list of scores using merge sort.
	 *
	 * @param scores The list of scores to be sorted.
	 */
	private static List<Score> sortScores(List<Score> scores) {
		if (scores.size() < 2)
			return scores;
		// split into two
		int halfSize = scores.size() / 2;
		List<Score> lower = new ArrayList<Score>();
		while (scores.size() > halfSize)
			lower.add(scores.remove(0));
		List<Score> upper = new ArrayList<Score>();
		while (!scores.isEmpty())
			upper.add(scores.remove(0));
		// recursively sort them
		lower = sortScores(lower);
		upper = sortScores(upper);
		// combine them
		List<Score> sorted = new ArrayList<Score>();
		while (!lower.isEmpty() || !upper.isEmpty())
			if (lower.isEmpty())
				sorted.add(upper.remove(0));
			else if (upper.isEmpty())
				sorted.add(lower.remove(0));
			else if (lower.get(0).compareTo(upper.get(0)) > 0)
				sorted.add(upper.remove(0));
			else
				sorted.add(lower.remove(0));
		return sorted;
	}

	/**
	 * Serializes and saves the scores
	 *
	 * @throws IOException when the save fails
	 */
	private void save() throws IOException {
		ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(scoresLocation));
		serializer.writeObject(scores);
		serializer.close();
	}

	/**
	 * Gets the top scores.
	 *
	 * @param numberOfItems The maximum number of scores to
	 *                      retrieve
	 * @return The top scores. The length of which will be the minimum of
	 * the total number of scores and the numberOfItems.
	 * @throws IOException when reading the scores file fails
	 */
	@SuppressWarnings("unchecked")
	public List<Score> getScores(int numberOfItems) throws IOException {
		// if scores file doesn't exist, then return an empty list
		if (!(new File(scoresLocation)).exists()) {
			scores = new ArrayList<Score>();
			save();
			return scores;
		}
		ObjectInputStream deserializer = new ObjectInputStream(new FileInputStream(scoresLocation));
		try {
			scores = (ArrayList<Score>) deserializer.readObject();
		} catch (ClassNotFoundException e) {
			Gdx.app.error("Hacking Attempted!", "Detected an invalid score file, crashing...");
			Gdx.app.exit();
		}
		deserializer.close();
		scores = sortScores(scores);
		return scores.size() > numberOfItems ? scores.subList(0, numberOfItems) : scores;
	}

	/**
	 * Adds a score to the database.
	 *
	 * @param score the score to add
	 */
	public void addScore(Score score) throws IOException {
		scores.add(score);
		save();
	}

	/**
	 * Gets the number of scores in the database.
	 *
	 * @return the number of scores
	 * @throws IOException when reading the scores list fails
	 */
	public int numScores() throws IOException {
		return scores.size();
	}

	/**
	 * Clears the score database
	 *
	 * @throws IOException when saving the scores list fails
	 */
	public void clear() throws IOException {
		scores.clear();
		save();
	}
}

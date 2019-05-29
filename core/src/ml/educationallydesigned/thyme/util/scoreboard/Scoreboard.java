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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


/**
 * Main interface for the scoreboard, handles reading and writing.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */

public class Scoreboard {
	private static final String SCOREBOARD_NODE = "ml/educationallydesigned/thyme/core/Scoreboard";
	private static final int ID_LENGTH = 16;
	private static final String ID_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private Preferences scoreDatabase;
	private Random random;

	/**
	 * Initiates the scoreboard with the database and the id random source.
	 */
	public Scoreboard() {
		scoreDatabase = Preferences.userRoot().node(SCOREBOARD_NODE);
		random = new Random();
	}

	/**
	 * Creates a unique ID that has not been used in the database yet
	 *
	 * @return the unique ID
	 */
	private String makeId() {
		StringBuilder id;
		do {
			id = new StringBuilder();
			for (int i = 0; i < ID_LENGTH; i++) {
				id.append(ID_CHARACTERS.charAt(random.nextInt(ID_LENGTH)));
			}
		} while (scoreDatabase.get(id.toString(), null) != null);

		return id.toString();
	}

	/**
	 * Gets the top scores.
	 *
	 * @param numberOfItems The maximum number of scores to retrieve
	 * @return The top scores. The length of which will be the minimum of the total number of scores and the numberOfItems.
	 * @throws BackingStoreException when reading the scores from database fails
	 */
	public List<Score> getScores(int numberOfItems) throws BackingStoreException {
		List<Score> scores = new ArrayList<Score>();
		for (String key : scoreDatabase.keys()) {
			scores.add(Score.fromString(scoreDatabase.get(key, null)));
		}
		Collections.sort(scores);
		return scores.size() < numberOfItems ? scores : scores.subList(0, numberOfItems);
	}

	/**
	 * Adds a score to the database.
	 *
	 * @param score the score to add
	 * @return the ID of the created score
	 */
	public String addScore(Score score) {
		String serialized = score.toString();
		String id = makeId();
		scoreDatabase.put(id, serialized);
		return id;
	}

	/**
	 * Gets the number of scores in the database.
	 *
	 * @return the number of scores
	 * @throws BackingStoreException when reading the database fails
	 */
	public int numScores() throws BackingStoreException {
		return scoreDatabase.keys().length;
	}

	/**
	 * Clears the score database
	 *
	 * @throws BackingStoreException when clearing the database fails
	 */
	public void clear() throws BackingStoreException {
		scoreDatabase.clear();
	}


}

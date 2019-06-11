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

import java.io.Serializable;

/**
 * Score data class.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 10 min</li>
 * <li>Larry - 10 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1
 */
public class Score implements Comparable<Score>, Serializable {
	/** the username */
	public final String username;
	/** the socre */
	public final int score;

	/**
	 * Initiates the score with name and score
	 *
	 * @param username the username of the player
	 * @param score    the score of the player
	 */
	public Score(String username, int score) {
		this.username = username;
		this.score = score;
	}

	/**
	 * Convert from the string representation of the score into the Score data object.
	 *
	 * @param serialized string version of the score
	 * @return The serialized score
	 * @throws NumberFormatException thrown when the score is invalid
	 */
	public static Score fromString(String serialized) throws NumberFormatException {
		String[] split = serialized.split("\n");
		return new Score(split[0], Integer.parseInt(split[1]));
	}

	/**
	 * Serializes the score data object into a string.
	 * <p>
	 * The string format is: username and newline separated by a newline character (\n)
	 *
	 * @return The string representation of the score.
	 */
	@Override
	public String toString() {
		return this.username + "\n" + this.score;
	}

	/**
	 * Compares the current score and another score.
	 *
	 * @param o other score
	 * @return the result of the comparison (positive if larger, 0 if same, negative if smaller)
	 */
	@Override
	public int compareTo(Score o) {
		return o.score - this.score;
	}
}

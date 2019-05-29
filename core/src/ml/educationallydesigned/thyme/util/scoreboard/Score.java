package ml.educationallydesigned.thyme.util.scoreboard;

/**
 * Score data class.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */

public class Score implements Comparable<Score> {
	public String username;
	public int score;

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

/*
 *
 * 	Thyme is an educational game to assist teenagers in time management, and tracking.
 * 	Copyright (C) 2019 Theodore Preduta, Larry Yuan
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU Affero General Public License as published
 * 	by the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 *
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * /
 */

package ml.educationallydesigned.thyme.util.task;

import ml.educationallydesigned.thyme.core.levels.EscapeRoom;

/**
 * Class to store all data required for each individual in-game survey.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 20 min</li>
 * <li>Larry - </li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class Survey extends Task {
	private EscapeRoom room;

	public Survey(String title, String description, String[] questions, EscapeRoom room) {
		super(title, description, questions, null, 100.0);

		this.room = room;
	}

	/**
	 * Submits the given numbers to the room class
	 *
	 * @param givenAnswers The given answers
	 * @return true if all answers are integers, false otherwise.
	 */
	@Override
	public boolean submit(String[] givenAnswers) {
		this.givenAnswers = givenAnswers;
		int[] estimates = new int[givenAnswers.length];

		attemptPercentage = 0.0;
		numOfAttempts++;

		// add number of correct answers
		for (int i = 0; i < givenAnswers.length; i++) {
			try {
				estimates[i] = Integer.parseInt(givenAnswers[i]);
				attemptPercentage++;
			} catch (NumberFormatException e) {
			}
		}

		// calcualte percentage
		attemptPercentage /= (double) givenAnswers.length;
		attemptPercentage *= 100;

		if (minPassPercentage <= attemptPercentage) {
			tracker.stop();
			room.addEstimates(estimates);
			return true;
		} else
			return false;
	}
}
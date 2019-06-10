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

package ml.educationallydesigned.thyme.util.task;

import ml.educationallydesigned.thyme.core.levels.EscapeRoom;
import ml.educationallydesigned.thyme.util.*;

/**
 * Class to store all data required for each individual in-game survey. Surveys
 * are tasks, with no 'right' answer <b>Time Spent:</b> <ul>
 * <li>Theodore - 25 min</li>
<<<<<<< HEAD
 * <li>Larry - 0 min</li>
 * </ul>
=======
 * <li>Larry - 0 min</li> </ul>
>>>>>>> theodore
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 * @version    1.2
 */
public class Survey extends Task {
	private EscapeRoom room;

	/**
	 * Constructs the survey.
	 *
	 * @param      title        The title of the survey
	 * @param      description  The description of the survey
	 * @param      questions    The questions in the survey
	 * @param      room         The room containing the survey
	 */
	public Survey(String title, String description, String[] questions, EscapeRoom room) {
		super(title, description, questions, null, 100.0);

		this.room = room;
	}

	/**
	 * Submits the given numbers to the room class
	 *
	 * @param      givenAnswers  The given answers
	 *
	 * @return     true if all answers are integers, false otherwise.
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
		} else {
			Dialog.showDefaultDialog(room.getStage(), "Task Quizzer", "You should only enter numbers for this task.");
			return false;
		}
	}
}
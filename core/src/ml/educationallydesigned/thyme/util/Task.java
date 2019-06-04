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

package ml.educationallydesigned.thyme.util;

import ml.educationallydesigned.thyme.util.time.*;

/**
 * Class to store all data required for each individual in-game task.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class Task {
	private String title;
	private String description;
	private String[] questions;
	private String[] expectedAnswers;
	private String[] givenAnswers;
	private double minPassPercentage;
	private double attemptPercentage;
	private int numOfAttempts;
	private Timer tracker;

	/**
	 * Constructs the task object.
	 *
	 * @param      title              The title of the task.
	 * @param      description        The description of the task.
	 * @param      questions          The questions needed to be answered for
	 *                                this task.
	 * @param      expectedAnswers    The expected answers that are required.
	 * @param      minPassPercentage  The minimum pass percentage for the
	 *                                questions.
	 */
	public Task(String title, String description, String[] questions, String[] expectedAnswers, double minPassPercentage) {
		this.title = title;
		this.description = description;
		this.questions = questions;
		this.expectedAnswers = expectedAnswers;
		this.minPassPercentage = minPassPercentage;
		attemptPercentage = -1.0;
		numOfAttempts = 0;
		tracker = new Timer();
		tracker.pause();
	}

	/**
	 * Gets the title of the task.
	 *
	 * @return     The title of the task.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the description of the task.
	 *
	 * @return     The description of the task.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the questions in the quiz.
	 *
	 * @return     The questions in the quiz.
	 */
	public String[] getQuestions() {
		return questions;
	}

	/**
	 * Gets the minimum pass percentage for the quiz.
	 *
	 * @return     The minimum pass percentage for the quiz.
	 */
	public double getMinPassPercentage() {
		return minPassPercentage;
	}

	/**
	 * Starts the timer for this task
	 */
	public void start() {
		tracker.unpause();
	}

	/**
	 * Checks the answers for errors.
	 *
	 * @param      givenAnswers  The given answers to check
	 *
	 * @return     true if the task is completed, false otherwise.
	 */
	public boolean submit(String[] givenAnswers) {
		this.givenAnswers = givenAnswers;

		attemptPercentage = 0.0;
		numOfAttempts++;

		// add number of correct answers
		for (int i = 0; i < expectedAnswers.length; i++)
			if (check(givenAnswers[i], expectedAnswers[i]))
				attemptPercentage++;

		// calcualte percentage
		attemptPercentage /= (double)expectedAnswers.length;
		attemptPercentage *= 100;

		if (minPassPercentage <= attemptPercentage) {
			tracker.stop();
			return true;
		} else
			return false;
	}

	/**
	 * Gets the time elapsed since the start.
	 *
	 * @return     The timeelapsed since the sart in miliseconds.
	 */
	public long getTime() {
		return tracker.getTime() / 1000000;
	}

	/**
	 * Compares two strings, sees if they are close enough
	 *
	 * @param      given   The given answer
	 * @param      target  The target answer
	 *
	 * @return     true if given is correct enough, flase otherwise.
	 */
	private boolean check(String given, String target) {
		return given.equals(target);
	}
}
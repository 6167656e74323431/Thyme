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

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

/**
 * Class to generate random tasks.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 20 min</li>
 * <li>Larry - 40 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class TaskGenerator {
	/** index of all tasks */
	private static String[] index = Gdx.files.internal("tasks/index").readString().replaceAll("\r", "").split("\n");
	/** array of all tasks */
	private static Task[] possibleTasks = new Task[index.length];
	/** random object to generate tasks */
	private static Random random = new Random();

	static {
		// read all tasks from index
		for (int i = 0; i < index.length; i++) {
			// make buffered reader from file
			BufferedReader reader = new BufferedReader(Gdx.files.internal("tasks/" + index[i]).reader());
			try {
				// name and description
				String name = reader.readLine(), description = reader.readLine();
				// min pass percentage & number of questions
				int minPassPercentage = Integer.parseInt(reader.readLine()), nQuestions = Integer.parseInt(reader.readLine());
				String[] questions = new String[nQuestions], answers = new String[nQuestions];
				// questions
				for (int j = 0; j < nQuestions; j++) {
					questions[j] = reader.readLine();
				}
				// answers
				for (int j = 0; j < nQuestions; j++) {
					answers[j] = reader.readLine();
				}
				possibleTasks[i] = new Task(name, description, questions, answers, minPassPercentage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Private constructor to ensure no one creates an instance of this class.
	 */
	private TaskGenerator() {

	}

	/**
	 * Generate a task
	 *
	 * @return The same task each time lol
	 */
	public static Task generateTask() {
		return possibleTasks[random.nextInt(possibleTasks.length)];
	}
}
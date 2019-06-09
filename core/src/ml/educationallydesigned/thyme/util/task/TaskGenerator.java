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

import ml.educationallydesigned.thyme.util.task.Task;

/**
 * Class to generate random tasks.
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
public class TaskGenerator {
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
		String[] ab = {"a", "b"};
		return new Task("a", "b", ab, ab, 50.0);
	}
}
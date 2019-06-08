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

package ml.educationallydesigned.thyme.core.levels;

import com.badlogic.gdx.Game;
import ml.educationallydesigned.thyme.Thyme;
import ml.educationallydesigned.thyme.core.windows.TextEditorWindow;
import ml.educationallydesigned.thyme.core.windows.TrackerWindow;
import ml.educationallydesigned.thyme.util.Task;
import ml.educationallydesigned.thyme.util.TaskGenerator;

/**
 * Class to implement the first room gamemode.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 30 min</li>
 * <li>Larry - </li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.2
 */
public class DeficiencyRoom extends GameLevel {
	/**
	 * Constructs the object.
	 *
	 * @param game The main game object
	 */
	public DeficiencyRoom(Thyme game) {
		super(game);
	}

	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 */
	@Override
	public void show() {
		// call GameLevel's show to initilize protected variables
		super.show();

		String[] tutorialQuestions = {"What is the name of the QnA window?",
				"What is the name of the browser?"};

		String[] tutorialAnswers = {"LarryOffice",
				"?"};

		tasks.add(new Task("Tutorial", "Explore the desktop, and see if you can answer the questions in the editor.",
				tutorialQuestions, tutorialAnswers, 100.0) {
			private boolean check(String given, String target) {
				return given.indexOf(target) >= 0;
			}
		});

		tasks.add(TaskGenerator.generateTask());

		// open the windows
		TrackerWindow tracker = new TrackerWindow(tasks.get(currentTask), this);
		TextEditorWindow editor = new TextEditorWindow(tasks.get(currentTask), this);
		stage.addActor(tracker);
		windows.add(tracker);
		stage.addActor(editor);
		windows.add(editor);

		// start current task
		tasks.get(currentTask).start();
	}

	/**
	 * Calculates the score once this level is done.
	 *
	 * @return the score for the deficciancy room.
	 */
	@Override
	protected int calcScore() {
		int attempts = -tasks.size();
		double averagePercentage = 0.0;

		for (Task t : tasks) {
			attempts += t.getNumOfAttempts();
			averagePercentage += t.getAttemptPercentage();
		}

		averagePercentage /= tasks.size();

		return Math.max(0, (int) ((10000 - attempts * 100) * averagePercentage));
	}
}
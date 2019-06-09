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

package ml.educationallydesigned.thyme.core.windows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import ml.educationallydesigned.thyme.core.levels.GameLevel;
import ml.educationallydesigned.thyme.util.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement the text editor window for in the Thyme game.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 80 min</li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.2
 */
public class TextEditorWindow extends DesktopWindow {
	private List<VisTextField> answerBoxes;
	private Task currentTask;
	private GameLevel level;

	/**
	 * Initializes, and draws the window.
	 *
	 * @param currentTask The current task
	 * @param level       The current level
	 */
	public TextEditorWindow(Task currentTask, GameLevel level) {
		super("LarryOffice Writer");
		this.currentTask = currentTask;
		this.level = level;

		setWidth(500);
		setHeight(500);
		align(Align.top);

		answerBoxes = new ArrayList<VisTextField>();

		for (String question : currentTask.getQuestions()) {
			VisTextField field = new VisTextField();
			field.setAlignment(Align.center);
			add(new VisLabel(question)).padBottom(20).row();
			add(field).padBottom(20).width(200).row();
			answerBoxes.add(field);
		}

		VisTextButton submitButton = new VisTextButton("Submit");
		submitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				boolean result = TextEditorWindow.this.submit();
				if (!result)
					clearAnswers();
			}
		});
		add(submitButton).row();
	}

	/**
	 * Prepares the input, and submits it through the gamelevel.
	 */
	public boolean submit() {
		String[] attempt = new String[answerBoxes.size()];

		for (int i = 0; i < answerBoxes.size(); i++)
			attempt[i] = answerBoxes.get(i).getText();

		return level.submit(attempt);
	}

	/**
	 * Clears all the text fields.
	 */
	public void clearAnswers() {
		for (VisTextField field : answerBoxes)
			field.setText("");
	}
}
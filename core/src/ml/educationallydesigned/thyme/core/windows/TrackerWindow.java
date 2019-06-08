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

import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ml.educationallydesigned.thyme.util.Task;
import ml.educationallydesigned.thyme.core.levels.GameLevel;

/**
 * Window to track the progress of tasks in the game.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.5
 */
public class TrackerWindow extends DesktopWindow {
	private Task currentTask;
	private GameLevel level;
	private VisLabel title;
	private VisLabel description;
	private VisLabel accuracy;

	/**
	 * Creates a new tracker window and allows the specification of the task
	 * list.
	 *
	 * @param      currentTask  The current task
	 * @param      level        The level
	 */
	public TrackerWindow(Task currentTask, GameLevel level) {
		super("Current Task");
		this.level = level;

		setWidth(500);
		setHeight(500);
		align(Align.topLeft);

		// create the window
		VisTable mainContainer = new VisTable();

		title = new VisLabel("");
		mainContainer.add(title).row();
		
		description = new VisLabel("");
		VisLabel.LabelStyle descriptionStyle = description.getStyle();
		descriptionStyle.font = VisUI.getSkin().getFont("small-font");
		description.setStyle(descriptionStyle);
		description.setWrap(true);
		mainContainer.add(description).row();

		accuracy = new VisLabel("");
		mainContainer.add(accuracy).row();

		add(mainContainer);

		// set the text in the window
		updateTask(currentTask);
	}

	/**
	 * Changes the task that is being displayed.
	 *
	 * @param      t     The task that is being displayed.
	 */
	public void updateTask(Task t) {
		currentTask = t;

		title.setText(currentTask.getTitle());
		description.setText(currentTask.getDescription());
		accuracy.setText("Pass Percentage: " + currentTask.getMinPassPercentage() + "%");
	}
}
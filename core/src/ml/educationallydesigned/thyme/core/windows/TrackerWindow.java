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

import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import ml.educationallydesigned.thyme.core.levels.GameLevel;
import ml.educationallydesigned.thyme.util.task.Task;

/**
 * Window to track the progress of tasks in the game.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 60 min</li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.5
 */
public class TrackerWindow extends DesktopWindow {
	/** default height */
	private static int DEFAULT_WIDTH = 550;
	/** default width */
	private static int DEFAULT_HEIGHT = 225;
	/** the current task */
	private Task currentTask;
	/** the current level */
	private GameLevel level;
	/** The title */
	private VisLabel title;
	/** the description */
	private VisLabel description;
	/** the minimum pass percentage */
	private VisLabel accuracy;

	/**
	 * Creates a new tracker window and allows the specification of the task
	 * list.
	 *
	 * @param currentTask The current task
	 * @param level       The level
	 */
	public TrackerWindow(Task currentTask, GameLevel level) {
		super("Current Task");
		this.level = level;
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
		this.align(Align.topLeft);

		// create the window
		VisTable mainContainer = new VisTable();
		mainContainer.setWidth(DEFAULT_WIDTH - 50);
		mainContainer.align(Align.topLeft);
		mainContainer.padLeft(10);

		title = new VisLabel("");
		VisLabel.LabelStyle titleStyle = title.getStyle();
		titleStyle.font = VisUI.getSkin().getFont("default-font");
		title.setStyle(titleStyle);
		mainContainer.add(title).width(DEFAULT_WIDTH - 50).row();

		description = new VisLabel("");
		description.setAlignment(Align.left);
		VisLabel.LabelStyle descriptionStyle = description.getStyle();
		descriptionStyle.font = VisUI.getSkin().getFont("small-font");
		description.setStyle(descriptionStyle);
		description.setWrap(true);
		mainContainer.add(description).width(DEFAULT_WIDTH - 50).align(Align.left).row();

		accuracy = new VisLabel("");
		mainContainer.add(accuracy).width(DEFAULT_WIDTH - 50).row();

		VisScrollPane scrollPane = new VisScrollPane(mainContainer);
		scrollPane.setFadeScrollBars(false);
		add(scrollPane).width(DEFAULT_WIDTH);
		setX(0);
		setY(0);
		// remove close button
		getTitleTable().removeActor(getTitleTable().getChildren().get(1));
		// set the text in the window
		updateTask(currentTask);
	}

	/**
	 * Changes the task that is being displayed.
	 *
	 * @param t The task that is being displayed.
	 */
	public void updateTask(Task t) {
		currentTask = t;
		title.setText(currentTask.getTitle());
		description.setText(currentTask.getDescription());
		accuracy.setText("Pass Percentage: " + currentTask.getMinPassPercentage() + "%");
	}
}
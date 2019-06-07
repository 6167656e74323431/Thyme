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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import ml.educationallydesigned.thyme.util.Task;

/**
 * Window to track the progress of tasks in the game.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.3
 */
public class TrackerWindow extends DesktopWindow {
	private Task currentTask;

	/**
	 * Creates a new tracker window and allows the specification of the task
	 * list.
	 * 
	 * @param       taskList  List of tasks that still need to be done.
	 */
	public TrackerWindow(Task currentTask) {
		super("Current Task");
		this.currentTask = currentTask;

		setWidth(500);
		setHeight(500);
		align(Align.topLeft);


		VisLabel label = new VisLabel(currentTask.getTitle());
		label.setWrap(true);
		add(label).row();
		label = new VisLabel(currentTask.getDescription());
		label.setWrap(true);
		add(label).row();
	}
}
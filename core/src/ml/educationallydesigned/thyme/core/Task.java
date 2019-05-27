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

package ml.educationallydesigned.thyme.core;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class to store all data required for each individual in-game task.
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 *
 * @version    1.1
 */
public class Task implements Iconable {
	public static String DEFAULT_TASK_ICON_PATH = "";

	private String iconPath;
	private Texture loadedIcon;
	private boolean isIconLoaded;
	private String title;
	private String description;
	private int priority;

	/**
	 * Constructs the task object.
	 *
	 * @param      title        The title of the task.
	 * @param      description  The description of the task.
	 * @param      priority     The priority of the task, a higher nujmber means
	 *                          a higher priority.
	 */
	public Task(String title, String description, int priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		iconPath = DEFAULT_TASK_ICON_PATH;
		isIconLoaded = false;
		tracker = new Timer();
	}

	/**
	 * Loads a task's icon.
	 *
	 * @return     true if something was loaded, false if nothing has changed.
	 */
	public boolean loadIcon() {
		if (!isIconLoaded) {
			loadedIcon = new Texture(iconPath);
			return true;
		}
		return false;
	}

	/**
	 * Gets the icon.
	 *
	 * @return     The icon as a com.badlogic.gdx.graphics.Texture.
	 */
	public Texture getIcon()  {
		return loadedIcon;
	}

	/**
	 * Sets the icon path.
	 *
	 * @param      path  The path of the new icon.
	 *
	 * @return     true if the path changed, false otherwise.
	 */
	public boolean setIcon(String path) {
		if (!path.equals(iconPath)) {
			iconPath = path;
			isIconLoaded = false;
			return true;
		}
		return false;
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
	 * Gets the priority of the task.
	 *
	 * @return     The priority of the task.
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Function that allows the texture to be destroyed by will.
	 */
	public void dispose() {
		loadedIcon.dispose();
		loadedIcon = null;
	}
}
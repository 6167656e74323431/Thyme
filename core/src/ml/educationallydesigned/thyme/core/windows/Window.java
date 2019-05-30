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

import java.awt.*;

/**
 * Class to outline and implement some methods that all winows have.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1.1
 */
public abstract class Window {
	private boolean visibility;
	private int id;
	private String name;
	private Point size;
	private Point location;

	/**
	 * Most powerful window constructor.
	 *
	 * @param visibility The visibility of the window.
	 * @param name       The name of the window.
	 * @param size       The size of the window as a Point of the max X
	 *                   and max Y if the location is (0, 0).
	 * @param location   The location of the window on screen.
	 */
	public Window(boolean visibility, String name, Point size, Point location) {
		this.visibility = visibility;
		id = 0;
		this.name = name;
		this.size = size;
		this.location = location;
	}

	/**
	 * Constructor where only the name is specified. Other parameters are set as
	 * follows <br> <ul>
	 * <li>visibility - false.</li>
	 * <li>size - (100, 100).</li>
	 * <li>location - (0, 0).</li> </ul>
	 *
	 * @param name The name of the window
	 */
	public Window(String name) {
		this(false, name, new Point(100, 100), new Point());
	}

	/**
	 * Gets called when the window becomes inactive.
	 */
	public abstract void onInactivity();

	/**
	 * Gets called when the window becomes active.
	 */
	public abstract void onActivity();

	/**
	 * Function that draws the windows graphics.
	 */
	public abstract void drawWindow();

	/**
	 * Sets the id of the window. This can only be done by an instance of the
	 * Desktop class to ensure no multiple assignments
	 *
	 * @param id      The unique identifier to be set.
	 * @param manager The manager class instance that is assigning the id.
	 */
	public void setID(int id, Object manager) {
		if (manager instanceof Desktop)
			this.id = id;
	}

	/**
	 * Returns the id of the window instance.
	 *
	 * @return The unique id of the window instance.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Changes the visibility of the window. Calls either onInvisibility or
	 * onVisibility depending on the new visibility.
	 */
	public void toggleVisibility() {
		visibility = !visibility;
	}

	/**
	 * Gets the visibility of the window.
	 *
	 * @return The visibility of the window, true meaning the window is
	 * visible.
	 */
	public boolean getVisibility() {
		return visibility;
	}
}
/*
	Thyme is an educational game to assist teenagers in time management, and tracking.
	Copyright (C) 2019 Theodore Preduta

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

import java.util.List;
import java.util.ArrayList;

/**
 * Class to manage all the windows in our virttual desktop.
 *
 * @author     Theodore Preduta
 *
 * @version    1.1
 */
public class Desktop {
	private List<Window> visibleWindows;
	private List<Window> invisibleWindows;
	private int nextWindowID;

	/**
	 * Constructor to initize all the instance variables for the Desktop class.
	 */
	public Desktop() {
		visibleWindows = new ArrayList<Window>();
		invisibleWindows = new ArrayList<Window>();
		nextWindowID = 1;
	}

	/**
	 * Adds a window to the list of the invisible windows.
	 *
	 * @param      newWindow  The window to be added
	 *
	 * @return     Returns the unique id that this window has, that was assigned
	 *             by the desktop class
	 */
	public int addWindow(Window newWindow) {
		if (newWindow == null)
			throw new IllegalArgumentException("Windows must be pre-initialized.");

		newWindow.setID(nextWindowID++, this);
		newWindow.onInactivity();
		invisibleWindows.add(newWindow);
		return newWindow.getID();
	}

	/**
	 * Goes through both the invisible, and visible windows, and removes the
	 * window with the specified id.
	 *
	 * @param      id    The identifier of the window.
	 *
	 * @return     true if a window was removed, false otherwise.
	 */
	public boolean removeWindow(int id) {
		for (int i = 0; i < visibleWindows.size(); i++)
			if (visibleWindows.get(i).getID() == id) {
				visibleWindows.remove(i);
				return true;
			}
		for (int i = 0; i < invisibleWindows.size(); i++)
			if (invisibleWindows.get(i).getID() == id) {
				invisibleWindows.remove(i);
				return true;
			}

		return false;
	}

	/**
	 * Makes a window visible.
	 *
	 * @param      id    The identifier of the window.
	 */
	public void makeVisible(int id) {
		for (int i = 0; i < invisibleWindows.size(); i++)
			if (invisibleWindows.get(i).getID() == id) {
				visibleWindows.add(invisibleWindows.remove(i));
				break;
			}
	}

	/**
	 * Makes a window invisible.
	 *
	 * @param      id    The identifier of the window.
	 */
	public void makeInvisible(int id) {
		for (int i = 0; i < visibleWindows.size(); i++)
			if (visibleWindows.get(i).getID() == id) {
				visibleWindows.get(i).onInactivity(); // make window inactive in case it's active
				invisibleWindows.add(visibleWindows.remove(i));
				break;
			}
	}

	/**
	 * Makes a window the active window.
	 *
	 * @param      id    The identifier of the window.
	 */
	public void makeActive(int id) {
		for (int i = 0; i < visibleWindows.size(); i++)
			if (visibleWindows.get(i).getID() == id) {
				visibleWindows.add(visibleWindows.remove(i));
				break;
			}
	}

	/**
	 * Draws the desktop, and sets all invisible windows to inactive, except for
	 * the active window.
	 */
	public void drawDesktop() {
		for (int i = 0; i < visibleWindows.size() - 1; i++) {
			visibleWindows.get(i).onInactivity();
			visibleWindows.get(i).drawWindow();
		}
		visibleWindows.get(visibleWindows.size() - 1).onActivity();
		visibleWindows.get(visibleWindows.size() - 1).drawWindow();
	}
}
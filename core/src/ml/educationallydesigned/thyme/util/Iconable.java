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

package ml.educationallydesigned.thyme.util;

import com.badlogic.gdx.graphics.Texture;

/**
 * Interface to deine all the methods doe all classes that can have an icon.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1
 */
public interface Iconable {
	/**
	 * Loads the icon.
	 *
	 * @return true if the icon is loaded sucessfully, false otherwise.
	 */
	boolean loadIcon();

	/**
	 * Returns the loaded icon.
	 */
	Texture getIcon();

	/**
	 * Sets the icon path.
	 *
	 * @param path The path of the new icon.
	 * @return true if the icon was changes, false otherwise.
	 */
	boolean setIcon(String path);
}
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

package ml.educationallydesigned.thyme.util.browser;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class representing a bookmark
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - 5 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class Bookmark {
	public String URL;
	public Texture favicon;
	public String name;

	/**
	 * Creates a new bookmark with a name and url
	 *
	 * @param favicon the favicon
	 * @param URL     the url
	 */
	public Bookmark(String name, Texture favicon, String URL) {
		this.name = name;
		this.favicon = favicon;
		this.URL = URL;
	}
}

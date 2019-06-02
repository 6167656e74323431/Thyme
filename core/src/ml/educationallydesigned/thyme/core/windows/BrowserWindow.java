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

import java.net.URI;

/**
 * Class to implement out in-game browser.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1
 */
public class BrowserWindow extends DesktopWindow {
	private static int DEFAULT_WIDTH = 850;
	private static int DEFAULT_HEIGHT = 900;

	/**
	 * Makes the browser window and sets the width and height
	 */
	public BrowserWindow() {
		super("Browser 1.0");
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
		this.centerOnDesktop();
	}

	public boolean browseTo(URI url) {

	}
}
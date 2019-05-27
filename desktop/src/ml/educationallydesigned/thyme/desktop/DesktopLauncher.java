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
package ml.educationallydesigned.thyme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ml.educationallydesigned.thyme.Thyme;

/**
 * Class to launch the game.
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 *
 * @version    1.1
 */

public class DesktopLauncher {
	/**
	 * Launcher for desktop application, configures Lwjgl with some settings.
	 * @param arg Command line arguments
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
<<<<<<< Updated upstream
=======
		// allow software rendering for school computers
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		// full screen borderless mode
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.resizable = false;
>>>>>>> Stashed changes
		new LwjglApplication(new Thyme(), config);
	}
}

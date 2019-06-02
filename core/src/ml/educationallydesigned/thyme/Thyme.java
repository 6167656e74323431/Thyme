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
package ml.educationallydesigned.thyme;

import com.badlogic.gdx.*;
import com.kotcrab.vis.ui.VisUI;
import ml.educationallydesigned.thyme.core.screens.AnimationScreen;
import ml.educationallydesigned.thyme.core.screens.Desktop;
import ml.educationallydesigned.thyme.core.screens.HomeScreen;
import ml.educationallydesigned.thyme.util.Skippable;

/**
 * Launches the game by displaying the first screen.
 *
 * @author Larry Yuan
 * @author Theodore Preduta
 * @version 1.1
 */
public class Thyme extends Game {
	/**
	 * Show the intro animation or the home screen.
	 * <p>
	 * On linux, FFMPEG does not play very well with LibGDX-video
	 */
	@Override
	public void create() {
		VisUI.load(VisUI.SkinScale.X2);

		this.setScreen(new Desktop(this));
		/*
		// check if windows or linux
		// libgdx-video does not currently work well with ffmpeg on linux.
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			this.setScreen(new AnimationScreen(this));
		} else {
			this.setScreen(new HomeScreen(this));
		}
		*/
	}

	/**
	 * Sets the input processor, but allows us to override certain keys.
	 *
	 * @param processor The input processor to use
	 */
	public void setInputProcessor(InputProcessor processor) {
		InputMultiplexer multiplexer = new InputMultiplexer();
		// reserve the escape key so that the current state can be exited.
		multiplexer.addProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char character) {
				if (character == (char) 27 && screen instanceof Skippable) {
					((Skippable) screen).skip();
					return true;
				}
				return false;
			}
		});
		multiplexer.addProcessor(processor);
		Gdx.input.setInputProcessor(multiplexer);
	}
}

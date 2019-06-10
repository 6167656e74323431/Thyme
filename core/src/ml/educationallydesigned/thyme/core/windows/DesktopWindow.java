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

package ml.educationallydesigned.thyme.core.windows;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Class to outline and implement some methods that all winows have.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 1 min</li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class DesktopWindow extends VisWindow {
	/**
	 * Creates the window with a title and close button
	 *
	 * @param title The title of the window
	 */
	public DesktopWindow(String title) {
		super(title);
		WindowStyle windowStyle = new WindowStyle(this.getStyle());
		windowStyle.titleFont = VisUI.getSkin().getFont("small-font");
		this.getTitleTable().padLeft(10);
		this.setStyle(windowStyle);
		this.addCloseButton();
	}

	/**
	 * Center the window on the desktop
	 */
	public void centerOnDesktop() {
		this.setX((Gdx.graphics.getWidth() >> 1) - this.getWidth() / 2);
		this.setY((Gdx.graphics.getHeight() >> 1) - this.getHeight() / 2);
	}

	/**
	 * Called when window should close
	 */
	@Override
	protected void close() {
		remove();
		super.close();
	}

	/**
	 * Override the fade out because it seems to be triggering when its not supposed to
	 */
	@Override
	public void fadeOut(float t) {
	}

	/**
	 * Override the fade out because it seems to be triggering when its not supposed to
	 */
	@Override
	public void fadeOut() {
	}

	/**
	 * Override the fade in because it seems to be triggering when its not supposed to
	 *
	 * @return the window
	 */
	public VisWindow fadeIn(float t) {
		return this;
	}

	/**
	 * Override the fade in because it seems to be triggering when its not supposed to
	 *
	 * @return the window
	 */
	public VisWindow fadeIn() {
		return this;
	}

}
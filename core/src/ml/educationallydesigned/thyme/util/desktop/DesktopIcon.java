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

package ml.educationallydesigned.thyme.util.desktop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

/**
 * Represents a desktop icon
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - </li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
abstract public class DesktopIcon extends VisTable {
	/**
	 * Creates a new desktop icon
	 *
	 * @param name the name of the icon
	 * @param icon the icon
	 */
	public DesktopIcon(String name, Drawable icon) {
		super();
		this.setWidth(75);
		this.add(new VisImage(icon)).width(50).height(50).padBottom(10).row();
		final VisLabel label = new VisLabel(name);
		label.setWrap(true);
		label.setAlignment(Align.center);
		this.add(label).align(Align.center).width(150).row();
		// listen for double clicks
		this.addListener(new ClickListener() {
			long lastClick = Long.MAX_VALUE;

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (lastClick - System.currentTimeMillis() <= 500) {
					DesktopIcon.this.clicked();
					lastClick = Long.MAX_VALUE;
					label.setColor(Color.WHITE);
				} else {
					lastClick = System.currentTimeMillis();
					// visui blue
					label.setColor(VisUI.getSkin().getColor("vis-blue"));
				}
			}
		});
	}

	/**
	 * Event handler for when the icon is double clicked
	 */
	public abstract void clicked();
}

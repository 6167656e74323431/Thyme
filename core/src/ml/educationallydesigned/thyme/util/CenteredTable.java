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

package ml.educationallydesigned.thyme.util;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.widget.VisTable;

public class CenteredTable extends VisTable {
	public CenteredTable() {
		super();
	}

	public void centerTable() {
		// center table
		this.setX((Gdx.graphics.getWidth() >> 1) - this.getWidth() / 2);
		this.setY((Gdx.graphics.getHeight() >> 1) - this.getHeight() / 2);
		// set background color
		BackgroundColor blackBackground = new BackgroundColor("backgrounds/white.png", this.getX(), this.getY(), this.getWidth(), this.getHeight());
		blackBackground.setColor(37, 37, 39, 255);
		this.setBackground(blackBackground);
	}
}

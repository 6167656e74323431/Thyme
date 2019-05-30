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

import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisWindow;

/**
 * Class to outline and implement some methods that all winows have.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class DesktopWindow extends VisWindow {
	public DesktopWindow(String title) {
		super(title);
		WindowStyle style = new WindowStyle(this.getStyle());
		style.titleFont = VisUI.getSkin().getFont("small-font");
		this.setStyle(style);
		this.getTitleTable().padLeft(10);
		this.addCloseButton();
	}
}
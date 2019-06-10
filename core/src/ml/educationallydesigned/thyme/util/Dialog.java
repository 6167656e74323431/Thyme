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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisDialog;

/**
 * Non-level game state.
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
public class Dialog {
	/**
	 * Shows the default OK dialog
	 *
	 * @param stage stage to show
	 * @param title the title
	 * @param text  the text
	 */
	public static void showDefaultDialog(Stage stage, String title, String text) {
		VisDialog dialog = Dialogs.showOKDialog(stage, title, text);
		dialog.setWidth(570);
		dialog.setHeight(200);
		dialog.padLeft(10);
		// center
		dialog.setX((Gdx.graphics.getWidth() >> 1) - dialog.getWidth() / 2);
		dialog.setY((Gdx.graphics.getHeight() >> 1) - dialog.getHeight() / 2);
	}

	/**
	 * Shows the error dialog
	 *
	 * @param stage stage to show
	 * @param title the title
	 * @param e     the exception that was thrown
	 */
	public static void showErrorDialog(Stage stage, String title, Exception e) {
		// display dialog, with the ability to copy the exception under details.
		VisDialog dialog = Dialogs.showErrorDialog(stage, title, e);
		dialog.setWidth(570);
		dialog.setHeight(200);
		dialog.padLeft(10);
		// center
		dialog.setX((Gdx.graphics.getWidth() >> 1) - dialog.getWidth() / 2);
		dialog.setY((Gdx.graphics.getHeight() >> 1) - dialog.getHeight() / 2);
	}
}

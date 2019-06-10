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
import com.kotcrab.vis.ui.widget.Separator;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import ml.educationallydesigned.thyme.util.socialmedia.GifPost;

/**
 * Class to represent the help window
 *
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class HelpWindow extends DesktopWindow {
	private static int DEFAULT_WIDTH = 500;
	private static int DEFAULT_HEIGHT = 1000;

	/**
	 * Normalize file name, removing unneeded characters and extension
	 *
	 * @param name the name of the file
	 * @return the normalized name
	 */
	private String normalizeFileName(String name) {
		return name.substring(0, name.lastIndexOf(".")).replaceAll("[_()]", "");
	}

	/**
	 * Displays the help window
	 */
	public HelpWindow() {
		super("Help");
		setSize(DEFAULT_WIDTH + 30, DEFAULT_HEIGHT);
		VisTable table = new VisTable();
		table.setWidth(DEFAULT_WIDTH + 30);
		// show all the gifs
		String[] helpGifs = Gdx.files.internal("help/index").readString().replaceAll("\r", "").split("\n");
		for (String gif : helpGifs) {
			GifPost gifPost = new GifPost(Gdx.files.internal("help/" + gif).read());
			table.add(gifPost.getActor()).width(DEFAULT_WIDTH).height(DEFAULT_WIDTH / (float) gifPost.getWidth() * gifPost.getHeight()).padBottom(10).row();
			table.add(new VisLabel(normalizeFileName(gif))).padBottom(10).row();
			table.add(new Separator()).width(DEFAULT_WIDTH).height(10).row();
		}
		VisScrollPane scrollPane = new VisScrollPane(table);
		add(scrollPane);
	}
}

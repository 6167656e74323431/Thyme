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

package ml.educationallydesigned.thyme.core.windows.browser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import ml.educationallydesigned.thyme.util.browser.Search;

import java.io.File;
import java.util.List;

/**
 * Class representing a GUI to search through each website.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - 1.5 hr</li>
 * </ul>
 *
 * @author Larry Yuan
 * @author Theodore Preduta
 * @version 1.0
 */
public class SearchView extends VisTable {
	public SearchView(final BrowserWindow window, final String domain) {
		final Search search = new Search(domain);
		// show title
		VisLabel title = new VisLabel("Search");
		VisLabel.LabelStyle titleStyle = title.getStyle();
		titleStyle.font = new BitmapFont(Gdx.files.internal("skins/fonts/arial_large.fnt"));
		title.setStyle(titleStyle);
		this.add(title).align(Align.top).padTop(10).padBottom(20).row();
		// add search box
		final VisTextField searchBox = new VisTextField();
		this.add(searchBox).width(600).padBottom(10).row();
		// add results table
		final VisTable searchResults = new VisTable();
		// message to display when the user should enter a query
		final VisLabel enterTextLabel = new VisLabel("Enter a query for search results");
		VisLabel.LabelStyle enterTextLabelStyle = enterTextLabel.getStyle();
		enterTextLabelStyle.font = VisUI.getSkin().getFont("small-font");
		enterTextLabel.setStyle(enterTextLabelStyle);
		// message to display when there are no search results
		final VisLabel nothingFound = new VisLabel("Nothing found, try entering a longer query.");

		searchResults.add(enterTextLabel);

		this.add(searchResults);
		// add listener for when user types in the text field
		searchBox.addListener(new InputListener() {
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				searchResults.clearChildren();
				searchResults.clear();

				// if nothing entered, display message prompting for input
				if (searchBox.getText().length() == 0) {
					searchResults.add(enterTextLabel);
					return false;
				}
				List<String> results = search.search(searchBox.getText(), 10, searchBox.getText().length() >= 6 ? 70 : 30);
				// if no results, display message
				if (results.size() == 0) {
					searchResults.add(nothingFound);
					return false;
				}
				for (final String result : results) {
					String searchItem = fileName(result);
					VisTextButton searchResult = new VisTextButton(searchItem.length() > 40 ? searchItem.substring(0, 40) + "..." : searchItem);
					searchResult.addListener(new ClickListener() {
						@Override
						public void clicked(InputEvent event, float x, float y) {
							if (result.lastIndexOf(".") == -1) return;
							window.browseTo(domain + "/" + result.substring(0, result.lastIndexOf(".")));
						}
					});
					searchResults.add(searchResult).padBottom(10).row();
				}
				return false;
			}
		});
	}

	private static String fileName(String path) {
		File f = new File(path);
		if (!f.getName().contains(".")) return f.getName();
		// remove path and extension
		return f.getName().substring(0, f.getName().lastIndexOf("."));
	}
}

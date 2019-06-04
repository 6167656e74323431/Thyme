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
import com.badlogic.gdx.files.FileHandle;
import com.kotcrab.vis.ui.widget.VisTable;
import ml.educationallydesigned.thyme.util.browser.Search;

import java.util.Arrays;

/**
 * Class representing a GUI to search through each website.
 *
 * @author Larry Yuan
 * @author Theodore Preduta
 *
 * @version 1.0
 */
public class SearchView extends VisTable {
	public SearchView(String domain) {
		Search search = new Search(domain);
		System.out.println(Arrays.asList(search.search("test", 10)));
	}
}

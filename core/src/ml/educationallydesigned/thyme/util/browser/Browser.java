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

package ml.educationallydesigned.thyme.util.browser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.util.Lml;

/**
 * Class to assist with the reading and parsing of web pages
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - 20 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class Browser {
	private static LmlParser parser = Lml.parser()
			.skin(new Skin(Gdx.files.internal("skins/website")))
			.styles(Gdx.files.internal("styles/website.lss"))
			.build();

	/**
	 * Fetches the page at a specific URL
	 *
	 * @param url the url to fetch
	 * @return the page
	 * @throws NotFoundException When the page cannot be found
	 */
	public static Page fetch(String url) throws NotFoundException {
		try {
			FileHandle file = Gdx.files.internal("websites/" + url.replaceAll("\r", "") + ".xml");
			return new Page(parser.parseTemplate(file));
		} catch (GdxRuntimeException e) {
			// tried to fetch a non existent page
			Gdx.app.error("Failed to read file", e.getMessage());
			throw new NotFoundException();
		}
	}
}

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

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import ml.educationallydesigned.thyme.util.browser.Bookmark;
import ml.educationallydesigned.thyme.util.browser.Browser;
import ml.educationallydesigned.thyme.util.browser.NotFoundException;
import ml.educationallydesigned.thyme.util.browser.Page;

/**
 * Class to implement our in-game browser.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class BrowserWindow extends DesktopWindow {
	private static int DEFAULT_WIDTH = 850;
	private static int DEFAULT_HEIGHT = 900;
	private static Bookmark[] bookmarks = {
			new Bookmark("Site #1", "randomfacts.com"),
			new Bookmark("Site #2", "wikipedia.org"),
			new Bookmark("Site #3", "wikipedia.org"),
			new Bookmark("Site #4", "wikipedia.org"),
			new Bookmark("Site #5", "wikipedia.org"),
			new Bookmark("Site #6", "wikipedia.org"),
	};

	private VisTable browserDisplay;

	private VisTextField urlBar;
	/**
	 * Makes the browser window and sets the width and height
	 */
	public BrowserWindow() {
		super("Browser 1.0");
		// set default parameters for window
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
		this.align(Align.topLeft);
		this.centerOnDesktop();

		// make the top part of the browser (url and bookmarks)
		VisTable topTable = new VisTable();

		// initialize back button
		VisImageButton backButton = new VisImageButton(VisUI.getSkin().getDrawable("icon-arrow-left"));
		backButton.getStyle().up = VisUI.getSkin().getDrawable("button-blue");
		backButton.getStyle().over = VisUI.getSkin().getDrawable("button-blue-over");
		backButton.getStyle().down = VisUI.getSkin().getDrawable("button-blue-down");
		topTable.add(backButton).width(50).height(40);

		BitmapFont smallFont = VisUI.getSkin().getFont("small-font");
		// initialize url bar
		this.urlBar = new VisTextField();
		VisTextField.VisTextFieldStyle urlBarStyle = urlBar.getStyle();
		urlBarStyle.focusBorder = new BaseDrawable(); // no border on active
		urlBarStyle.font = smallFont; // make font smaller
		topTable.add(urlBar).width(DEFAULT_WIDTH - 10 - 50).height(40);
		topTable.row();
		// add top table to the window
		this.add(topTable).width(DEFAULT_WIDTH - 10).padBottom(5).height(40).row();

		// initialize bookmarks bar
		VisTable bookmarksBar = new VisTable();
		// properly style each bookmark
		VisTextButton.VisTextButtonStyle bookmarkButtonStyle = (VisTextButton.VisTextButtonStyle) (new VisTextButton("")).getStyle();
		bookmarkButtonStyle.focusBorder = new BaseDrawable();
		bookmarkButtonStyle.font = smallFont;
		BaseDrawable noBackground = new BaseDrawable();
		noBackground.setLeftWidth(8);
		noBackground.setRightWidth(8);
		bookmarkButtonStyle.up = noBackground;

		// add each bookmark to bar
		for (Bookmark bookmark : bookmarks) {
			VisTextButton bookmarkButton = new VisTextButton(bookmark.name);
			bookmarkButton.setStyle(bookmarkButtonStyle);
			bookmarksBar.add(bookmarkButton).height(40).padRight(10);
		}
		bookmarksBar.align(Align.left);
		bookmarksBar.row();
		this.add(bookmarksBar).width(DEFAULT_WIDTH - 10).height(40).row();

		// add separator
		VisTable separator = new VisTable();
		separator.add(new Separator()).width(DEFAULT_WIDTH - 10).height(5).row();
		this.add(separator).width(DEFAULT_WIDTH - 10).height(5).row();

		// initialize the page renderer
		this.browserDisplay = new VisTable();
		this.browserDisplay.padLeft(10).padRight(10);
		VisScrollPane scrollPane = new VisScrollPane(this.browserDisplay);
		scrollPane.setFlickScroll(true);
		this.add(scrollPane).row();
		this.browseTo("google.com/home");
	}

	/**
	 * Browse to a certain URL
	 * @param URL the url to browse to
	 */
	public void browseTo(String URL) {
		urlBar.setText(" local://" + URL);
		browserDisplay.clearChildren();
		try {
			Page page = Browser.fetch(URL);
			for (Actor element : page.getElements().items) {
				if (element == null) continue;
				Container<Actor> labelContainer = new Container<Actor>(element);
				labelContainer.prefWidth(DEFAULT_WIDTH - 30);
				labelContainer.width(DEFAULT_WIDTH - 30);
				browserDisplay.add(labelContainer).padBottom(10).row();
			}
		} catch (NotFoundException e) {
			VisLabel notFoundLabel = new VisLabel("Page not found!");
			browserDisplay.add(notFoundLabel);
		}
	}
}
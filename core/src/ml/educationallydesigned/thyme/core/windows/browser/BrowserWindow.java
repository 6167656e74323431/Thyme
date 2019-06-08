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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import ml.educationallydesigned.thyme.core.windows.DesktopWindow;
import ml.educationallydesigned.thyme.util.browser.Bookmark;
import ml.educationallydesigned.thyme.util.browser.Browser;
import ml.educationallydesigned.thyme.util.browser.NotFoundException;
import ml.educationallydesigned.thyme.util.browser.Page;

/**
 * Class to implement our in-game browser.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - </li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 2.0
 */
public class BrowserWindow extends DesktopWindow {
	private static int DEFAULT_WIDTH = 850;
	private static int DEFAULT_HEIGHT = 900;
	private static String HOME_URL = "home/home";
	private static Bookmark[] bookmarks = {
			new Bookmark("Wikipedia", new Texture(Gdx.files.internal("favicons/wikipedia.png")), "wikipedia.org"),
	};
	private static String prefix = " local://";
	private VisTable browserDisplay;
	private VisTextField urlBar;
	private String lastURL = HOME_URL;
	private String currentURL = HOME_URL;

	/**
	 * Makes the browser window and sets the width and height
	 */
	public BrowserWindow() {
		super("Browser");
		// set default parameters for window
		this.setWidth(DEFAULT_WIDTH);
		this.setHeight(DEFAULT_HEIGHT);
		this.align(Align.topLeft);
		this.centerOnDesktop();

		// make the top part of the browser (url and bookmarks)
		VisTable topTable = new VisTable();

		// initialize back button
		final VisImageButton backButton = new VisImageButton(VisUI.getSkin().getDrawable("icon-arrow-left"));
		backButton.getStyle().up = VisUI.getSkin().getDrawable("button-blue");
		backButton.getStyle().over = VisUI.getSkin().getDrawable("button-blue-over");
		backButton.getStyle().down = VisUI.getSkin().getDrawable("button-blue-down");
		topTable.add(backButton).width(50).height(40);

		// add listener for back button
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				browseTo(lastURL);
			}
		});

		BitmapFont smallFont = VisUI.getSkin().getFont("small-font");

		// initialize url bar
		this.urlBar = new VisTextField();
		// listen for when user hits enter
		urlBar.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Input.Keys.ENTER) {
					// browse to requested link
					browseTo(getURL());
					// unfocus text box
					getStage().unfocusAll();
				}
				return false;
			}
		});
		VisTextField.VisTextFieldStyle urlBarStyle = urlBar.getStyle();
		urlBarStyle.focusBorder = new BaseDrawable(); // no border on active
		urlBarStyle.font = smallFont; // make font smaller
		topTable.add(urlBar).width(DEFAULT_WIDTH - 10 - 50).height(40);
		topTable.row();
		// add top table to the window
		this.add(topTable).width(DEFAULT_WIDTH - 10).padBottom(5).height(40).row();

		// initialize bookmarks bar
		VisTable bookmarksBar = new VisTable();

		// add each bookmark to bar
		for (final Bookmark bookmark : bookmarks) {
			final VisImageTextButton bookmarkButton = new VisImageTextButton("Wikipedia", new TextureRegionDrawable(bookmark.favicon));

			// properly style bookmark
			VisImageTextButton.VisImageTextButtonStyle bookmarkButtonStyle = bookmarkButton.getStyle();
			// remove background and border
			bookmarkButtonStyle.focusBorder = new BaseDrawable();
			bookmarkButtonStyle.up = new BaseDrawable();
			bookmarkButtonStyle.over = new BaseDrawable();
			bookmarkButtonStyle.down.setLeftWidth(0);
			bookmarkButtonStyle.down.setRightWidth(0);
			bookmarkButtonStyle.focused = new BaseDrawable();
			bookmarkButton.setStyle(bookmarkButtonStyle);

			// make font text small
			VisLabel.LabelStyle bookmarkLabelStyle = bookmarkButton.getLabel().getStyle();
			bookmarkLabelStyle.font = VisUI.getSkin().getFont("small-font");
			bookmarkButton.getLabel().setStyle(bookmarkLabelStyle);

			// make favicon small
			bookmarkButton.getImageCell().height(30);

			// add handler for when user clicks bookmark
			bookmarkButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					BrowserWindow.this.browseTo(bookmark.URL);
				}
			});

			bookmarksBar.add(bookmarkButton).height(35).padRight(10);
		}
		bookmarksBar.align(Align.left);
		bookmarksBar.row();
		this.add(bookmarksBar).width(DEFAULT_WIDTH - 10).height(40).padBottom(5).row();

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
		this.browseTo(HOME_URL);
	}

	/**
	 * Browse to a certain URL
	 *
	 * @param URL the url to browse to
	 */
	public void browseTo(String URL) {
		lastURL = currentURL;
		currentURL = URL;
		urlBar.setText(prefix + URL);
		browserDisplay.clearChildren();
		// check if only the root of the website is being accessed and if so, display search
		// first replaces the final slash if it has one, then checks if there is any slash
		// domain names cannot contain slashes
		if (Gdx.files.internal("websites/" + URL).exists() && !URL.replace("/$", "").contains("/")) {
			browserDisplay.add(new SearchView(this, URL.replace("/", "")))
					.width(DEFAULT_WIDTH - 30);
			return;
		}
		try {
			Page page = Browser.fetch(URL);
			for (Actor element : page.elements.items) {
				// ignore null elements
				if (element == null) continue;
				Container<Actor> actorContainer = new Container<Actor>(element);
				actorContainer.prefWidth(DEFAULT_WIDTH - 30);
				actorContainer.width(DEFAULT_WIDTH - 30);
				browserDisplay.add(actorContainer).padBottom(10).row();
			}
		} catch (NotFoundException e) {
			VisLabel notFoundLabel = new VisLabel("Page not found!");
			browserDisplay.add(notFoundLabel);
		}
	}

	/**
	 * Gets the current URL in bar (not the current page!)
	 */
	public String getURL() {
		return urlBar.getText().replace(prefix, "");
	}
}
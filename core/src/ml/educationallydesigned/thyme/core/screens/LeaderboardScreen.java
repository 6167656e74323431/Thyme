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

package ml.educationallydesigned.thyme.core.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.*;
import ml.educationallydesigned.thyme.Thyme;
import ml.educationallydesigned.thyme.util.scoreboard.Score;
import ml.educationallydesigned.thyme.util.scoreboard.Scoreboard;

import java.util.prefs.BackingStoreException;

/**
 * Non-level game state.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class LeaderboardScreen implements Screen {
	private final static int MAX_ITEMS = 12;
	private Thyme game;
	private Stage stage;
	private Scoreboard scoreboard;
	private VisTable scoresTable;

	/**
	 * Initializes the game instance variable.
	 *
	 * @param game The game
	 */
	public LeaderboardScreen(Thyme game) {
		this.game = game;
	}

	/**
	 * Renders the scoreboard (adds the scores to the table)
	 */
	private void renderScoreboard() {
		try {
			scoresTable.clearChildren();
			if (scoreboard.numScores() == 0) {
				scoresTable.add(new VisLabel("No scores, play the game to see your name here!"));
			} else {
				for (Score score : scoreboard.getScores(MAX_ITEMS)) {
					// display player username
					VisLabel username = new VisLabel(score.username);
					username.setAlignment(Align.left);
					scoresTable.add(username).width(300).align(Align.left);
					// display player score
					VisLabel userScore = new VisLabel(Integer.toString(score.score));
					userScore.setAlignment(Align.right);
					scoresTable.add(userScore).width(300).align(Align.right);
					scoresTable.row();
				}
			}
		} catch (BackingStoreException e) {
			Gdx.app.error("Scoreboard", "Failed to read from database");
			Gdx.app.exit();
		}
	}


	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 * <p>
	 * Creates the GUI items
	 */
	@Override
	public void show() {
		stage = new Stage();
		game.setInputProcessor(stage);
		AssetManager manager = new AssetManager();
		scoreboard = new Scoreboard();

		manager.load("logos/scoreboard.png", Texture.class);
		manager.finishLoading();
		// make table
		VisTable table = new VisTable();
		table.setFillParent(true);

		// display logo
		table.add(new VisImage(manager.get("logos/scoreboard.png", Texture.class)));
		table.row();

		// display scores
		scoresTable = new VisTable();
		renderScoreboard();
		table.add(scoresTable).padBottom(20);
		table.row();

		// display home and clear buttons
		VisTextButton homeButton = new VisTextButton("Back");
		VisTextButton clearButton = new VisTextButton("Clear");

		// add listeners to buttons
		homeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// return home
				game.setScreen(new HomeScreen(game));
			}
		});
		clearButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// clear scoreboard and re-render scoreboard
				try {
					scoreboard.clear();
				} catch (BackingStoreException e) {
					Gdx.app.error("Scoreboard", "Failed to clear database");
					Gdx.app.exit();
				}
				renderScoreboard();
				// display success message
				VisDialog dialog = Dialogs.showOKDialog(stage, "", "Successfully Cleared Scoreboard!");
				dialog.setWidth(570);
				dialog.setHeight(200);
				dialog.pad(20);
			}
		});

		// display home and clear buttons
		VisTable split = new VisTable();
		split.add(homeButton).width(300).height(80);
		split.add(clearButton).width(300).height(80).padLeft(10);
		table.add(split);
		table.row();

		stage.addActor(table);
	}

	/**
	 * Called when the screen should render itself.
	 *
	 * @param delta The time in seconds since the last render.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	/**
	 * @param width
	 * @param height
	 * @see ApplicationListener#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {

	}

	/**
	 * @see ApplicationListener#pause()
	 */
	@Override
	public void pause() {

	}

	/**
	 * @see ApplicationListener#resume()
	 */
	@Override
	public void resume() {

	}

	/**
	 * Called when this screen is no longer the current screen for a {@link Game}.
	 */
	@Override
	public void hide() {

	}

	/**
	 * Called when this screen should release all resources.
	 */
	@Override
	public void dispose() {

	}
}
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
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import ml.educationallydesigned.thyme.Thyme;
import ml.educationallydesigned.thyme.core.levels.DeficiencyRoom;
import ml.educationallydesigned.thyme.core.levels.EscapeRoom;
import ml.educationallydesigned.thyme.core.levels.PanicRoom;

/**
 * Non-level game state.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 10 min</li>
 * <li>Larry - </li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.4
 */
public class HomeScreen implements Screen {
	private Stage stage;
	private Thyme game;

	/**
	 * Constructs the HomeScreen.
	 *
	 * @param game The game that is creating this class
	 */
	public HomeScreen(Thyme game) {
		this.game = game;
	}

	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 * <p>
	 * Creates the various UI components of the home screen.
	 */
	@Override
	public void show() {
		// load assets
		AssetManager manager = new AssetManager();
		manager.load("logos/thyme.png", Texture.class);
		manager.finishLoading();
		// make stage
		stage = new Stage();
		game.setInputProcessor(stage);
		// make table
		VisTable table = new VisTable();
		table.setFillParent(true);
		// make title
		VisImage gameTitle = new VisImage(manager.get("logos/thyme.png", Texture.class));
		table.add(gameTitle).padBottom(20);
		table.row();
		// make level buttons
		VisTextButton levelOneButton = new VisTextButton("Level One");
		table.add(levelOneButton).width(500).height(80).padBottom(20);
		table.row();

		VisTextButton levelTwoButton = new VisTextButton("Level Two");
		table.add(levelTwoButton).width(500).height(80).padBottom(20);
		table.row();

		VisTextButton levelThreeButton = new VisTextButton("Level Three");
		table.add(levelThreeButton).width(500).height(80).padBottom(20);
		table.row();

		// add the scoreboard and exit buttons.
		VisTable split = new VisTable();
		VisTextButton scoreboardButton = new VisTextButton("Scores");

		VisTextButton exitButton = new VisTextButton("Exit");
		split.add(scoreboardButton).width(240).height(80).padRight(20);
		split.add(exitButton).width(240).height(80);
		table.add(split);
		table.row();

		// add listener for exit button
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		// add listener for scoreboard button
		scoreboardButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new LeaderboardScreen(game));
			}
		});

		// add listener for first level
		levelOneButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new DeficiencyRoom(game);
			}
		});

		// add listener for second level
		levelTwoButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new PanicRoom(game);
			}
		});

		// add listener for third level
		levelThreeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				new EscapeRoom(game);
			}
		});

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
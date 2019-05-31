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
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import ml.educationallydesigned.thyme.Thyme;

import java.io.FileNotFoundException;

/**
 * Screen that displays the intro
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.2
 */
public class AnimationScreen implements Screen {
	private VideoPlayer player;
	private Thyme game;

	/**
	 * Initializes the class with the current game
	 *
	 * @param game the current game
	 */
	public AnimationScreen(Thyme game) {
		this.game = game;
	}


	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 */
	@Override
	public void show() {
		player = VideoPlayerCreator.createVideoPlayer();
		// move to home screen when video finishes
		player.setOnCompletionListener(new VideoPlayer.CompletionListener() {
			@Override
			public void onCompletionListener(FileHandle file) {
				game.setScreen(new HomeScreen(game));
			}
		});

		// play the video
		try {
			player.play(Gdx.files.internal("videos/intro.ogv"));
			// set size to size of window
			player.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

			// skip intro handler if the escape key is pressed
			Gdx.input.setInputProcessor(new InputAdapter() {
				@Override
				public boolean keyTyped(char character) {
					// stop video if escape is pressed
					if (character == (char)27) {
						player.stop();
						game.setScreen(new HomeScreen(game));
						return true;
					}
					return false;
				}
			});
		} catch (FileNotFoundException e) {
			Gdx.app.error("Failed to load intro", "Intro file not found");
			Gdx.app.exit();
		}
	}

	/**
	 * Called when the screen should render itself.
	 *
	 * @param delta The time in seconds since the last render.
	 */
	@Override
	public void render(float delta) {
		player.render();
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
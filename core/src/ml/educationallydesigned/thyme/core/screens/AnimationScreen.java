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

package ml.educationallydesigned.thyme.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;

import java.io.FileNotFoundException;

/**
 * Screen that displays the intro
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 *
 * @version    1.1
 */
public class AnimationScreen implements GameState, Screen {
	private VideoPlayer player;
	private Game game;

	/**
	 * Initializes the class with the current game
	 * @param game the current game
	 */
	public AnimationScreen(Game game) {
		this.game = game;
	}

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
		} catch (FileNotFoundException e) {
			Gdx.app.error("Failed to load intro", "Intro file not found");
			Gdx.app.exit();
		}
	}


	/**
	 * Renders the video
	 * @param delta amount of time since last frame (unused)
	 */
	@Override
	public void render(float delta) {
		player.render();
	}

	/**
	 * Called when window has been resized
	 * @param width width of the resized window
	 * @param height height of the resized window
	 */
	@Override
	public void resize(int width, int height) {
		player.resize(width, height);
	}

	/* The following methods are unused, but required due to the Screen interface */
	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public InputProcessor getInputProcessor() {
		return null;
	}
}
package ml.educationallydesigned.thyme.core.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ml.educationallydesigned.thyme.Thyme;
import ml.educationallydesigned.thyme.core.windows.browser.BrowserWindow;

public class Desktop implements Screen {

	private Thyme game;
	private Stage stage;


	/**
	 * Initiates the class with the game.
	 *
	 * @param game
	 */
	public Desktop(Thyme game) {
		this.game = game;
	}

	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 */
	@Override
	public void show() {
		stage = new Stage();
		game.setInputProcessor(stage);
		BrowserWindow window = new BrowserWindow();
		stage.addActor(window);
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

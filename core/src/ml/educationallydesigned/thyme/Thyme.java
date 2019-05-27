package ml.educationallydesigned.thyme;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.*;
import ml.educationallydesigned.thyme.core.GameState;

public class Thyme extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	GameState currentState;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	/**
	 * Method to change the game state of the game that is being run.
	 *
	 * @param      nextState  The next state.
	 */
	private void changeState(GameState nextState) {
		if (currentState != null)
			currentState.dispose();
		InputMultiplexer multiplexer = new InputMultiplexer();
		// reserve the escape key so that the current state can be exited.
		multiplexer.addProcessor(new InputAdapter () {
			public boolean keyTyped(char character) {
				if (character == (char)27) {
					// do something
					return true;
				}
				return false;
			}
		});
		multiplexer.addProcessor(nextState.getInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);
		nextState.render();
		currentState = nextState;
	}
}

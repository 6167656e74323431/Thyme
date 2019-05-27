package ml.educationallydesigned.thyme;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;
import ml.educationallydesigned.thyme.core.HomeScreen;

public class Thyme extends Game {
	@Override
<<<<<<< Updated upstream
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
}
=======
	public void create() {
		VisUI.load(VisUI.SkinScale.X2);
		this.setScreen(new HomeScreen(this));
	}
}
>>>>>>> Stashed changes

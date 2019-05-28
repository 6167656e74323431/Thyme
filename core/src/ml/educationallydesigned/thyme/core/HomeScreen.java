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

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

/**
 * Non-level game state.
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 *
 * @version    1.1
 */
public class HomeScreen implements GameState, Screen {
    private Stage stage;
    private Game game;
    private VisTextButton levelOneButton;
    private VisTextButton levelTwoButton;
    private VisTextButton levelThreeButton;
    private VisImage gameTitle;
    private VisTextButton scoreboardButton;
    private VisTextButton exitButton;
    private AssetManager manager;

    public HomeScreen(Game game) {
        this.game = game;
    }
    
    /* temporary methods for compiling. */

    /**
     * Gets the input processor for this screen
     * @return the input processor
     */
    public InputProcessor getInputProcessor() {
        return stage;
    }

    /**
     * Destroys the screen
     */
    public void dispose() {

    }

    /**
     * Hides the screen
     */
    public void hide() {

    }

    /**
     * Pauses the screen
     */
    public void pause() {

    }

    /**
     * Resumes the game
     */
    public void resume() {

    }

    /**
     * Renders the scene (render-loop)
     *
     * @param delta amount of time passed since last call to render
     */
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    /**
     * Called when the window is resized
     * @param width new width
     * @param height new height
     */
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Displays the home screen and creates all UI components
     */
    public void show() {
        // load assets
        manager = new AssetManager();
        manager.load("logos/thyme.png", Texture.class);
        manager.finishLoading();
        // make stage
        stage = new Stage();
        stage.setViewport(new ScreenViewport());
        // make table
        VisTable table = new VisTable();
        table.setFillParent(true);
        //table.debug();
        Gdx.input.setInputProcessor(stage);
        // make title
        gameTitle = new VisImage(manager.get("logos/thyme.png", Texture.class));
        table.add(gameTitle).padBottom(20);
        table.row();
        // make level buttons
        levelOneButton = new VisTextButton("Level One");
        table.add(levelOneButton).width(500).height(80).padBottom(20);
        table.row();

        levelTwoButton = new VisTextButton("Level Two");
        table.add(levelTwoButton).width(500).height(80).padBottom(20);
        table.row();

        levelThreeButton = new VisTextButton("Level Three");
        table.add(levelThreeButton).width(500).height(80).padBottom(20);
        table.row();

        // add the scoreboard and exit buttons.
        VisTable split = new VisTable();
        scoreboardButton = new VisTextButton("Scores");

        exitButton = new VisTextButton("Exit");
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
                game.setScreen(new ScoreScreen(game));
            }
        });

        stage.addActor(table);
    }
}
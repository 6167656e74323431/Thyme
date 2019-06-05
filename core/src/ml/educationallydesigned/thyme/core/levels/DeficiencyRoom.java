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

package ml.educationallydesigned.thyme.core.levels;

import com.badlogic.gdx.scenes.scene2d.Actor;
import ml.educationallydesigned.thyme.Thyme;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ml.educationallydesigned.thyme.core.windows.*;
import ml.educationallydesigned.thyme.util.*;
import ml.educationallydesigned.thyme.core.screens.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class to implement the first room gamemode.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1
 */
public class DeficiencyRoom extends GameLevel {
	/**
	 * Constructs the object.
	 *
	 * @param      game  The main game object
	 */
	public DeficiencyRoom(Thyme game) {
		super(game);
	}

	/**
	 * Called when this screen becomes the current screen for a {@link Game}.
	 */
	@Override
	public void show() {
		// call GameLevel's show to initilize protected variables
		super.show();

		String[] tutorialQuestions = {"What is the name of the QnA window?",
										"What is the name of the browser?"};

		String[] tutorialAnswers = {"LarryOffice",
									"?"};

		tasks.add(new Task("Tutorial", "Explore the desktop, and see if you can answer the questions in the editor.", 
							tutorialQuestions, tutorialAnswers, 100.0) {
			private boolean check(String given, String target) {
				return given.indexOf(target) >= 0;
			}
		});

		tasks.add(TaskGenerator.generateTask());
		
		// open the windows
		TrackerWindow tracker = new TrackerWindow(tasks.get(currentTask), this);
		TextEditorWindow editor = new TextEditorWindow(tasks.get(currentTask), this);
		stage.addActor(tracker);
		windows.add(tracker);
		stage.addActor(editor);
		windows.add(editor);

		// start current task
		tasks.get(currentTask).start();
	}
}
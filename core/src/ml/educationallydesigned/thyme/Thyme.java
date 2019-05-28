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
package ml.educationallydesigned.thyme;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import ml.educationallydesigned.thyme.core.AnimationScreen;

public class Thyme extends Game {
	@Override
	public void create() {
		VisUI.load(VisUI.SkinScale.X2);
		this.setScreen(new AnimationScreen(this));
	}

}
/*
 *
 * 	Thyme is an educational game to assist teenagers in time management, and tracking.
 * 	Copyright (C) 2019 Theodore Preduta, Larry Yuan
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU Affero General Public License as published
 * 	by the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 *
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * /
 */

package ml.educationallydesigned.thyme.util.socialmedia;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

/**
 * Taken from https://old.reddit.com/r/libgdx/comments/4gwxod/best_way_to_make_an_animated_button_in_scene2d/?ref=share&ref_source=link
 */
public class AnimatedDrawable extends BaseDrawable {

	private Animation animation;
	private float stateTime = 0;

	public AnimatedDrawable(Animation animation){
		this.animation = animation;
		TextureRegion key = (TextureRegion) animation.getKeyFrame(0);
		this.setLeftWidth(key.getRegionWidth() / 2);
		this.setRightWidth(key.getRegionWidth() / 2);
		this.setTopHeight(key.getRegionHeight() / 2);
		this.setBottomHeight(key.getRegionHeight() / 2);
		this.setMinWidth(key.getRegionWidth());
		this.setMinHeight(key.getRegionHeight());
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height){
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion keyFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);
		batch.draw(keyFrame, x, y, width, height);
	}
}
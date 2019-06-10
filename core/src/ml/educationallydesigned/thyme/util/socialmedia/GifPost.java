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

package ml.educationallydesigned.thyme.util.socialmedia;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.holidaystudios.tools.GifDecoder;
import com.kotcrab.vis.ui.widget.VisImage;

import java.io.InputStream;

/**
 * Class representing a gif.
 *
 * <b>Time Spent:</b> <ul>
 * <li>Theodore - </li>
 * <li>Larry - 10 min</li> </ul>
 *
 * @author     Theodore Preduta
 * @author     Larry Yuan
 * @version    1.0
 */
public class GifPost {

	private int width;
	private int height;
	private GifDecoder decoder;

	/**
	 * Creates the gif post
	 *
	 * @param gifFile the file to read from
	 */
	public GifPost(InputStream gifFile) {
		decoder = new GifDecoder();
		decoder.read(gifFile);
		decoder.prepAnimation();
		Pixmap firstFrame = decoder.getFrame(0);
		width = firstFrame.getWidth();
		height = firstFrame.getHeight();
	}

	/**
	 * Gets the width of the gif
	 *
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the gif
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the scene2d actor representing the gif
	 *
	 * @return the actor (VisImage)
	 */
	public VisImage getActor() {
		return new VisImage(new AnimatedDrawable(decoder.getAnimation(Animation.PlayMode.LOOP)));
	}
}

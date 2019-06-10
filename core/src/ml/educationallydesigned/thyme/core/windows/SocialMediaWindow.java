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

package ml.educationallydesigned.thyme.core.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;
import ml.educationallydesigned.thyme.util.socialmedia.GifPost;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Class to represent a Social Media window
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 0 min</li>
 * <li>Larry - 3 hr</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class SocialMediaWindow extends DesktopWindow {
	private static int DEFAULT_WIDTH = 500;
	private static int DEFAULT_HEIGHT = 1000;
	private static int DEFAULT_POST_NUMBER = 5;

	private Deque<String> postsFiles;
	private VisTable posts;

	/**
	 * Creates a new window and adds all the default elements to it
	 */
	public SocialMediaWindow() {
		super("RedditBot");
		this.setWidth(DEFAULT_WIDTH + 30);
		this.setHeight(DEFAULT_HEIGHT);
		this.align(Align.top);
		// load postsFiles
		postsFiles = new LinkedList<String>();
		postsFiles.addAll(Arrays.asList(Gdx.files.internal("distractions/index").readString().replaceAll("\r", "").split("\n")));
		// create posts table
		posts = new VisTable();
		posts.align(Align.top);
		for (int i = 0; i < DEFAULT_POST_NUMBER; i++) {
			addPost();
		}
		final VisScrollPane scrollPane = new VisScrollPane(posts);
		scrollPane.setFadeScrollBars(false);
		// lazy loading
		scrollPane.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				if (scrollPane.getScrollPercentY() > 0.7f && postsFiles.size() > 0) {
					addPost();
				}
				return false;
			}
		});
		this.add(scrollPane).width(DEFAULT_WIDTH + 30).height(DEFAULT_HEIGHT).row();
		this.centerOnDesktop();
	}


	/**
	 * Normalize file name, removing unneeded characters and extension
	 *
	 * @param name the name of the file
	 * @return the normalized name
	 */
	private String normalizeFileName(String name) {
		return name.substring(0, name.lastIndexOf(".")).replaceAll("[_()]", "");
	}

	/**
	 * Adds a post (either video or picture)
	 */
	private void addPost() {
		if (postsFiles.peekFirst().endsWith(".gif")) {
			addVideo();
		} else {
			addPicture();
		}
	}

	/**
	 * Add first video in queue to display, loading as much as possible on new thread.
	 */
	private void addVideo() {
		final String name = postsFiles.removeFirst();
		new Thread(new Runnable() {
			@Override
			public void run() {
				final InputStream file = new ByteArrayInputStream(Gdx.files.internal("distractions/" + name).readBytes());
				final GifPost video = new GifPost(file);
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						// add post, upscaling and calculating the ratio
						posts.add(video.getActor()).width(DEFAULT_WIDTH).height(DEFAULT_WIDTH / (float) video.getWidth() * video.getHeight()).padBottom(10).row();
						// remove file name and unwanted characters
						VisLabel videoLabel = new VisLabel(normalizeFileName(name));
						videoLabel.setWrap(true);
						posts.add(videoLabel).width(DEFAULT_WIDTH - 20).padBottom(10).row();
						posts.add(new Separator()).width(DEFAULT_WIDTH).height(10).padBottom(10).row();
					}
				});
			}
		}).start();
	}

	/**
	 * Add the first picture in queue to display.
	 */
	private void addPicture() {
		String name = postsFiles.removeFirst();
		// display image
		Texture image = new Texture(Gdx.files.internal("distractions/" + name));
		posts.add(new VisImage(image)).width(DEFAULT_WIDTH).height(DEFAULT_WIDTH / (float) image.getWidth() * image.getHeight()).padBottom(10).row();
		// display name
		VisLabel videoLabel = new VisLabel(normalizeFileName(name));
		videoLabel.setWrap(true);
		posts.add(videoLabel).width(DEFAULT_WIDTH - 20).padBottom(10).row();
		posts.add(new Separator()).width(DEFAULT_WIDTH).height(10).padBottom(10).row();
	}

	@Override
	protected void close() {

		super.close();
	}
}

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

package ml.educationallydesigned.thyme.util.time;

/**
 * Class to abstract the time functionality for the video game.
 * <b>Time Spent:</b>
 * <ul>
 * <li>Theodore - 10 min</li>
 * <li>Larry - 30 min</li>
 * </ul>
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.1
 */
public class Timer {
	/** the start time */
	private long startTime;
	/** the last pause time */
	private long pauseTime;
	/** the end time */
	private long endTime;
	/** ehtehr or not the timer has finished */
	private boolean hasFinished;

	/**
	 * Makes a timer with a custom start time.
	 *
	 * @param startTime The start time
	 */
	public Timer(long startTime) {
		this.startTime = startTime;
		this.pauseTime = -1;
	}

	/**
	 * Makes a timer with the start time being the current nano time
	 */
	public Timer() {
		this(System.nanoTime());
	}

	/**
	 * Checks if the timer can be mutated.
	 */
	private void checkMutableState() {
		if (hasFinished) {
			throw new InvalidTimerStateException("Timer cannot be changed after its end.");
		}
	}

	/**
	 * Pauses the timer
	 */
	public void pause() {
		checkMutableState();
		this.pauseTime = System.nanoTime();
	}

	/**
	 * Unpause the timer
	 */
	public void unpause() {
		checkMutableState();
		this.startTime += System.nanoTime() - pauseTime;
		this.pauseTime = -1;
	}

	/**
	 * Gets the amount of time elapsed.
	 *
	 * @return The elapsed time.
	 */
	public long getTime() {
		// sets the time to either the time when the timer was stopped or the current time
		long currentTime = (hasFinished ? System.nanoTime() : endTime);
		if (pauseTime == -1) {
			return currentTime - startTime;
		} else {
			return (currentTime - startTime) - (currentTime - pauseTime);
		}
	}

	/**
	 * Stops the timer
	 *
	 * @return     the amount of time the timer has been active for.
	 */
	public long stop() {
		checkMutableState();
		this.endTime = System.nanoTime();
		this.hasFinished = true;
		return getTime();
	}
}
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

package ml.educationallydesigned.thyme.util.browser;

import com.badlogic.gdx.Gdx;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that performs searching on the website index.
 *
 * @author Larry Yuan
 * @author Theodore Preduta
 */
public class Search {
	private String domain;
	private List<String> pages;

	/**
	 * Initializes the object with the domain to search through.
	 *
	 * @param domain the domain
	 */
	public Search(String domain) {
		this.domain = domain;
		// read the index and split for each line
		this.pages = Arrays.asList(Gdx.files.internal("websites/" + domain + "/index").readString().split("\n"));
	}

	/**
	 * Searches through the pages by a keyword and returns the top matches
	 *
	 * @param keyword   the keyword
	 * @param number    the number of results to list
	 * @param threshold the threshold required to be selected
	 */
	public List<String> search(String keyword, int number, int threshold) {
		List<ExtractedResult> results = FuzzySearch.extractSorted(keyword, pages, number);
		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			if (results.get(i).getScore() >= threshold) {
				resultList.add(results.get(i).getString());
			}
		}
		return resultList;
	}

}

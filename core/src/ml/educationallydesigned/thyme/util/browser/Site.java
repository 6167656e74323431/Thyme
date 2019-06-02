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

package ml.educationallydesigned.thyme.util.browser;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Class to represent a Website.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class Site {
	private String hostName = "";
	private Node root;

	/**
	 * Creates a new site with a host name
	 *
	 * @param hostName the hostname to get
	 */
	public Site(String hostName, FileHandle fileTree) {
		this.hostName = hostName;
		this.root = new Node("/");
		XmlReader parser = new XmlReader();
		this.parse(root, parser.parse(fileTree));
	}

	/**
	 * Parses the XML file tree into node representations recursively.
	 *
	 * @param current The current node
	 * @param element The current XML element
	 */
	public void parse(Node current, XmlReader.Element XMLNode) {
		for (int i = 0; i < XMLNode.getChildCount(); i++) {
			XmlReader.Element child = XMLNode.getChild(i);
			if (child.getChildCount() == 0) {
				// no more children, this is a "leaf" node.
				current.addChild(child.getAttribute("name"), child.getAttribute("fileName"));
			} else {
				// there are more children, continue to recurse
				parse(current.addChild(child.getAttribute("name")), child);
			}
		}
	}

	/**
	 * Gets the page with the specified path, returns null if page doesn't exist
	 *
	 * @param path
	 * @return the page
	 */
	public Page fetchPage(String path) {
		String[] split = path.split("/");
		Node currentNode = root;
		for (String pathName : split) {
			Node nextNode = root.getChild(pathName);
			if (nextNode == null) {
				return null;
			}
			currentNode = nextNode;
		}
		return currentNode.getPage();
	}
}
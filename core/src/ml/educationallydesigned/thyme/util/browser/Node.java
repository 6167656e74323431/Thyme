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

import java.util.HashMap;

/**
 * Class to represent node.
 *
 * @author Theodore Preduta
 * @author Larry Yuan
 * @version 1.0
 */
public class Node {
	private HashMap<String, Node> children;
	private String name;
	private Page page;

	/**
	 * Creates a new non-leaf node.
	 *
	 * @param name
	 */
	public Node(String name) {
		this(name, null);
	}

	/**
	 * Creates a new leaf node
	 */
	public Node(String name, Page page) {
		this.name = name;
		this.page = page;
		this.children = new HashMap<String, Node>();
	}

	/**
	 * Gets the subpath with name
	 *
	 * @param name
	 * @return the node of the subpath
	 */
	public Node getChild(String name) {
		return children.get(name);
	}

	/**
	 * Adds a leaf child to the node
	 *
	 * @param name     the name of the child
	 * @param fileName the name of the file
	 * @return the newly created node
	 */
	public Node addChild(String name, String fileName) {
		Node newNode = new Node(name, new Page(name, fileName));
		children.put(name, newNode);
		return newNode;
	}

	/**
	 * Adds a new inode to the node.
	 *
	 * @param name The name of the inode
	 */
	public Node addChild(String name) {
		Node newNode = new Node(name);
		this.children.put(name, newNode);
		return newNode;
	}

	/**
	 * Gets the node's associated page, if no page associated, it will return null.
	 */
	public Page getPage() {
		return page;
	}
}
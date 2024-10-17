/**
 * This class represents a node in a quadrant tree, used in the class QuadrantTree.
 */
public class QTreeNode {

	private int x;
	private int y;
	private int size;
	private int color;
	private QTreeNode parent;
	private QTreeNode[] children;

	/**
     * Builds a new QTreeNode object with specific/default values.
     */
	
	public QTreeNode() {
		parent = null;
		children = new QTreeNode[4];
		for (int i = 0; i < 4; i++) {
			children[i] = null;
		}
		x = 0;
		y = 0;
		size = 0;
		color = 0;
	}

	/**
	 * Initializes every new QTreeNode with specified values.
	 * 
	 * @param theChildren the child nodes
	 * @param xcoord      the x-coordinate of the node
	 * @param ycoord      the y-coordinate of the node
	 * @param theSize     the size of the node
	 * @param theColor    the color of the node
	 **/

	public QTreeNode(QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
		children = theChildren;
		x = xcoord;
		y = ycoord;
		size = theSize;
		color = theColor;

	}

	/**
     * Inspects if a node contains the identified coordinate.
     *
     * @param xcoord - x-coordinate to verify
     * @param ycoord - y-coordinate to verify
     * @return true if a node contains the coordinates, false if not
     **/
	public boolean contains(int xcoord, int ycoord) {
		if (xcoord < x || xcoord >= (x + size) || ycoord < y || ycoord >= (y + size)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets the x-coordinate of a tree node.
	 *
	 * @return the x-coordinate
	 */
	
	public int getx() {
		return x;
	}

	/**
	 * Gets the y-coordinate of a tree node.
	 *
	 * @return the y-coordinate
	 */
	
	public int gety() {
		return y;
	}

	/**
	 * Gets the size of a tree node.
	 *
	 * @return the size
	 */
	
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the color of a tree node.
	 *
	 * @return the color
	 */

	public int getColor() {
		return color;
	}

	/**
	 * Retrieves the parent node in relation to the current node.
	 *
	 * @return the parent node
	 */
	
	public QTreeNode getParent() {
		return parent;
	}

	/**
	 * Gets a child node at the stated index.
	 *
	 * @param index is the index of the child node to retrieve
	 * @return the child node of the stated index
	 * @throws QTreeException if the index is out of bounds or the children array is null
	 */
	
	public QTreeNode getChild(int index) throws QTreeException {
		if (index < 0 || index > 3) {
			throw new QTreeException("QTreeException");
		} else if (children == null) {
			throw new QTreeException("children array is full");
		} else {
			return children[index];
		}
	}

	/**
	 * Sets the x-coordinate of the treenode to a value.
	 *
	 * @param newx the new x-coordinate
	 */
	
	public void setx(int newx) {
		x = newx;
	}

	/**
	 * Sets the y-coordinate of the treenode to a value.
	 *
	 * @param newy the new y-coordinate
	 */
	
	public void sety(int newy) {
		y = newy;
	}

	/**
	 * Sets the size of a node to a specific value.
	 *
	 * @param newSize is the new size
	 */
	
	public void setSize(int newSize) {
		size = newSize;
	}
	
	/**
	 * Sets the color of the treenode to the specified value.
	 *
	 * @param newColor the new color
	 */

	public void setColor(int newColor) {
		color = newColor;
	}

	/**
	 * Sets the parent node of the current node to the specified parent node.
	 *
	 * @param newParent the new parent node
	 */
	
	public void setParent(QTreeNode newParent) {
		parent = newParent;
	}

	/**
	 * Sets the child node at the given index to a specific child node.
	 *
	 * @param newChild the new child node
	 * @param index    the index at which to set the child node
	 * @throws QTreeException if the index is out of bounds
	 */
	
	public void setChild(QTreeNode newChild, int index) {
		if (index < 0 || index > 3) {
			throw new QTreeException("QTreeNode Exception ");
		}
		children[index] = newChild;
	}

	/**
     * Verifies if a node is a leaf node has no children(has no children)  
     *
     * @return true if the node is a leaf, false otherwise
     */
	public boolean isLeaf() {
		if (children == null) {
			return true;
		}
		for (QTreeNode children : children) {
			if (children == null) {
				return true;
			}
		}
		return false;
	}
}
	
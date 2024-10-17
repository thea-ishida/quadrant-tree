
public class QuadrantTree {

	private QTreeNode root;

	/**
	 * Creates a QuadrantTree object with the given pixels.
	 *
	 * @param thePixels the 2D array of pixels
	 */

	public QuadrantTree(int[][] thePixels) {
		this.root = makeQuadrantTree(thePixels, 0, 0, thePixels.length);
	}

	/**
	 * Recursively builds a quadrant tree with the given pixels.
	 *
	 * @param pixels the 2D array of pixels
	 * @param x      x-coordinate of the quadrant's top-left corner
	 * @param y      y-coordinate of the quadrant's top-left corner
	 * @param size   the size of the quadrant
	 * @return the root node of the built quadrant tree
	 */

	private QTreeNode makeQuadrantTree(int[][] pixels, int x, int y, int size) {
		if (size == 1) {
			int averageColor = pixels[y][x];
			return new QTreeNode(null, x, y, size, averageColor);
			// base case if size == 1

		} else { // if the size of Q is greater than 1
			QTreeNode R1 = makeQuadrantTree(pixels, x, y, size / 2);
			QTreeNode R2 = makeQuadrantTree(pixels, x + size / 2, y, size / 2);
			QTreeNode R3 = makeQuadrantTree(pixels, x, y + size / 2, size / 2);
			QTreeNode R4 = makeQuadrantTree(pixels, x + size / 2, y + size / 2, size / 2);

			int averageColor = calculateColor(pixels, x, y, size);

			QTreeNode currNode = new QTreeNode(new QTreeNode[] { R1, R2, R3, R4 }, x, y, size, averageColor);

			try {
				R1.setParent(currNode);
				R2.setParent(currNode);
				R3.setParent(currNode);
				R4.setParent(currNode);
			} catch (QTreeException e) {
				System.out.println("QTreeException");
			}

			return currNode;
		}
	}

	/**
	 * Returns the root node of the quadrant tree.
	 *
	 * @return the root node
	 */

	public QTreeNode getRoot() {
		return root;
	}

	/**
	 * Performs the calculation to get the average color of a quadrant.
	 *
	 * @param pixels the 2D array of pixels
	 * @param x      the x-coordinate of the quadrant's top-left corner
	 * @param y      the y-coordinate of the quadrant's top-left corner
	 * @param size   the size of the quadrant
	 * @return the average color of the quadrant
	 */

	private int calculateColor(int[][] pixels, int x, int y, int size) {
		if (size != 1) {
			int averageColor = Gui.averageColor(pixels, x, y, size);
			return averageColor;
		}
		return pixels[x][y];
	}

	/**
	 * Retrieves pixels from the quadrant tree at a specified level.
	 *
	 * @param r        the root node of the quadrant tree
	 * @param theLevel the level at which to retrieve pixels
	 * @return a list of nodes containing pixels at the specified level
	 */

	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
		if (r == null) {
			return null;
		}
		if (r.isLeaf() || theLevel == 0) {
			ListNode<QTreeNode> QTreeNodeList = new ListNode<QTreeNode>(r);
			return QTreeNodeList;
		} else {
			ListNode<QTreeNode> totalNodes = null;
			for (int i = 0; i < 4; i++) {
				ListNode<QTreeNode> totalChildNodes = getPixels(r.getChild(i), theLevel - 1);
				totalNodes = addLists(totalNodes, totalChildNodes);
			}

			return totalNodes;
		}
	}

	/**
	 * Concatenates two lists of tree nodes.
	 *
	 * @param firstList  is the first list of tree nodes
	 * @param secondList is the second list of tree nodes
	 * @return the merged list of tree nodes
	 */
	
	private ListNode<QTreeNode> addLists(ListNode<QTreeNode> firstList, ListNode<QTreeNode> secondList) {
		if (firstList == null) {
			return secondList;
		}

		ListNode<QTreeNode> newList = firstList;
		while (newList.getNext() != null) {
			newList = newList.getNext();
		}
		newList.setNext(secondList);

		return firstList;

	}

	/**
	 * Finds nodes in the quadrant tree with the same color and level.
	 *
	 * @param r        the root node of the quadrant tree
	 * @param theColor the color to match
	 * @param theLevel the level at which to search
	 * @return a Duple object containing the list of matching nodes and their count
	 */

	public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
		if (r.isLeaf() || theLevel == 0) {
			if (Gui.similarColor(r.getColor(), theColor)) {
				ListNode<QTreeNode> treeNodes = new ListNode<>(r);

				return new Duple(treeNodes, 1);

			} else {
				return new Duple(null, 0);
			}
		} else {
			int recursiveCount = 0;
			ListNode<QTreeNode> totNodes = null;

			for (int i = 0; i < 4; i++) {
				Duple child = findMatching(r.getChild(i), theColor, theLevel - 1);
				ListNode<QTreeNode> childrenNode = child.getFront();
				int childrenCount = child.getCount();
				recursiveCount += childrenCount;

				totNodes = addLists(totNodes, childrenNode);
			}
			return new Duple(totNodes, recursiveCount);
		}

	}

	/**
	 * Finds the node in the quadrant tree at the given level and coordinate.
	 *
	 * @param r        root node of the quadrant tree
	 * @param theLevel level at which to search
	 * @param x        x-coordinate of the node
	 * @param y        y-coordinate of the node
	 * @return the node found at the stated level and coordinate, or null if not
	 *         found
	 */
	
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
		// basecase if r is null or if theLevel
		if (r == null) {
			return null;
		}

		if (r.isLeaf() || theLevel == 0) {
			if (x >= r.getx() && x < r.getx() + r.getSize() && y >= r.gety() && y < r.gety() + r.getSize()) {
				return r;
			}
			return null;
		} else {
			QTreeNode qNode = null;
			for (int i = 0; i < 4; i++) {
				qNode = findNode(r.getChild(i), theLevel - 1, x, y);
				if (qNode != null)
					return qNode;
			}
			return qNode;
		}
	}
}


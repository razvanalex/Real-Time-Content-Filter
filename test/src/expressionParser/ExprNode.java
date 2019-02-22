package expressionParser;

import java.lang.UnsupportedOperationException;

/**
 * ExprNode class represents a generic node in an expression tree. It also be
 * considered the root of a tree or a subtree. Each node from a tree must have
 * maximum two children: one for left and one for right. Also, each node
 * contains the height of the subtree determined by node as root. This height is
 * used to increase performance while evaluating the tree. For evaluation, it is
 * used a visitor pattern, so to get the result, <code>accept(IVisitor)</code>
 * method should be invoked and use the returned value (in logical expression
 * tree the returned result is a boolean value - true or false).
 * 
 * @author razvan
 *
 */
public class ExprNode implements IVisitable {

	/**
	 * Reference to left child node of current node.
	 */
	private ExprNode left;

	/**
	 * Reference to right child node of current node.
	 */
	private ExprNode right;

	/**
	 * The height of the subtree determined the by current node considered as
	 * root for subtree. It is used to increase performance while evaluating the
	 * expression tree. The root is considered at level 0.
	 */
	private int height = 0;

	/**
	 * Create a new generic node for the expression tree, without setting left
	 * and right children.
	 */
	public ExprNode() {
	}

	/**
	 * Create a new generic node and set its left and right children.
	 * 
	 * @param left
	 *            reference to left child node.
	 * @param right
	 *            reference to right child node.
	 */
	public ExprNode(ExprNode left, ExprNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Getter for left child node.
	 * 
	 * @return reference to left child node
	 * 
	 * @see expressionParser.ExprNode#left
	 */
	public ExprNode getLeft() {
		return left;
	}

	/**
	 * Setter for left child node.
	 * 
	 * @param left
	 *            reference to a new left child node
	 * 
	 * @see expressionParser.ExprNode#left
	 */
	public void setLeft(ExprNode left) {
		this.left = left;
	}

	/**
	 * Getter for right child node.
	 * 
	 * @return reference to right child node
	 * 
	 * @see expressionParser.ExprNode#right
	 */
	public ExprNode getRight() {
		return right;
	}

	/**
	 * Setter for right child node.
	 * 
	 * @param right
	 *            reference to a new right child node
	 * 
	 * @see expressionParser.ExprNode#right
	 */
	public void setRight(ExprNode right) {
		this.right = right;
	}

	/**
	 * Getter for height.
	 * 
	 * @return the height of the subtree determined by current node.
	 * 
	 * @see expressionParser.ExprNode#height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setter for height. It should be used to set height while creating the
	 * expression tree.
	 * 
	 * @param height
	 *            set the new height
	 * 
	 * @see expressionParser.ExprNode#right
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.IVisitable#accept(expressionParser.IVisitor)
	 */
	@Override
	public boolean accept(IVisitor visitor) {
		throw new UnsupportedOperationException();
	}

}

package expressionParser;

/**
 * AndNode class represents a type of node (logical AND) in the expression tree.
 * Its children nodes can be another operand node (such as <code>OrNode</code>)
 * or any class that extends <code>ExprNode</code> class. The value is evaluated
 * using visitor pattern, by using <code>accept()</code> method. 
 * 
 * @see expressionParser.ExprNode#accept(expressionParser.IVisitor)
 * 
 * @author razvan
 */
public class AndNode extends ExprNode {

	/**
	 * Create a new AND node without setting its child nodes (left and right).
	 */
	public AndNode() {
	}

	/**
	 * Create a new AND node and set its left and right children.
	 * 
	 * @param left
	 *            reference to left child node.
	 * @param right
	 *            reference to right child node.
	 */
	public AndNode(ExprNode left, ExprNode right) {
		super(left, right);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.ExprNode#accept(expressionParser.IVisitor)
	 */
	@Override
	public boolean accept(IVisitor visitor) {
		return visitor.visit(this);
	}
}

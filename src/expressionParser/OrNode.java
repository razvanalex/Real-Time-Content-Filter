package expressionParser;

/**
 * OrNode class represents a type of node (logical OR) in the expression tree.
 * Its children nodes can be another operand node (such as <code>AndNode</code>)
 * or any class that extends <code>ExprNode</code> class. The value is evaluated
 * using visitor pattern, by using <code>accept()</code> method.
 * 
 * @see expressionParser.ExprNode#accept(expressionParser.IVisitor)
 * 
 * @author razvan
 */
public class OrNode extends ExprNode {

	/**
	 * Create a new OR node without setting its child nodes (left and right).
	 */
	public OrNode() {
	}
	
	/**
	 * Create a new OR node and set its left and right children.
	 * 
	 * @param left
	 *            reference to left child node.
	 * @param right
	 *            reference to right child node.
	 */
	public OrNode(ExprNode left, ExprNode right) {
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

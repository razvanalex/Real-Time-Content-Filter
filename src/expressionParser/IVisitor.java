package expressionParser;

/**
 * Visitor interface used to cover the expression tree (visit each node) and
 * evaluate the tree.
 * 
 * @author razvan
 *
 */
public interface IVisitor {
	/**
	 * Visit an AND node and evaluate it
	 * 
	 * @param operatorNode
	 *            an AND node
	 * @return the value of AND between left and right children
	 */
	public boolean visit(AndNode operatorNode);

	/**
	 * Visit an OR node and evaluate it
	 * 
	 * @param operatorNode
	 *            an OR node
	 * @return the value of OR between left and right children
	 */
	public boolean visit(OrNode operatorNode);

	/**
	 * Visit a leaf from the tree which is an operand.
	 * 
	 * @param operandNode
	 *            an operand
	 * @return the value of evaluation - true of false
	 */
	public boolean visit(OperandNode operandNode);
}
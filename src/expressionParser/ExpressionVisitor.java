package expressionParser;

/**
 * ExpressionVisitor class has the implementation of the IVisitor interface,
 * being used in visitor pattern. Each <code>visit()</code> method has
 * implementation for each type of node:
 * 
 * <ul>
 * <li>AndNode - for nodes that represent AND operation. The result is AND
 * between left and right node</li>
 * <li>OrNode - for nodes that represent OR operation. The result is OR between
 * left and right node</li>
 * <li>OperandNode - for nodes that represent operands of an operation such as
 * AND, OR. This operands are used in evaluation of the expression.</li>
 * </ul>
 * 
 * For increasing the performance, <code>visit()</code> methods for AndNode and
 * OrNode uses short-circuit evaluation, being evaluated the shortest branch
 * from the tree firstly, and then for:
 * 
 * <ul>
 * <li>AndNode - if the result is 0, then <code>visit()</code> method will return
 * 0.</li>
 * <li>OrNode - if the result is 1, then <code>visit()</code> method will return
 * 1.</li>
 * </ul>
 * 
 * @see expressionParser.IVisitor#visit(expressionParser.AndNode)
 * @see expressionParser.IVisitor#visit(expressionParser.OrNode)
 * @see expressionParser.IVisitor#visit(expressionParser.OperandNode)
 * 
 * @author razvan
 *
 */
public class ExpressionVisitor implements IVisitor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.IVisitor#visit(expressionParser.AndNode)
	 */
	@Override
	public boolean visit(AndNode operatorNode) {
		ExprNode rightNode = operatorNode.getRight();
		ExprNode leftNode = operatorNode.getLeft();

		if (leftNode.getHeight() < rightNode.getHeight()) {
			return leftNode.accept(this) && rightNode.accept(this);
		}

		return rightNode.accept(this) && leftNode.accept(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.IVisitor#visit(expressionParser.OrNode)
	 */
	@Override
	public boolean visit(OrNode operatorNode) {
		ExprNode rightNode = operatorNode.getRight();
		ExprNode leftNode = operatorNode.getLeft();

		if (leftNode.getHeight() < rightNode.getHeight()) {
			return leftNode.accept(this) || rightNode.accept(this);
		}

		return rightNode.accept(this) || leftNode.accept(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.IVisitor#visit(expressionParser.OperandNode)
	 */
	@Override
	public boolean visit(OperandNode operandNode) {
		return operandNode.getResult();
	}
}

package expressionParser;

import operations.OperationFactory;

/**
 * ExprNodeFactory class can be used to create nodes for expression tree much
 * more easier. This class uses a singleton pattern, so a new instance of
 * <code>ExprNodeFactory</code> class it is created at the first call of
 * <code>getInstance()</code> method. To create nodes, use
 * <code>getExprNode(String)</code> method. In the case of creating a new
 * operator (using "OPERATOR" as argument), <code>setOpExpression(String)</code>
 * should be called to set the operator expression (e.g. "eq value 2") before.
 * Otherwise, <code>null</code> or a new instance of the last value is returned.
 * 
 * @author razvan
 *
 */
public class ExprNodeFactory {

	/**
	 * The instance of ExprNodeFactory. Singleton pattern is used.
	 */
	public static ExprNodeFactory instance = null;

	/**
	 * Expression of operation. This string should respect the next format:
	 * 
	 * <ul>
	 * <li>first 2 letters has to be: lg, le, eq, ne, gt, ge; followed by
	 * space</li>
	 * <li>next word has to be "value" or "name"</li>
	 * <li>next word has to be a number for "value" and a string without any
	 * space character for "name"</li>
	 * </ul>
	 * 
	 */
	private String opExpression = null;

	/**
	 * Create a new instance of ExprNodeFactory class. Only once is invoked.
	 */
	private ExprNodeFactory() {
	}

	/**
	 * Create a new instance of ExprNodeFactory if there does not exist,
	 * otherwise return the instance created before.
	 * 
	 * @return an instance of ExprNodeFactory
	 */
	public static ExprNodeFactory getInstance() {
		if (instance == null) {
			instance = new ExprNodeFactory();
		}

		return instance;
	}

	/**
	 * Create a new expression node from a given string.
	 * 
	 * @param nodeType
	 *            the type of node that should be created. It can be:
	 *            <ul>
	 *            <li>( - create a left parentheses node</li>
	 *            <li>) - create a right parentheses node</li>
	 *            <li>&amp;&amp; - create an AND node</li>
	 *            <li>|| - create an OR node</li>
	 *            <li>OPERATOR - for creating an operator.
	 *            <code>setOpExpression(String)</code> method should be invoked
	 *            before to create a new operator node.</li>
	 *            </ul>
	 * @return a new expression node
	 */
	public ExprNode getExprNode(String nodeType) {
		OperationFactory opFactory = OperationFactory.getInstance();

		if (nodeType == null) {
			return null;
		}

		if (nodeType.equalsIgnoreCase("(")) {
			return new GenericNode("(");

		} else if (nodeType.equalsIgnoreCase(")")) {
			return new GenericNode(")");

		} else if (nodeType.equalsIgnoreCase("&&")) {
			return new AndNode();

		} else if (nodeType.equalsIgnoreCase("||")) {
			return new OrNode();

		} else if (nodeType.equalsIgnoreCase("OPERATOR")) {
			return opFactory.getOperationNode(opExpression);
		}

		return null;
	}

	/**
	 * This method sets the operation string.
	 * 
	 * @param opExpression
	 *            an expression
	 */
	public void setOpExpression(String opExpression) {
		this.opExpression = opExpression;
	}
}

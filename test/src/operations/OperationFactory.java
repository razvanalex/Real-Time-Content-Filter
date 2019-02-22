package operations;

import expressionParser.OperandNode;

/**
 * OperationFactory class is a factory that creates instances of operations such
 * as: equals, not equals, greater than, greater or equals, less than, less or
 * equals. This class uses singleton pattern and factory pattern.
 * 
 * @author razvan
 *
 */
public class OperationFactory {

	/**
	 * An instance of operations factory.
	 */
	public static OperationFactory instance = null;

	/**
	 * Create a new OperationFactor instance.
	 */
	private OperationFactory() {
	}

	/**
	 * Get an instance of OperationFactory by creating one if there does not
	 * exist an instance, or return the existing one.
	 * 
	 * @return the instance of OperationFactory
	 */
	public static OperationFactory getInstance() {
		if (instance == null) {
			instance = new OperationFactory();
		}

		return instance;
	}

	/**
	 * Factory method. This method creates an OperandNode by a given string.
	 * String can be:
	 * 
	 * <ul>
	 * <li>eq - equal node =&gt; new EqOperation instance</li>
	 * <li>ne - not equal node =&gt; new NeOperation instance</li>
	 * <li>ge - greater or equals node =&gt; new GeOperation instance</li>
	 * <li>gt - greater than node =&gt; new GtOperation instance</li>
	 * <li>le - less or equals node =&gt; new LeOperation instance</li>
	 * <li>lt - less than node =&gt; new LtOperation instance</li>
	 * </ul>
	 * 
	 * @param expr
	 *            an expression given as string, as described above.
	 * @return an OperandNode instance or null
	 */
	public OperandNode getOperationNode(String expr) {
		if (expr == null) {
			return null;
		}

		String[] tokens = expr.split(" ");
		if (!checkTokens(tokens)) {
			return null;
		}

		if (tokens[0].equalsIgnoreCase("eq")) {
			if (tokens[1].equals("name")) {
				return new EqOperation(tokens[2]);
			} else if (tokens[1].equals("value")) {
				return new EqOperation(Float.parseFloat(tokens[2]));
			}

		} else if (tokens[0].equalsIgnoreCase("ne")) {
			if (tokens[1].equals("name")) {
				return new NeOperation(tokens[2]);
			} else if (tokens[1].equals("value")) {
				return new NeOperation(Float.parseFloat(tokens[2]));
			}

		} else if (tokens[0].equalsIgnoreCase("ge")) {
			if (tokens[1].equals("value")) {
				return new GeOperation(Float.parseFloat(tokens[2]));
			}

		} else if (tokens[0].equalsIgnoreCase("gt")) {
			if (tokens[1].equals("value")) {
				return new GtOperation(Float.parseFloat(tokens[2]));
			}

		} else if (tokens[0].equalsIgnoreCase("le")) {
			if (tokens[1].equals("value")) {
				return new LeOperation(Float.parseFloat(tokens[2]));
			}

		} else if (tokens[0].equalsIgnoreCase("lt")) {
			if (tokens[1].equals("value")) {
				return new LtOperation(Float.parseFloat(tokens[2]));
			}
		}

		return null;
	}

	/**
	 * Check if tokens meet some conditions:
	 * 
	 * <ul>
	 * <li>operators are "eq ne le lt ge gt"</li>
	 * <li>operand is "name" or "value"</li>
	 * </ul>
	 * 
	 * @param tokens
	 *            an array of tokens
	 * @return if the conditions are met
	 */
	private boolean checkTokens(String[] tokens) {
		String operators = "eq ne le lt ge gt";

		boolean isValidOperator = operators.contains(tokens[0]);
		boolean isValidOperand = tokens[1].equals("name")
				|| tokens[1].equals("value");

		return isValidOperator && isValidOperand;
	}
}

package expressionParser;

/**
 * GenericNode class is used to create generic nodes with its value. This class
 * is used at parsing the expressions.
 * 
 * @author razvan
 *
 */
public class GenericNode extends ExprNode {

	/**
	 * The value of the node.
	 */
	private String value;

	/**
	 * Create a new instance of GenericNode and set its value.
	 * 
	 * @param value
	 *            the value of the generic node
	 */
	public GenericNode(String value) {
		this.value = value;
	}

	/**
	 * Getter of value
	 * 
	 * @return the value of node
	 * 
	 * @see GenericNode#value
	 */
	public String getValue() {
		return value;
	}

}

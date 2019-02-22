package expressionParser;

/**
 * OperandNode class represents a leaf node in the expression tree. This node is
 * a simple expression such as "le name NAME" or "ge value 5.0". It is used to
 * check if a pair (name, value) satisfies the boolean expression. After all
 * leafs of AndNode or OrNode are evaluated, AND and OR operations are executed.
 * Evaluation is done using a visitor.
 * 
 * @author razvan
 *
 */
public abstract class OperandNode extends ExprNode {

	/**
	 * The value that should be checked for all leafs of type 'value'.
	 */
	private static Float value = null;

	/**
	 * The name that should be checked for all leafs of type 'name'.
	 */
	private static String name = null;

	/**
	 * The type of the operand node. It can be 'name' or 'value' in this
	 * project.
	 */
	private String type = null;

	/**
	 * Constructor for creating a new OperandNode instance, without setting any
	 * field.
	 */
	public OperandNode() {
	}

	/**
	 * Constructor for creating a new OperandNode instance, setting both
	 * children.
	 * 
	 * @param left
	 *            reference to left node
	 * @param right
	 *            reference to right node
	 */
	public OperandNode(ExprNode left, ExprNode right) {
		super(left, right);
	}

	/**
	 * Getter for value.
	 * 
	 * @return the value
	 * 
	 * @see OperandNode#value
	 */
	public static Float getValue() {
		return value;
	}

	/**
	 * Setter for value.
	 * 
	 * @param value
	 *            the new value that should be evaluated
	 * 
	 * @see OperandNode#value
	 */
	public static void setValue(Float value) {
		OperandNode.value = value;
	}

	/**
	 * Getter for name.
	 * 
	 * @return the name
	 * 
	 * @see OperandNode#name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 * 
	 * @param name
	 *            the new name that should be evaluated
	 * 
	 * @see OperandNode#name
	 */
	public static void setName(String name) {
		OperandNode.name = name;
	}

	/**
	 * Getter for type
	 * 
	 * @return the type
	 * 
	 * @see OperandNode#type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for type
	 * 
	 * @param type
	 *            can be 'name' of 'value'
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * Evaluate operation that implies 'value' or 'name'.
	 * 
	 * @return the result of the evaluation: true or false
	 */
	public abstract boolean getResult();

}

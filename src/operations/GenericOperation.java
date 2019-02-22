package operations;

import expressionParser.ExprNode;
import expressionParser.OperandNode;

/**
 * GenericOperation class is an abstract class that contains the reference value
 * or the reference name of the OperationNode, depending on the type of node
 * (eq, ne node for name or le, lt, ge, gt, eq, ne node for value).
 * 
 * @author razvan
 *
 */
public abstract class GenericOperation extends OperandNode {

	/**
	 * The reference value that is particularly at each node
	 */
	private Float refValue = null;

	/**
	 * The reference name that is particularly at each node
	 */
	private String refName = null;

	/**
	 * Create a new GenericOperation and set refValue
	 * 
	 * @param refValue
	 *            new reference value
	 * 
	 * @see operations.GenericOperation#refValue
	 */
	public GenericOperation(Float refValue) {
		this.setType("value");
		this.refValue = refValue;
	}

	/**
	 * Create a new GenericOperation and set refName
	 * 
	 * @param refName
	 *            new reference name
	 * 
	 * @see operations.GenericOperation#refName
	 */
	public GenericOperation(String refName) {
		this.setType("name");
		this.refName = refName;
	}

	/**
	 * Create new GenericOperation and set left and right children
	 * 
	 * @param left
	 *            reference to left child
	 * @param right
	 *            reference to right child
	 */
	public GenericOperation(ExprNode left, ExprNode right) {
		super(left, right);
	}

	/**
	 * Create new GenericOperation and set left and right children and refValue.
	 * The node will be considered a value node.
	 * 
	 * @param left
	 *            reference to left child
	 * @param right
	 *            reference to right child
	 * @param refValue
	 *            new reference value
	 */
	public GenericOperation(ExprNode left, ExprNode right, Float refValue) {
		super(left, right);
		this.refValue = refValue;
		this.setType("value");
	}

	/**
	 * Create new GenericOperation and set left and right children and refName.
	 * The node will be considered a name node.
	 * 
	 * @param left
	 *            reference to left child
	 * @param right
	 *            reference to right child
	 * @param refName
	 *            new reference name
	 */
	public GenericOperation(ExprNode left, ExprNode right, String refName) {
		super(left, right);
		this.refName = refName;
		this.setType("name");
	}

	/**
	 * Getter for refValue.
	 * 
	 * @return the reference value
	 * 
	 * @see operations.GenericOperation#refValue
	 */
	public Float getRefValue() {
		return refValue;
	}

	/**
	 * Getter for refName.
	 * 
	 * @return the reference name
	 * 
	 * @see operations.GenericOperation#refName
	 */
	public String getRefName() {
		return refName;
	}

}

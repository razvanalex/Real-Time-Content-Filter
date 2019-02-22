package operations;

/**
 * NeOperation class is a type of node that represents not equality of name or
 * value.
 * 
 * @author razvan
 *
 */
public class NeOperation extends GenericOperation {

	/**
	 * Create an operation of type not equals, and set refValue; so the node
	 * will be a value node.
	 * 
	 * @param refValue
	 *            the reference value
	 */
	public NeOperation(Float refValue) {
		super(refValue);
	}

	/**
	 * Create an operation of type not equals, and set refName; so the node will
	 * be a name node.
	 * 
	 * @param refName
	 *            the reference name
	 */
	public NeOperation(String refName) {
		super(refName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see expressionParser.OperandNode#getResult()
	 */
	@Override
	public boolean getResult() {
		if (getValue() == null || getName() == null)
			throw new NullPointerException();

		if (getRefValue() != null && getType().equals("value")) {
			return !getRefValue().equals(getValue());
		} else if (getRefName() != null && getType().equals("name")) {
			return !getRefName().equals(getName());
		}

		return false;
	}

}

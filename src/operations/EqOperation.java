package operations;

/**
 * EqOperation class is a type of node that represents equality of name or
 * value.
 * 
 * @author razvan
 *
 */
public class EqOperation extends GenericOperation {

	/**
	 * Create an operation of type equals, and set refValue; so the node will be
	 * a value node.
	 * 
	 * @param refValue
	 *            the reference value
	 */
	public EqOperation(Float refValue) {
		super(refValue);
	}

	/**
	 * Create an operation of type equals, and set refName; so the node will be
	 * a name node.
	 * 
	 * @param refName
	 *            the reference name
	 */
	public EqOperation(String refName) {
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
			return getRefValue().equals(getValue());
		} else if (getRefName() != null && getType().equals("name")) {
			return getRefName().equals(getName());
		}

		return false;
	}

}

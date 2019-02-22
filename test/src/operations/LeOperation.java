package operations;

/**
 * LeOperation class is a type of node that represents less of equals to value.
 * 
 * @author razvan
 *
 */
public class LeOperation extends GenericOperation {

	/**
	 * Create an operation of type less of equals, and set refValue; so the node
	 * will be a value node.
	 * 
	 * @param refValue
	 *            the reference value
	 */
	public LeOperation(Float refValue) {
		super(refValue);
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
			return getRefValue().compareTo(getValue()) >= 0;
		}

		return false;
	}
}

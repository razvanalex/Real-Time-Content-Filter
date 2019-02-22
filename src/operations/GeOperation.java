package operations;

/**
 * GeOperation class is a type of node that represents greater or equals to
 * value.
 * 
 * @author razvan
 *
 */
public class GeOperation extends GenericOperation {

	/**
	 * Create an operation of type greater or equals, and set refValue; so the
	 * node will be a value node.
	 * 
	 * @param refValue
	 *            the reference value
	 */
	public GeOperation(Float refValue) {
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
			return getRefValue().compareTo(getValue()) <= 0;
		}

		return false;
	}
}

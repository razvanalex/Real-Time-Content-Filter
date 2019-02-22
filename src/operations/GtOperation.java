package operations;

/**
 * GtOperation class is a type of node that represents greater than value.
 * 
 * @author razvan
 *
 */
public class GtOperation extends GenericOperation {

	/**
	 * Create an operation of type greater than, and set refValue; so the node
	 * will be a value node.
	 * 
	 * @param refValue
	 *            the reference value
	 */
	public GtOperation(Float refValue) {
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
			return getRefValue().compareTo(getValue()) < 0;
		}

		return false;
	}
}

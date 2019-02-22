package expressionParser;

/**
 * IVisitable interface is used to mark a class as visitable. Each visitable
 * class should have an <code>accept()</code> method.
 * 
 * @author razvan
 *
 */
public interface IVisitable {

	/**
	 * <code>accept()</code> method from a visitor pattern. It is used to visit
	 * each member.
	 * 
	 * @param visitor
	 *            the visitor
	 * @return the result of evaluation
	 */
	boolean accept(IVisitor visitor);
}

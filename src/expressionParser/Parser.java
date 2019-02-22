package expressionParser;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Parser class can be used to parse expressions and transform the
 * representation form string to an expression graph. This parser works only for
 * boolean expressions. The algorithm implies 3 main steps:
 * 
 * <ol>
 * <li>Create a queue from a string. This queue contains all elements in the
 * same order as in the string.</li>
 * <li>Create a queue, by applying Shunting-Yard algorithm over the queue
 * resulted at the previous step.</li>
 * <li>Create the expression tree form the last queue.</li>
 * </ol>
 * 
 * Each step can be reproduced alone, if needed.
 * 
 * @author razvan
 *
 */
public class Parser {

	/**
	 * Instance of Parser. Uses singleton pattern.
	 */
	private static Parser instance = null;

	/**
	 * Create a new Parser instance.
	 */
	private Parser() {
	}

	/**
	 * Get an instance of Parser class. If there does not exist one, then it is
	 * created and returned, otherwise return the existing instance.
	 * 
	 * @return an instance of Parser class
	 */
	public static Parser getInstance() {
		if (instance == null) {
			instance = new Parser();
		}

		return instance;
	}

	/**
	 * Create an expression tree directly from a string. It uses all the 3
	 * steps:
	 * 
	 * <ol>
	 * <li>Create a queue from a string. This queue contains all elements in the
	 * same order as in the string.</li>
	 * <li>Create a queue, by applying Shunting-Yard algorithm over the queue
	 * resulted at the previous step.</li>
	 * <li>Create the expression tree form the last queue.</li>
	 * </ol>
	 * 
	 * If the expression is "nil" then null is returned. If the parsing fails,
	 * then it is displayed "Error while parsing..." message.
	 * 
	 * @param expression
	 *            given as string
	 * @return the expression tree, more precisely the root node.
	 */
	public ExprNode createExprGraph(String expression) {
		ExprNode expr = null;

		if (expression.equals("nil")) {
			return expr;
		}

		try {
			Queue<ExprNode> q = createQueue(expression);
			Queue<ExprNode> q2 = createShuntingYardQueue(q);
			expr = createGraphUsingQueue(q2);

		} catch (Exception e) {
			System.out.println("Error while parsing...");
		}

		return expr;
	}

	/**
	 * Create a queue from a given string. It parses the expression and for each
	 * word, an expression node is created and put into a queue.
	 * 
	 * @param str
	 *            which is an expression
	 * @return the resulted queue
	 */
	public Queue<ExprNode> createQueue(String str) {
		Queue<ExprNode> queue = new LinkedList<ExprNode>();

		ExprNodeFactory eNodeFactory = ExprNodeFactory.getInstance();

		String nameRegex = "[a-zA-Z]{2} +name +[^)(|& ]+.*";
		String valueRegex = "[a-zA-Z]{2} +value +[^)(|& ]+.*";

		for (int i = 0; i < str.length(); i++) {
			String substr = str.substring(i);
			int lengthDifference = str.length() - substr.length();
			int index = i - lengthDifference;

			ExprNode node = null;

			if (substr.indexOf(" ") == index) {
				continue;

			} else if (substr.indexOf("(") == index) {
				node = eNodeFactory.getExprNode("(");

			} else if (substr.indexOf(")") == index) {
				node = eNodeFactory.getExprNode(")");

			} else if (substr.indexOf("&&") == index) {
				node = eNodeFactory.getExprNode("&&");

			} else if (substr.indexOf("||") == index) {
				node = eNodeFactory.getExprNode("||");

			} else if (substr.matches(nameRegex)
					|| substr.matches(valueRegex)) {
				String operator = getWords(substr, 3, ')');

				eNodeFactory.setOpExpression(operator);
				node = eNodeFactory.getExprNode("OPERATOR");
				eNodeFactory.setOpExpression(null);

			}

			if (node != null) {
				queue.add(node);
			}
		}

		return queue;
	}

	/**
	 * This method is an implementation of Shunting-Yard algorithm to create a
	 * queue where no parentheses is needed, from an infix notation to a postfix
	 * notation (such as reversed polish notation).
	 * 
	 * @param inQueue
	 *            is a queue of expression nodes in infix notation
	 * @return a queue where no parentheses is needed in postfix notation
	 * 
	 * @see <a href=
	 *      "https://en.wikipedia.org/wiki/Shunting-yard_algorithm#The_algorithm_in_detail">
	 *      Shunting-Yard algorithm on Wikipedia</a>
	 */
	public Queue<ExprNode> createShuntingYardQueue(Queue<ExprNode> inQueue) {
		Stack<ExprNode> stack = new Stack<ExprNode>();
		Queue<ExprNode> outQueue = new LinkedList<ExprNode>();

		ExprNode crtNode = null;

		while (!inQueue.isEmpty()) {
			crtNode = inQueue.remove();

			if (crtNode instanceof OperandNode) {
				outQueue.add(crtNode);

			} else if (crtNode instanceof AndNode
					|| crtNode instanceof OrNode) {
				ExprNode topStack = !stack.empty() ? stack.peek() : null;

				boolean topStackIsGeneric = topStack instanceof GenericNode;
				boolean topStackIsAnd = topStack instanceof AndNode;
				boolean topStackIsOr = topStack instanceof OrNode;
				boolean crtIsAnd = crtNode instanceof AndNode;
				boolean crtIsOr = crtNode instanceof OrNode;

				boolean greaterPrecedence = topStackIsAnd && crtIsOr;
				boolean equalPrecedence = (topStackIsOr && crtIsOr)
						|| (topStackIsAnd && crtIsAnd);
				boolean isLeftBracket = topStackIsGeneric
						&& (((GenericNode) topStack).getValue() == "(");

				boolean condition = (greaterPrecedence)
						|| (equalPrecedence) && (!isLeftBracket);

				while (condition && !stack.isEmpty()) {
					outQueue.add(stack.pop());
				}

				stack.push(crtNode);

			} else if (crtNode instanceof GenericNode) {
				if (((GenericNode) crtNode).getValue() == "(") {
					stack.push(crtNode);

				} else if (((GenericNode) crtNode).getValue() == ")") {
					ExprNode topStack = !stack.empty() ? stack.peek() : null;

					boolean isGenericNode = topStack instanceof GenericNode;
					boolean isLeftBracket = isGenericNode
							&& (((GenericNode) topStack).getValue() == "(");

					while ((!isGenericNode && !isLeftBracket)
							&& (!stack.empty())) {
						outQueue.add(stack.pop());

						topStack = !stack.empty() ? stack.peek() : null;

						isGenericNode = topStack instanceof GenericNode;
						isLeftBracket = isGenericNode
								&& (((GenericNode) topStack).getValue() == "(");
					}

					if (!stack.empty()) {
						stack.pop();
					}
				}
			}
		}

		if (inQueue.isEmpty()) {
			while (!stack.empty()) {
				outQueue.add(stack.pop());
			}
		}

		return outQueue;
	}

	/**
	 * This method creates an expression tree from a queue of expressions, in
	 * infix notation. It takes each element from queue and put each operand in
	 * a stack. If, in this time, an AND node or an OR node is found, then it
	 * its created a subtree with last two operands from the stack. The last
	 * subtree (which is the final tree) is returned.
	 * 
	 * @param queue
	 *            in infix notation
	 * @return an expression tree
	 */
	public ExprNode createGraphUsingQueue(Queue<ExprNode> queue) {
		Stack<ExprNode> stack = new Stack<ExprNode>();

		while (!queue.isEmpty()) {
			ExprNode node = queue.remove();

			if (node instanceof OperandNode) {
				stack.push(node);

			} else if (node instanceof AndNode || node instanceof OrNode) {
				ExprNode leftNode = stack.pop();
				ExprNode rightNode = stack.pop();
				node.setHeight(1 + Math.max(leftNode.getHeight(),
						rightNode.getHeight()));

				node.setLeft(leftNode);
				node.setRight(rightNode);
				stack.push(node);
			}
		}

		return !stack.empty() ? stack.pop() : null;
	}

	/**
	 * This method takes first n words from a string up to finalChar. For
	 * example, the next code: <br>
	 * <br>
	 * 
	 * <code>String string = "((le value 2.3) &amp;&amp; (ge value 1))";</code>
	 * <br>
	 * <code>getWords(string, 3, ')')</code> <br>
	 * <br>
	 * 
	 * will have as output the following string: "le value 2.3"
	 * 
	 * @param str
	 *            the initial string
	 * @param n
	 *            number of words
	 * @param finalChar
	 *            the last char (is not taken)
	 * @return the string containing maximum n words
	 */
	private String getWords(String str, int n, char finalChar) {
		int finalCharPos = str.indexOf(finalChar);
		int endIndex = finalCharPos > 0 ? finalCharPos : str.length();
		String substring = str.substring(0, endIndex);

		String[] substrTokens = substring.split(" ");
		String result = "";

		int num = 0;

		for (String s : substrTokens) {
			if (s.length() == 0) {
				continue;

			} else if (num < n) {
				result += s + " ";
				num++;
			}
		}

		return result.substring(0, result.length() - 1);
	}
}

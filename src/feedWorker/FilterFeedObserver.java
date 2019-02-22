package feedWorker;

import java.util.Map;
import java.util.TreeMap;

import expressionParser.ExprNode;
import expressionParser.ExpressionVisitor;
import expressionParser.OperandNode;
import expressionParser.Parser;

/**
 * This class represents an implementation of an observer for feeds. This type
 * of observers filters the feed according to a filter stored as an expression
 * tree. Also, this type of observers stores information needed to be printed.
 * 
 * @author razvan
 *
 */
public class FilterFeedObserver extends FeedObserver {

	/**
	 * A map of stocks from feed. This map contains the name of each stock and
	 * information about the stock.
	 */
	private TreeMap<String, StockData> stock = new TreeMap<String, StockData>();

	/**
	 * An expression filter that may differ from observer to observer. It is
	 * stored as a tree and it is used to filter what feeds should be printed.
	 */
	private ExprNode expressionFilter;

	/**
	 * A visitor to cover the expression tree.
	 */
	private ExpressionVisitor v = new ExpressionVisitor();

	/**
	 * Create a new observer.
	 * 
	 * @param subject
	 *            the feed subject which is observed
	 * @param ID
	 *            the id of the observer
	 * @param filter
	 *            the filter expression string
	 */
	public FilterFeedObserver(Feed subject, int ID, String filter) {
		Parser p = Parser.getInstance();
		expressionFilter = p.createExprGraph(filter);

		this.ID = ID;
		this.subject = subject;
		setStock();
	}

	/**
	 * Create stock information for observer.
	 */
	private void setStock() {
		TreeMap<String, Float> crtStock = this.subject.getStock();

		for (Map.Entry<String, Float> entry : crtStock.entrySet()) {
			String name = entry.getKey();
			Float value = entry.getValue();

			StockData stockData = new StockData(name, value);
			stockData.setNumChanges(0);
			stock.put(name, stockData);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see feedWorker.FeedObserver#printFeed()
	 */
	@Override
	public void printFeed() {
		for (Map.Entry<String, StockData> entry : stock.entrySet()) {
			String name = entry.getKey();
			StockData data = entry.getValue();

			OperandNode.setName(name);
			OperandNode.setValue(data.getLastValue());

			if (expressionFilter != null && expressionFilter.accept(v)) {
				float fluctuation = 0;

				if (data.getLastPrintedValue() != null) {
					float lastPrintedVal = data.getLastPrintedValue();
					fluctuation = (data.getLastValue() - lastPrintedVal)
							/ lastPrintedVal;
					fluctuation *= 100;
				}

				data.setLastPrintedValue(data.getLastValue());
				data.setFluctuation(fluctuation);
				System.out.printf("obs %d: %s %.2f %.2f%% %d\n", this.ID, name,
						data.getLastValue(), data.getFluctuation(),
						data.numChanges);
				data.setNumChanges(0);

			} else if (expressionFilter == null) {
				float fluctuation = 0;

				if (data.getLastPrintedValue() != null) {
					float lastPrintedVal = data.getLastPrintedValue();
					fluctuation = (data.getLastValue() - lastPrintedVal)
							/ lastPrintedVal;
					fluctuation *= 100;
				}

				data.setLastPrintedValue(data.getLastValue());
				data.setFluctuation(fluctuation);

				if (data.getLastPrintedValue() != null) {
					float lastPrintedVal = data.getLastPrintedValue();
					fluctuation = (data.getLastValue() - lastPrintedVal)
							/ lastPrintedVal;
					fluctuation *= 100;
				}

				System.out.printf("obs %d: %s %.2f %.2f%% %d\n", this.ID, name,
						data.getLastValue(), data.getFluctuation(),
						data.numChanges);

				data.setNumChanges(0);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see feedWorker.FeedObserver#update()
	 */
	@Override
	public void update() {
		TreeMap<String, Float> crtStock = this.subject.getStock();

		for (Map.Entry<String, Float> entry : crtStock.entrySet()) {
			String name = entry.getKey();
			Float value = entry.getValue();

			StockData crtStockData = stock.get(name);

			if (crtStockData == null) {
				StockData stockData = new StockData(name, value);
				stock.put(name, stockData);
			} else if (crtStockData.getLastValue() != value) {
				crtStockData.setLastValue(value);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see feedWorker.FeedObserver#update(java.lang.String, java.lang.Float)
	 */
	@Override
	public void update(String name, Float value) {
		OperandNode.setName(name);
		OperandNode.setValue(value);

		StockData crtStockData = stock.get(name);

		if (crtStockData == null) {
			StockData stockData = new StockData(name, value);
			stock.put(name, stockData);
		} else if (crtStockData.getLastValue() != value) {
			crtStockData.setLastValue(value);
		}

	}
}

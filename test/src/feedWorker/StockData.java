package feedWorker;

/**
 * StockData class contains all information needed to be printed. It contains
 * name of the stock, last value (can be considered as current value), the last
 * printed value, fluctuation, number of changes between prints.
 * 
 * @author razvan
 *
 */
public class StockData {

	/**
	 * The name of the stock.
	 */
	private String name;

	/**
	 * Last value of the stock
	 */
	private float lastValue;

	/**
	 * Last printed value of the stock
	 */
	private Float lastPrintedValue;

	/**
	 * The fluctuation between printed stock values
	 */
	private float fluctuation;

	/**
	 * The number of changes of the stock
	 */
	public int numChanges = 1;

	/**
	 * Create a new not set stock
	 */
	public StockData() {
	}

	/**
	 * Create a new stock with a name and a value.
	 * 
	 * @param name
	 *            the name of the stock
	 * @param lastValue
	 *            the value of the stock
	 */
	public StockData(String name, float lastValue) {
		super();
		this.name = name;
		this.lastValue = lastValue;
		this.fluctuation = 0;
	}

	/**
	 * Getter for name
	 * 
	 * @return the name
	 * 
	 * @see feedWorker.StockData#name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param name
	 *            new name of the stock
	 * 
	 * @see feedWorker.StockData#name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for lastValue
	 * 
	 * @return the last value
	 * 
	 * @see feedWorker.StockData#lastValue
	 */
	public float getLastValue() {
		return lastValue;
	}

	/**
	 * Setter for lastValue. Also, updates the number of changes.
	 * 
	 * @param lastValue
	 *            new last value
	 * 
	 * @see feedWorker.StockData#lastValue
	 */
	public void setLastValue(float lastValue) {
		this.lastValue = lastValue;
		this.numChanges++;
	}

	/**
	 * Getter for fluctuation.
	 * 
	 * @return the fluctuation
	 * 
	 * @see feedWorker.StockData#fluctuation
	 */
	public float getFluctuation() {
		return fluctuation;
	}

	/**
	 * Setter for fluctuation.
	 * 
	 * @param fluctuation
	 *            new fluctuation
	 * 
	 * @see feedWorker.StockData#fluctuation
	 */
	public void setFluctuation(float fluctuation) {
		this.fluctuation = fluctuation;
	}

	/**
	 * Getter for lastPrintedValue.
	 * 
	 * @return last printed value
	 * 
	 * @see feedWorker.StockData#lastPrintedValue
	 */
	public Float getLastPrintedValue() {
		return lastPrintedValue;
	}

	/**
	 * Setter for lastPrintedValue.
	 * 
	 * @param lastPrintedValue
	 *            new last printed value
	 * 
	 * @see feedWorker.StockData#lastPrintedValue
	 */
	public void setLastPrintedValue(Float lastPrintedValue) {
		this.lastPrintedValue = lastPrintedValue;
	}

	/**
	 * Getter for numChanges
	 * 
	 * @return the number of changes between prints
	 * 
	 * @see feedWorker.StockData#numChanges
	 */
	public int getNumChanges() {
		return numChanges;
	}

	/**
	 * Setter for numChanges
	 * 
	 * @param numChanges
	 *            updated number of changes
	 * 
	 * @see feedWorker.StockData#numChanges
	 */
	public void setNumChanges(int numChanges) {
		this.numChanges = numChanges;
	}

}

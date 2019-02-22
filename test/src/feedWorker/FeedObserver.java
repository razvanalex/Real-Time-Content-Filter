package feedWorker;

/**
 * FeedObserver class is the generic observer that it's implemented by observers
 * for the feed.
 * 
 * @author razvan
 *
 */
public abstract class FeedObserver {

	/**
	 * The ID of the observer
	 */
	protected int ID;

	/**
	 * The subject that is observed
	 */
	protected Feed subject;

	/**
	 * Update the information about all stocks from the observers
	 */
	public abstract void update();

	/**
	 * Update the information about a certain stock from the observers
	 * 
	 * @param name
	 *            the name of the stock
	 * @param value
	 *            the value from the stock
	 */
	public abstract void update(String name, Float value);

	/**
	 * Print all information from an observer
	 */
	public abstract void printFeed();
}

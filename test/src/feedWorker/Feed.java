package feedWorker;

import java.util.*;

/**
 * This class represents a feed that stores observers and stocks. Each observer
 * observes the stocks and any time when there is an update, the observers will
 * be notified.
 * 
 * @author razvan
 *
 */
public class Feed {

	/**
	 * A list of observers.
	 */
	private List<FeedObserver> observers = new ArrayList<FeedObserver>();

	/**
	 * A map of stocks which contains a name and its value
	 */
	private TreeMap<String, Float> stock = new TreeMap<String, Float>();

	/**
	 * Create an new feed.
	 */
	public Feed() {
	}

	/**
	 * Create a new stock or update an existing one.
	 * 
	 * @param name
	 *            the name of the stock
	 * @param value
	 *            the value of that stock
	 */
	public void setStock(String name, Float value) {
		stock.put(name, value);

		notifyAllObservers(name, value);
	}

	/**
	 * Getter for stock
	 * 
	 * @return return the stock
	 * 
	 * @see Feed#stock
	 */
	public TreeMap<String, Float> getStock() {
		return stock;
	}

	/**
	 * Attach a new observer to the feed
	 * 
	 * @param observer
	 *            new FeedObserver which will be added
	 */
	public void attach(FeedObserver observer) {
		observers.add(observer);
	}

	/**
	 * Detach an existing observer in the feed
	 * 
	 * @param observer
	 *            a FeedObserver which will be removed
	 */
	public void detach(FeedObserver observer) {
		observers.remove(observer);
	}

	/**
	 * Print all statistics from an observer, specifying its ID
	 * 
	 * @param ID
	 *            the ID of the observer
	 */
	public void printObserver(int ID) {
		for (FeedObserver observer : observers) {
			if (observer.ID == ID) {
				observer.printFeed();
				return;
			}
		}
	}

	/**
	 * Detach an existing observer in the feed, by its ID
	 * 
	 * @param ID
	 *            the ID of the observer which will be removed
	 */
	public void detach(int ID) {
		for (FeedObserver observer : observers) {
			if (observer.ID == ID) {
				observers.remove(observer);
				return;
			}
		}
	}

	/**
	 * Notify all observers for a change to a single stock
	 * 
	 * @param name
	 *            the name of the stock
	 * @param value
	 *            the value of the stock
	 */
	public void notifyAllObservers(String name, Float value) {
		for (FeedObserver observer : observers) {
			observer.update(name, value);
		}
	}

	/**
	 * Update all observers. This method acts like a synchronization.
	 */
	public void syncObservers() {
		for (FeedObserver observer : observers) {
			observer.update();
		}
	}
}

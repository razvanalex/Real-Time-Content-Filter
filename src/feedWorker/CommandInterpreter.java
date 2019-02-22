package feedWorker;

import java.util.Scanner;

/**
 * CommandInterpreter takes as parameter a command form standard input and
 * interprets that command. Also, there are some methods that controls the input
 * stream. The commands affects the feed.
 * 
 * @author razvan
 *
 */
public class CommandInterpreter {

	/**
	 * The instance of CommandInterpreter, thus it is a singleton
	 */
	private static CommandInterpreter instance = null;

	/**
	 * Scanner for input stream.
	 */
	private Scanner scanner = null;

	/**
	 * Feed that is processed.
	 */
	private Feed feed = null;

	/**
	 * Create a new CommandInterpreter instance
	 */
	private CommandInterpreter() {
	}

	/**
	 * Get the instance created or create a new instance
	 * 
	 * @return an instance CommandInterpreter
	 */
	public static CommandInterpreter getInstance() {
		if (instance == null) {
			instance = new CommandInterpreter();
		}

		return instance;
	}

	/**
	 * Initialize a new feed
	 */
	public void initFeed() {
		if (feed == null) {
			feed = new Feed();
		}
	}

	/**
	 * Start the input stream and check first command.
	 */
	public void start() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
			String startingString = scanner.nextLine();

			if (!startingString.equals("begin")) {
				System.out.println(
						"Invalid starting point! Stream will be closed!");
				stop();
			}

		} else {
			System.out.println("Scanner already started!");
		}
	}

	/**
	 * Stop the stream.
	 */
	public void stop() {
		if (scanner != null) {
			scanner.close();
		} else {
			System.out.println("Scanner not started!");
		}
	}

	/**
	 * Interprets commands. The commands are reflected to the feed. Available
	 * commands are:
	 * 
	 * <ul>
	 * <li>create_obs &lt; id &gt; &lt; filter &gt; - create observer</li>
	 * <li>delete_obs &lt; id &gt; - delete observer</li>
	 * <li>print &lt; id &gt; - print observer</li>
	 * <li>feed &lt; name &gt; &lt; value &gt; - update stock</li>
	 * <li>end - end stream</li>
	 * </ul>
	 */
	public void interpretNextCmd() {
		try {
			String line = scanner.nextLine();
			if (line.isEmpty()) {
				return;
			}

			String trimedString = line.trim();
			String[] tokens = trimedString.split(" ");
			String cmd = tokens[0];
			int obs_id;

			switch (cmd) {
				case "create_obs":
					obs_id = Integer.parseInt(tokens[1]);
					int startFilterIndex = tokens[0].length()
							+ tokens[1].length() + 2;
					String filter = trimedString.substring(startFilterIndex);
					feed.attach(new FilterFeedObserver(feed, obs_id, filter));
					break;

				case "delete_obs":
					obs_id = Integer.parseInt(tokens[1]);
					feed.detach(obs_id);
					break;

				case "print":
					obs_id = Integer.parseInt(tokens[1]);
					feed.printObserver(obs_id);
					break;

				case "feed":
					String name = tokens[1];
					float value = Float.parseFloat(tokens[2]);
					feed.setStock(name, value);
					break;

				case "end":
					stop();
					break;

				default:
					System.out.println("Invalid Command!");
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Check if stream is opened or not
	 * 
	 * @return true if the stream is opened, otherwise false is returned.
	 */
	public boolean checkStream() {
		try {
			return scanner.hasNext();
		} catch (IllegalStateException e) {
			return false;
		}
	}

}

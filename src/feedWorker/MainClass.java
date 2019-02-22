package feedWorker;

/**
 * This class is the main class and represents the entry point in the program.
 * 
 * @author razvan
 *
 */
public class MainClass {

	/**
	 * Entry point of the program. It gets the input from stdin and interpret
	 * each command. The feeds are updated/created according to commands used.
	 * When the comand is "end" then the stream is closed and the program exits.
	 * 
	 * @param args
	 *            of program
	 */
	public static void main(String[] args) {
		CommandInterpreter ci = CommandInterpreter.getInstance();
		ci.initFeed();
		ci.start();

		while (ci.checkStream()) {
			ci.interpretNextCmd();
		}
	}

}

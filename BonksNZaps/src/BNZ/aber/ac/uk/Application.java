package BNZ.aber.ac.uk;
import java.util.ArrayList;
import java.util.Scanner;

public class Application {
	private Board gridWorld;
	private Scanner input = new Scanner(System.in);
	private int amountOfBonks = 0, amountOfZaps = 0, cycles = 0, size = 0;
	private boolean zaps = false;

	/**
	 * Bonks and zaps cs12320 main assignment 
	 * @author Phillip Jefferies
	 */

	public static void main(String[] args) throws CannotActException {
		Application app = new Application();
		app.run();
	}

	/**
	 * Shows the menu and gets the users input
	 * 
	 * @throws CannotActException
	 */
	private void run() throws CannotActException {
		String choice;
		do {
			printMenu();
			choice = getInput();
			switch (choice) {
			case "1":
				gridWorld = new Board(20, 5, 20, false);
				gridWorld.run(20);
				break;
			case "2":
				customSim();
				break;
			case "3":
				multiThread();
				break;
			case "4":
				System.out.println("Thanks for using this program!");
				break;
			default:
				System.out.println("Not a valid input, please try again");
				break;
			}
		} while (!(choice.equals("2")));

	}

	/**
	 * Sets up a customised simulation from user boundarys
	 * amount of bonks, zaps, cycles, world size and player controlled zaps
	 * @throws CannotActException
	 */
	private void customSim() throws CannotActException{
		getParams();
		boolean check = true;
		System.out.println("Do you want to control the Zaps (Y/N): ");
		do{
			check = true;
			String playerZaps = getInput();
			char temp = playerZaps.charAt(0);
			if(temp == 'Y'){
				zaps = true;
			}
			else if(temp == 'N'){
				zaps = false;
			}
			else{
				check = false;
			}
		}while(!check);
		gridWorld = new Board(amountOfBonks,amountOfZaps,size, zaps);
		gridWorld.run(cycles);
	}

	/**
	 * gets the users boundary for custom/multi-threaded simulations except for
	 * player controlled zaps
	 */
	private void getParams() {
		System.out.println("How many Bonks will this start with (0 - 200): ");
		amountOfBonks = getUserInput(200, 0);
		System.out.println("How many Zaps will be in this world (0 - 150): ");
		amountOfZaps = getUserInput(150, 0);
		System.out.println("How many days will be simulated (1 - 1000): ");
		cycles = getUserInput(1000, 0);
		System.out.println("How large is the world (1 - 200): ");
		size = getUserInput(200, 0);
	}
	
	/**
	 * gets a user input from between two numbers
	 * @param max the maximum amount
	 * @param min the minimum amount
	 * @return
	 */
	private int getUserInput(int max, int min) {
		boolean check;
		int number = 0;
		do
			try {
				number = Integer.parseInt(getInput());
				check = true;
				if (number > max || number < min) {
					check = false;
					System.out.println("Input should be between " + min + " - " + max + ": ");
				}
			} catch (Exception e) {
				check = false;
			}
		while (!check);
		return number;
	}

	/**
	 * sets up and runs 10 threads, the prints the results of each custom sim.
	 * non player controlled.
	 */
	private void multiThread() {
		ArrayList<SimThread> threads = new ArrayList<SimThread>();
		// starts each thread
		for (int i = 0; i < 10; i++) {
			String temp = "Thread " + (i+1);
			threads.add(new SimThread(temp));
		}
		// fetches the user requirements and starts each thread
		getParams();
		for (SimThread thread : threads) {
			System.out.println("Starting: " + thread.getNm());
			thread.setup(amountOfBonks, amountOfZaps, size, cycles);
			thread.start();
		}
		// waits till all the threads are finished
		boolean check;
		do {
			check = true;
			for (SimThread thread : threads) {
				if (thread.isAlive()) {
					check = false;
					System.out.println("Waiting...");
					Long start = System.currentTimeMillis();
					do{}while(System.currentTimeMillis() - start < 1000);
				}
			}
		} while (!check);

		for (SimThread thread : threads) {
			thread.results();
		}
		threads.removeAll(threads);
	}

	/**
	 * returns input from the keyboard literally just easier to type getInput()
	 * than input.nextLine()
	 * 
	 * @return
	 */
	private String getInput() {
		return input.nextLine().toUpperCase();
	}

	/**
	 * Prints out the menu options
	 */
	private void printMenu() {
		System.out.print(
				  "Welcome to bonks and zaps \n"
				+ "  1 - run base simulation (20 bonks, 5 zaps, 20 cycles, 20*20 world) \n"
				+ "  2 - custom simulation (X bonks, X zaps, X cycles, X*X world, player choice of zap control)\n"
				+ "  3 - multi-threading of multiple custom sims for comparison \n"
				+ "  4 - quit \n"
				+ "input: ");
	}
}
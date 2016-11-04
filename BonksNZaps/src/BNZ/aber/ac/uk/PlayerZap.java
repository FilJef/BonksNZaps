package BNZ.aber.ac.uk;
import java.util.Scanner;

public class PlayerZap extends Zap {
	
	/**
	 * see zap constructor
	 * @param number
	 * @param loc
	 * @param size
	 */
	public PlayerZap(int number, Position loc, int size) {
		super(number, loc, size);
	}
	
	/**
	 * gets the user to input a direction
	 */
	public Position getDir() {
		boolean finish = false;
		System.out.println("1,2,3");
		System.out.println("4,0,5");
		System.out.println("6,7,8");
		System.out.println("Where 0 is " + name + "'s current position, if zap moves out of world it is moved back in.");
		
		Scanner in = new Scanner(System.in);
		do {
			try {
				int hold = in.nextInt();
				switch (hold) {
				case 0:
					break;
				case 1:
					return (new Position(location.getX() - 1, location.getY() - 1));
				case 2:
					return (new Position(location.getX() - 1, location.getY()));
				case 3:
					return (new Position(location.getX() - 1, location.getY() + 1));
				case 4:
					return (new Position(location.getX(), location.getY() - 1));
				case 5:
					return (new Position(location.getX(), location.getY() + 1));
				case 6:
					return (new Position(location.getX() + 1, location.getY() - 1));
				case 7:
					return (new Position(location.getX() + 1, location.getY()));
				case 8:
					return (new Position(location.getX() + 1, location.getY() + 1));
				}
				finish = true;
			} catch (Exception e) {
				System.out.println("Invalid move, please input a number 0 - 8");
				finish = false;
			}
		} while (!finish);
		in.close();
		return location;
	}
}

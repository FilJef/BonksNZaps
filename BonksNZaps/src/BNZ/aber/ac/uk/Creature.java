package BNZ.aber.ac.uk;
import java.util.Random;

public class Creature implements Being {
	String name;
	Random rand = new Random();
	Position location;
	final int worldSize;
	boolean act = true;
	Cell worldRef[][];

	/**
	 * sets up the basic creature
	 * @param nm the name
	 * @param tempLocthe location
	 * @param size the world size
	 */
	public Creature(String nm, Position tempLoc, int size) {
		name = nm;
		location = tempLoc;
		worldSize = size;
	}

	/**
	 * allows the creature to move to any cell next to it or stay in this one it
	 * also moves the creature back into bounds
	 */
	public void move() {
		Position hold = new Position(location.getX(), location.getY());
		
		this.setLocation(new Position(getDir()));
		
		if (location.getY() > worldSize - 1) {
			location.setY(worldSize - 1);
		} else if (location.getY() < 0) {
			location.setY(0);
		}

		if (location.getX() > worldSize - 1) {
			location.setX(worldSize - 1);
		} else if (location.getX() < 0) {
			location.setX(0);
		}
		
		worldRef[location.getX()][location.getY()].add(this);
		worldRef[hold.getX()][hold.getY()].remove(this);
	}
	
	/**
	 * returns a random number that is the direction it is heading
	 * overridden in the PlayerZap class
	 * @return
	 */
	private Position getDir() {
		return new Position(location.getX() + rand.nextInt(3)-1, location.getY() + rand.nextInt(3)-1);
	}
	
	/**
	 * sets the act status of the Creature
	 * @param temp
	 */
	public void setAct(boolean temp) {
		act = temp;
	}
	
	/**
	 * returns if the Creature has acted this turn
	 * @return
	 */
	public boolean getAct() {
		return act;
	}
	
	/**
	 * see Being
	 */
	public void setLocation(Position temp) {
		location = temp;
	}
	
	/**
	 * see Being
	 */
	public Position getLocation() {
		return location;
	}
	
	/**
	 * gives every creature a reference of the world grid
	 * @param temp
	 */
	public void setWorld(Cell[][] temp) {
		worldRef = new Cell[worldSize][worldSize];
		for (int x = 0; x < worldSize; x++) {
			for (int y = 0; y < worldSize; y++) {
				worldRef[x][y] = temp[x][y];
			}
		}
	}

	/**
	 * see Being
	 */
	public String getName() {
		String returnable = name;
		return returnable;
	}

	/**
	 * see Being
	 */
	public void act() throws CannotActException {
		if(act){
			move();
		}
	}
}
package BNZ.aber.ac.uk;
import java.util.ArrayList;

public class Bonk extends Creature{
	boolean canBreed = false;
	boolean alive = true;
	boolean gender = rand.nextBoolean();
	int id;
	Bonk parent1;
	Bonk parent2;

	/**
	 * sets up the bonk
	 * @param number the bonks ID (BxA) x = id
	 * @param size world size
	 * @param name1 parents 1
	 * @param name2 parent 2
	 */
	public Bonk(int number, Position tempLoc,int size, Bonk name1, Bonk name2) {
		super("B" + Integer.toHexString(number) + "A",tempLoc, size);
		id = number;
		parent1 = name1;
		parent2 = name2;
	}
	/**
	 * see being
	 */
	@Override
	public void act() throws CannotActException {
		if(act && alive){
			breed();
			move();
			act = false;
		}
	}
	
	/**
	 * called when the bonk is killed, sets the name to dead state and flags as dead
	 */
	public void kill() {
		alive = false;
		name = "B" + Integer.toHexString(id) + "D";
	}
	
	/**
	 * Breeds this bonk with another bonk that is alive, not related and the
	 * opposite gender
	 */
	private void breed() {
		ArrayList<Creature> add = new ArrayList<Creature>();
		if (this.canBreed && this.alive) {
			for (Creature B : worldRef[location.getX()][location.getY()].getList()) {
				if (B.getName().charAt(0) == 'B') {
					if (((Bonk) B).canBreed() && (((Bonk) B).getGender() != this.gender) && ((Bonk) B).isAlive()
							&& !((Bonk) B).related(this)) {
						Bonk temp = new Bonk(getNumber(), this.getLocation(),worldSize, this, (Bonk) B);
						temp.setWorld(worldRef);
						add.add(temp);
						((Bonk) B).setBreed(false);
						canBreed = false;
					}
				}
			}
		}
		add.removeAll( worldRef[location.getX()][location.getY()].getList());
		worldRef[location.getX()][location.getY()].getList().addAll(add);
	}
	
	/**
	 * works out what number the new bonk is
	 * @return
	 */
	private int getNumber() {
		int number = 20;
		for (int x = 0; x < worldSize; x++) {
			for (int y = 0; y < worldSize; y++) {
				for (Being B : worldRef[x][y].getList()) {
					if (B.getName().charAt(0) == 'B') {
						if (((Bonk) B).getID() > number) {
							number = ((Bonk) B).getID();
						}
					}
				}
			}
		}
		return number + 1;
	}

	/**
	 * returns if this bonk is related to another
	 * @param temp
	 * @return
	 */
	private boolean related(Bonk temp) {
		boolean answer = false;
		if (temp == this.getDad() || temp == this.getMum() || this == temp.getDad() || this == temp.getMum()) {
			answer = true;
		} else {
			if (temp.getDad() != null && !answer) {
				answer = temp.getDad().related(this);
			}
			if (temp.getMum() != null && !answer) {
				answer = temp.getMum().related(this);
			}
			if (this.getDad() != null && !answer) {
				answer = this.getDad().related(temp);
			}
			if (this.getDad() != null && !answer) {
				answer = this.getDad().related(temp);
			}
		}
		return answer;
	}
	
	/**
	 * returns a parent of this bonk
	 * @return
	 */
	public Bonk getDad() {
		return parent1;
	}
	/**
	 * returns the other parent
	 * @return
	 */
	public Bonk getMum() {
		return parent2;
	}

	/**
	 * returns if the bonk is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * returns if the bonk can breed
	 */
	public boolean canBreed() {
		return canBreed;
	}

	/**
	 * sets the breed status
	 */
	public void setBreed(boolean set) {
		canBreed = set;
	}

	/**
	 * returns the gender of this bonk
	 */
	public boolean getGender() {
		return gender;
	}

	/**
	 * returns the bonks number
	 */
	public int getID() {
		return id;
	}

}

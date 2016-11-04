package BNZ.aber.ac.uk;
public class Zap extends Creature{
	
	/**
	 * see creature
	 */
	public Zap(int number, Position tempLoc, int size) {
		super("Z" + Integer.toHexString(number), tempLoc,size);
	}
	/**
	 * acts if it has not this cycle
	 */
	public void act() throws CannotActException{
		if(act){
			kill();
			move();
			act = false;
		}
	}
	/**
	 * kills every single bonk in the same cell as this one
	 */
	private void kill(){
		for(Creature B: worldRef[location.getX()][location.getY()].getList()){
			if(B.getName().charAt(0) == 'B'){
				((Bonk) B).kill();
			}
		}
	}
}
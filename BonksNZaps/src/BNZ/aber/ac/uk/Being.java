package BNZ.aber.ac.uk;

public interface Being {
	/**
	 * returns the name of the Being
	 * @return
	 */
	public String getName();
	/**
	 * being does its act phase, throws exception if it cannot
	 * @throws CannotActException
	 */
	public void act() throws CannotActException;
	/**
	 * returns the location of the being in a class that contains x,y
	 * @return
	 */
	public	Position getLocation();
	/**
	 * makes the beings location = loc
	 * @param Loc
	 */
	public void setLocation(Position Loc);
}
package BNZ.aber.ac.uk;

public class Position {
	private int X, Y;
	
	/**
	 * sets the x and y of position
	 * @param x
	 * @param y
	 */
	public Position(int x, int y){
		this.X = x;
		this.Y = y;
	}
	
	public Position(Position dir) {
		this.X = dir.X;
		this.Y = dir.Y;
	}

	/**
	 * returns x
	 * @return
	 */
	public int getX() {
		return this.X;
	}
	
	/**
	 * sets x
	 * @param x
	 */
	public void setX(int x) {
		this.X = x;
	}
	
	/**
	 * gets Y
	 * @return
	 */
	public int getY() {
		return this.Y;
	}
	
	/**
	 * sets y
	 * @param y
	 */
	public void setY(int y) {
		this.Y = y;
	}
}
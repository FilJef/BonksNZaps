package BNZ.aber.ac.uk;

public class SimThread extends Thread{
	
	private ThreadBoard threadBoard;
	String name;
	int cyc;
	
	/**
	 * sets up the thread name
	 * @param nm
	 */
	public SimThread(String nm){
		name = nm;
	}
	
	/**
	 * sets up the simulation
	 * @param b
	 * @param z
	 * @param s
	 * @param c
	 */
	public void setup(int b, int z, int s, int c){
		cyc = c;
		try {
			threadBoard = new ThreadBoard(b,z,s,false, this.getName());
		} catch (CannotActException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * runs the simulation
	 */
	public void run(){
		try {
			threadBoard.run(cyc);
		} catch (CannotActException e) {
			e.printStackTrace();
		}
		System.out.println("" + this.getNm() + " has finished");
	}
	
	/**
	 * prints out the threads results
	 */
	public void results(){
		threadBoard.getInfo();
	}
	
	/**
	 * returns the name
	 * @return
	 */
	public String getNm(){
		return name;
	}
}

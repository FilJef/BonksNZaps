package BNZ.aber.ac.uk;

public class ThreadBoard extends Board {
	int startZap;
	String name;
	int startBonk;
	int days;
	long time;

	/**
	 * sets up the board
	 * 
	 * @param genBonks
	 * @param genZaps
	 * @param size
	 * @param player
	 * @param title
	 * @throws CannotActException
	 */
	public ThreadBoard(int genBonks, int genZaps, int size, boolean player, String title) throws CannotActException {
		super(genBonks, genZaps, size, player);
		name = title;
		startZap = genZaps;
		startBonk = genBonks;
	}

	/**
	 * very similar to Board.run, with the delay and the display output. these
	 * were removed to improve sim time and reduce screen spam
	 */
	public void run(int cycles) throws CannotActException {
		long start = System.currentTimeMillis();
		days = cycles;
		for(int i = 0;i < cycles; i++){
			setActStatus();
			for(int x = 0; x < worldSize; x++){
				for(int y = 0; y < worldSize; y++){
					world[x][y].actB();
				}
			}
			for(int x = 0; x < worldSize; x++){
				for(int y = 0; y < worldSize; y++){
					world[x][y].actZ();
				}
			}
		}
		long stop = System.currentTimeMillis();
		stop = stop - start;
		time = (stop);
	}

	/**
	 * prints out the info of a simulation
	 */
	public void getInfo() {
		String info = "";
		info = "[" + name + "] \n";
		info += "Size: " + worldSize + "\n";
		info += "Cycles: " + days + "\n";
		info += "Start Bonks: " + startBonk + "\n";
		info += "Amount of Zaps: " + startZap + "\n";
		Position temp = bonksBornKill();
		info += "Bonks born: " + temp.getX() + "\n";
		info += "Bonks killed: " + temp.getY() + "\n";
		info += "Time in milliseconds: " + time + "\n";
		System.out.println(info);
	}

	/**
	 * returns a position that is just used as a package to return the amount of
	 * bonks born and the amount of bonks killed in a simulation
	 * 
	 * @return
	 */
	private Position bonksBornKill() {
		int born = 0;
		int died = 0;
		for (int x = 0; x < worldSize; x++) {
			for (int y = 0; y < worldSize; y++) {
				for (Creature B :  world[x][y].getList()) {
					if (B instanceof Bonk) {
						if (((Bonk) B).getID() > startBonk) {
							born += 1;
						}
						if (!((Bonk) B).isAlive()) {
							died += 1;
						}
					}
				}
			}
		}
		Position pack = new Position(born, died);
		return pack;
	}
}

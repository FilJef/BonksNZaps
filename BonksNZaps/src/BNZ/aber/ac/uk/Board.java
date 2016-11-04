package BNZ.aber.ac.uk;
import java.util.Random;

public class Board {
	//use a 3 d array for x, y and all monsters in the room
	Cell world[][];
	int numOfBonks = 0;
	final int worldSize;
	Random rand = new Random();
	private Display output;
	
	/**
	 * instanitates the board by creating the game world
	 * genbonks amount of bonks and genzap amount of zaps
	 * then lets the simulation run for cycle days
	 * @param genBonks
	 * @param genZaps
	 * @param cycles
	 * @throws CannotActException
	 */
	public Board(int genBonks, int genZaps, int size, boolean player) throws CannotActException{
		Position tempLoc = null;
		worldSize = size;
		world = new Cell[worldSize][worldSize];
		output = new Display(worldSize,world);
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				world[x][y] = new Cell(worldSize);
			} 
		}
		//generates and places x bonks (default 20)
		for(int i = 1; i < (genBonks + 1); i++){
			numOfBonks += 1;
			tempLoc = new Position(getRandom(worldSize),getRandom(worldSize));
			world[tempLoc.getX()][tempLoc.getY()].add(new Bonk(numOfBonks,tempLoc, size, null, null));
		}
		//generates and places x zaps (default 5)
		if(player){
			genAllPlayzaps(tempLoc, genZaps);
		}
		else{
			genAllZaps(tempLoc,genZaps);
		}
		
		for(int x = 0; x < worldSize; x++){
			for(int y = 0; y < worldSize; y++){
				for(Creature B: world[x][y].getList()){
					B.setWorld(world);
				}
			}
		}
	}
	
	/**
	 * generates all the zaps player controlled
	 * @param tempLoc
	 * @param genZaps
	 */
	private void genAllPlayzaps(Position tempLoc, int genZaps){
		for(int i = 1; i < (genZaps + 1); i++){
			tempLoc = new Position(getRandom(worldSize),getRandom(worldSize));
			world[tempLoc.getX()][tempLoc.getY()].add(new PlayerZap(i,tempLoc, worldSize));
		}
	}
	
	/**
	 * generates an amount of zaps
	 * @param tempLoc
	 * @param genZaps
	 */
	private void genAllZaps(Position tempLoc, int genZaps){
		for(int i = 1; i < (genZaps + 1); i++){
			tempLoc = new Position(getRandom(worldSize),getRandom(worldSize));
			world[tempLoc.getX()][tempLoc.getY()].add(new Zap(i,tempLoc, worldSize));
		}
	}
	
	/**
	 * simulates x days where cycle == x
	 * and displays an output
	 * all zaps act first, then bonks act
	 * this is so that bonks breed after everything has been killed
	 * @param cycles
	 * @throws CannotActException
	 */
	public void run(int cycles) throws CannotActException{
		for(int i = 0;i < cycles; i++){
			System.out.println("Day " +(i+1)+ " of day " +cycles);
			setActStatus();
			output.display();
			for(int x = 0; x < worldSize; x++){
				for(int y = 0; y < worldSize; y++){
					world[x][y].actZ();
				}
			}
			for(int x = 0; x < worldSize; x++){
				for(int y = 0; y < worldSize; y++){
					world[x][y].actB();
				}
			}
			Long start = System.currentTimeMillis();
			do{}while(System.currentTimeMillis() - start < 1000);
		}
	}
	/**
	 * sets the status of all bonks and zaps to allow movement
	 * also sets all the bonks breeding to true
	 */
	protected void setActStatus(){
		for(int x = 0; x < worldSize; x++){
			for(int y = 0; y < worldSize; y++){
				for(Creature B: world[x][y].getList()){
					B.setAct(true);
					if(B.getName().charAt(0) == 'B'){
						((Bonk) B).setBreed(true);
					}
				}
			}
		}
	}
	
	/**
	 * returns a random int of 0 - number - 1
	 * @param number
	 * @return
	 */
	public int getRandom(int number){
		return rand.nextInt(number);
	}

}

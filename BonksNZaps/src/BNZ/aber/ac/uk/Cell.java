package BNZ.aber.ac.uk;
import java.util.ArrayList;

public class Cell {
	private ArrayList<Creature> population;
	private final int size;
	/**
	 * sets up the cell
	 */
	public Cell(int worldSize){
		population = new ArrayList<Creature>();
		size = worldSize;
	}
	
	/**
	 * adds in item temp
	 * @param temp
	 */
	public void add(Creature temp){
		population.add(temp);
	}
	
	/**
	 * removes item temp
	 * @param temp
	 */
	public void remove(Being temp){
		population.remove(temp);
	}
	
	/**
	 * sets the whole arraylist to newPop
	 * @param newPop
	 */
	public void setList(ArrayList<Creature> newPop){
		population = newPop;
	}
	
	/**
	 * returns the current arraylist
	 * @return
	 */
	public ArrayList<Creature> getList(){
		return population;
	}
	
	/**
	 * all the bonks in this cell act
	 */
	public void actB(){
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				for(int b =0; b < population.size(); b++){
					if(population.get(b).getName().charAt(0) == 'B'){
						try {
							population.get(b).act();
						} catch (CannotActException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	/**
	 * all the zaps in this cell act
	 */
	public void actZ(){
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				for(int b =0; b < population.size(); b++){
					if(population.get(b).getName().charAt(0) == 'Z'){
						try {
							population.get(b).act();
						} catch (CannotActException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
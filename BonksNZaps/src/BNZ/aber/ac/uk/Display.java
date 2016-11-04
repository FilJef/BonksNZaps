package BNZ.aber.ac.uk;

import javafx.scene.control.Label;

public class Display {
	final int worldSize;
	private Cell worldCopy[][];

	public Display(int size, Cell worldRef[][]){
		worldSize = size;
		worldCopy = worldRef;
	}
	
	/**
	 * gives the display in rows an columns rather than 400 lines of output each cycle.
	 * also is much nicer to look at and gives a visual representation
	 */
	public void display(){
		System.out.println("");
		recursiveDraw(0);
		borderLine();
	}
	
	/**
	 * x being the current row i am drawing into the grid, uses recursion to write out the display
	 * @param x
	 */
	private void recursiveDraw(int x){
		Cell temp[] = new Cell[worldSize];
		borderLine();

		for(int y = 0; y < worldSize; y++){
				temp[y] = worldCopy[x][y];
		}
		//finds the array list with the longest length so we know the height of these cells
		int height = 2;
        for(int y = 0; y < worldSize; y++){
			if(height < worldCopy[x][y].getList().size()){
				height = worldCopy[x][y].getList().size();
			}
		}
		String output[] = new String[height];
		for(int i = 0; i < height; i++){
			String lineOut = "#";
			for(int o = 0; o < worldSize; o++){
				try{
					lineOut += makeLength(temp[o].getList().get(i).getName());
				}
				catch(Exception e){
					lineOut += "      #";
				}
				output[i] += lineOut;
			}
			System.out.println(lineOut);
		}
		if(x < (worldSize - 1)){
			recursiveDraw(x + 1);
		}
	}
	
	/**
	 * temp is a string of <6 length that this will make a length of 6 + #
	 * using this i can support up to 9999 bonks. more if i used hexadecimal (which im obviously not).
	 * @param temp
	 * @return
	 */
	private String makeLength(String temp){
		String output;
		output = temp;
		if( output.length() != 6){
			do{
				output += " ";
			}while(output.length() != 6);
		}
		output += "#";
		return output;
	}
	
	/**
	 * borderline prints out a horizontal line of #'s of dynamic length 
	 */
	private void borderLine(){
		String ret = "#";
		for(int i = 0; i < worldSize; i++){
			ret += "#######";
		}
		System.out.println(ret);		
	}
}

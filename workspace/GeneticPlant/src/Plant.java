import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Plant implements EnvEntity {
	int totalHealth;
	int numCells;
	
	ArrayList<PlantCell> listOfCells;
	Environment environmentPointer;
	
	//Create new sprout
	public Plant(GeneticInfo gi, Environment e)
	{
		environmentPointer = e;
		listOfCells = new ArrayList<PlantCell>();
		numCells = 0;
		totalHealth = 0;
		
		//TODO: Check - First cell gets first genetic info, rest is inherited by parent
		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 1, 8, 10));
		
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 2, 7, 10));
//		
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 3, 6, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 4, 5, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 5, 4, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 5, 5, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 6, 6, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 5, 7, 10));
//		
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 3, 8, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 4, 9, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 5, 10, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 6, 9, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 6, 8, 10));
//		listOfCells.add(new PlantCell(this, gi, 0, -1,-1, environmentPointer.map.length - 5, 7, 10));
		
		//TODO: Remove - 4 seeds for testing
//		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 1, 9, 10));
//		
//		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 2, 10, 10));
//		
//		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 2, 9, 10));
	}
	
	//--Basically gets all cells to do something in random order, and removes dead cells
	//Reservoir samples all cells,
	//TODO: Give health from sun
	//Ask each cell to do action
	//	if action is grow cell, add cell to general list of cells
	//If cell is dead, mark it
	//Clear dead cells
	public void step()
	{
		// Reservoir sampling by generating ordered list of numbers
		// randomly pick a number within length and use that as index
		Random rand = new Random();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i < listOfCells.size(); i++) { numbers.add(i); }
		
		// Randomly sample from reservoir until empty
		while(numbers.size() > 0)
		{
			int picked_index = rand.nextInt(numbers.size());
			int picked_val = numbers.get(picked_index);
			
			//TODO: Maybe call a function on cell of random action that might occur
			//	Consider only returning object when necessary, (how it is now)
			PlantCell newObject = listOfCells.get(picked_val).doAction();
			if(newObject != null)
			{
//				System.out.println("New cell generated!");
				listOfCells.add(newObject);
			}
			
			//Kill spawning cell if its health drops <= 0
			//should die right away to make room for other cell
			if(listOfCells.get(picked_val).getHealth() <= 0)
			{
				environmentPointer.map[listOfCells.get(picked_val).getX()][listOfCells.get(picked_val).getY()] = null;
				listOfCells.set(picked_val, null); //Set to null as to preserve list integrity for reservoir sampling, will be removed later
			}
			
			numbers.remove(picked_index);
		}
		
		// All recently dead cells are now null in our cell list, so we are removing them from the list now
		for(int i = 0; i < listOfCells.size(); i++)
		{
			if(listOfCells.get(i) == null)
			{
				listOfCells.remove(i);
				i--; //Adjust for changing size
			}
		}
	}
	
	//Setters and getters
	public int getTotalHealth() { return this.totalHealth; }
	public int getNumCells() { return this.numCells; }
	public ArrayList<PlantCell> getCellList() { return this.listOfCells; }
	public Environment getEnvironment() { return this.environmentPointer; }
	
	//Statistic information
	public void printCellsHealth()
	{
		for(PlantCell cell : listOfCells)
			System.out.printf("(%d, %d)|P(%d, %d): %d\t", cell.getX(), cell.getY(), cell.getParentX(), cell.getParentY(), cell.getHealth());
		System.out.println();
	}
}

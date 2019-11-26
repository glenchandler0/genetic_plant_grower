import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Plant implements EnvEntity {
	int totalHealth;
	int numCells;
	
	ArrayList<PlantCell> listOfCells;
	
	//Create new sprout
	public Plant(GeneticInfo gi)
	{
		listOfCells = new ArrayList<PlantCell>();
		numCells = 0;
		totalHealth = 0;
		
		//TODO: Check - First cell gets first genetic info, rest is inherited by parent
		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 1, 10, 10));
		
		//TODO: Remove - 4 seeds for testing
		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 1, 9, 10));
		
		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 2, 10, 10));
		
		listOfCells.add(new PlantCell(this, gi, -1,-1, Environment.map.length - 2, 9, 10));
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

			//TODO: Remove this feature, replicates the sun
			int randGetHealth = rand.nextInt(5);
			if(randGetHealth == 0) {
				System.out.printf("Cell (%d,%d) gained health from the sun!\n", listOfCells.get(picked_val).getX(), listOfCells.get(picked_val).getY());
				listOfCells.get(picked_val).setHealth(listOfCells.get(picked_val).getHealth() + 1);
			}
			//TODO: Remove this feature, replicates starvation
			int randStarve = rand.nextInt(5);
			if(randStarve == 0) {
				System.out.printf("Cell (%d,%d) lost health from starvation!\n", listOfCells.get(picked_val).getX(), listOfCells.get(picked_val).getY());
				listOfCells.get(picked_val).setHealth(listOfCells.get(picked_val).getHealth() - 2);
			}
			
			//TODO: Maybe call a function on cell of random action that might occur
			//	Consider only returning object when necessary, (how it is now)
			PlantCell newObject = listOfCells.get(picked_val).doAction();
			if(newObject != null)
			{
				System.out.println("New cell generated!");
				listOfCells.add(newObject);
			}
			
			//Kill spawning cell if its health drops <= 0
			//should die right away to make room for other cell
			if(listOfCells.get(picked_val).getHealth() <= 0)
			{
				Environment.map[listOfCells.get(picked_val).getX()][listOfCells.get(picked_val).getY()] = null;
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
	
	//Statistic information
	public void printCellsHealth()
	{
		for(PlantCell cell : listOfCells)
			System.out.printf("(%d, %d)|P(%d, %d): %d\t", cell.getX(), cell.getY(), cell.getParentX(), cell.getParentY(), cell.getHealth());
		System.out.println();
	}
}

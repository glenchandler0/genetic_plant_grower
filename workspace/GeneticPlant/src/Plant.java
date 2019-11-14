import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Plant implements EnvEntity {
	int totalHealth;
	int numCells;
	
	ArrayList<PlantCell> listOfCells;
	
	public Plant()
	{
		listOfCells = new ArrayList<PlantCell>();
		numCells = 0;
		totalHealth = 0;
		
		listOfCells.add(new PlantCell(Environment.map.length - 1, 10));
	}
	
	public void step()
	{
		//TODO: Sort randomly here
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
			//	Also, consider passing list of cells to cell so it can handle nullifying itself,
			//	this would also allow for the generic function to be void (TODO: add protected to plant?)
			//	Might not be great, however, considering amount of meta plant info we might need.
			//	Consider only returning object when necessary, (how it is now)
			PlantCell newObject = listOfCells.get(picked_val).growAdjacent();
			if(newObject != null)
			{
				System.out.println("Object not null!");
				listOfCells.add(newObject);
				
				//Kill spawning cell if its health drops <= 0
				//should die right away to make room for other cell
				if(listOfCells.get(picked_val).getHealth() <= 0)
				{
					Environment.map[listOfCells.get(picked_val).getX()][listOfCells.get(picked_val).getY()] = null;
					listOfCells.set(picked_val, null); //Set to null as to preserve list integrity for reservoir sampling, will be removed later
				}
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
			System.out.printf("(%d, %d): %d\t", cell.getX(), cell.getY(), cell.getHealth());
		System.out.println();
	}
}

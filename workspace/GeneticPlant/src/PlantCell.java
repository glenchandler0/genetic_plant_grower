import java.awt.Point;
import java.util.ArrayList;
import java.util.Random; 

public class PlantCell extends EnvObject{
	int x;
	int y;
	
	int parentX;
	int parentY;
	
	int health;
	
	Plant myPlant; //Reference to full plant 
	
	GeneticInfo genes;
	
	//TODO Add type of plant
	
	//TODO Add parent direction meta info
		//-Maybe change health moving to giving to children vs parent and randomize proportions?

	
	//TODO: Add genetic ticket information here
	//	possibly meta data class? For easy genetic handling later?
	
	
	public PlantCell(Plant plantPointer, int parentX, int parentY)
	{
		//TODO: Handle generic cell creation
		
		health = 5; //TODO: Make random
		
		myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
	}
	
	public PlantCell(Plant plantPointer, int parentX, int parentY, int x, int y)
	{
		this.x = x;
		this.y = y;
		
		health = 5; //TODO: Make random
		
		Environment.map[x][y] = this;
		myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
	}
	
	//Actions
	//This function is the function that will ultimately encode genetics into actions and directions
	//TODO: Implement random action - you can add arguments back to grow adjacent and pass health
	//	this function will probably be the one that handles genetic ticket handling
	public PlantCell doAction()
	{	
		//TODO: Implement with genetics
		//Choose action
		
		//Choose direction for certain action
		
		//Choose amount for direction
		
		
		Random rand = new Random();
		int chooseAction = rand.nextInt(3);
		
		int newX = pickX(chooseAction);
		int newY = pickY(chooseAction);
		
		if(chooseAction == 0)
			return growAdjacent(newX, newY);
		else if(chooseAction == 1 || chooseAction == 2)
			return passHealth(newX, newY);
		
		System.out.println("Code should never get here - doAction()");
		return null; //stub
	}
	
	//TODO: Complete
	//Postcondition: Null corresponds to no cell needs to be added to 
	public PlantCell passHealth(int newX, int newY)
	{
		System.out.println("Attempting to give heatlh!");
		
		int healthIncr = 1; //Should always be positive
		
		//TODO: Rethink this rule
		if(this.health <= healthIncr)
			return null;
		
		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < Environment.map.length && newY > 0 && newY < Environment.map[0].length) {
			if(Environment.map[newX][newY] != null)
			{
				//Code to ensure adjacent cell is part of this plant
				for(PlantCell cell: myPlant.getCellList())
				{
					if(cell == null) {
						System.out.println("Found null cell in cell list while in passHealth function!");
						continue;
					}
					if(cell.getX() == newX && cell.getY() == newY)
					{
						//Give health to another, and take from myself
						cell.setHealth(cell.getHealth() + healthIncr);
						this.health = this.health - healthIncr;
						
						System.out.println("Gave health!");
						
						return null;
					}
				}
			}
		}
		
		return null;
	}
	
	public PlantCell growAdjacent(int newX, int newY)
	{
		//TODO: Rethink feature for cells not to end themselves
		if(this.health <= 2)
			return null;
		
		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < Environment.map.length && newY > 0 && newY < Environment.map[0].length) {
			if(Environment.map[newX][newY] == null)
			{
				newPlant = new PlantCell(myPlant, this.x, this.y, newX, newY);
				
				Environment.map[newX][newY] = newPlant;
				
				this.health -= 2; //Spawning a new plant reduces health
				return newPlant;
			}
		}
		
		return null;
	}
	
	//Private functions
	private int pickX(int chooseAction)
	{		
		//TODO: Change directionality to refer to parent cell
		//Logic for deciding a direction
		int xDiff = 0;
		if(this.parentX < 0) {
			xDiff = 1;
		} else {
			xDiff = (this.parentX - this.x);// / Math.abs(this.parentX - this.x);
		}
		
		//Diffs are already oriented towards parent, if we want to grow new, we should go other way
		if(chooseAction == 0) {
			xDiff *= -1;
		}
	
		return this.x + xDiff;
	}
	private int pickY(int chooseAction)
	{		
		//TODO: Change directionality to refer to parent cell
		//Logic for deciding a direction
		int yDiff = 0;
		if(this.parentX < 0 || this.parentY < 0) {
			yDiff = 0;
		} else {
			yDiff = (this.parentY - this.y);// / Math.abs(this.parentY - this.y);
		}
		
		//Diffs are already oriented towards parent, if we want to grow new, we should go other way
		if(chooseAction == 0) {
			yDiff *= -1;
		}
		
		return this.y + yDiff;
	}
	
	//Setters and getters
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getHealth() { return this.health; }
	public int getParentX() { return this.parentX; }
	public int getParentY() { return this.parentY; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setHealth(int health) { this.health = health; }
}

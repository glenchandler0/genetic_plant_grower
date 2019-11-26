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
	
	GeneticInfo genes; //Will be used for making decisions
	
	//TODO Add type of plant
	
	
	public PlantCell(Plant plantPointer, GeneticInfo genes, int parentX, int parentY)
	{
		//TODO: Handle different plant cell cases here by copying previous genetics, then modifying this newer one
		
		
		//TODO: Handle generic cell creation
		
		health = 5; //TODO: Make random
		
		this.genes = genes;
		this.myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
	}
	
	public PlantCell(Plant plantPointer, GeneticInfo genes, int parentX, int parentY, int x, int y, int health)
	{
		this.x = x;
		this.y = y;
		Environment.map[x][y] = this;
		
		this.health = health; //TODO: Make random
		
		this.genes = genes;
		myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
	}
	
	public PlantCell doAction()
	{
		System.out.printf("cell (%d,%d) ", this.x, this.y);
		//0-grow, 1-share, 2-obstain
		int chooseAction = genes.chooseAction();
		
		//Get direction based on action
		int direction = -1;
		if(chooseAction == 0) {
			direction = genes.chooseGrowDirection();
			System.out.print("Chose to grow ");
		}
		else if(chooseAction == 1) {
			direction = genes.chooseShareDirection();
			System.out.print("Chose to share ");
		}
		else {
			System.out.print("Chose to obstain ");
		}
		
		//Decode new location
		int newX = -1, newY = -1;
		if(direction == 0) { //Left
			newX = this.x;
			newY = this.y - 1;
		} else if(direction == 1) { //Right
			newX = this.x;
			newY = this.y + 1;
		} else if(direction == 2) { //Up
			newX = this.x - 1;
			newY = this.y;
		} else if(direction == 3) { //Down
			newX = this.x + 1;
			newY = this.y;
		} 
		//TODO: Probably going to want to remove the below, seems to confuse it
		else if(direction == 4) { //xParent
			if((this.x > this.parentX))
				newX = this.x - 1;
			else
				newX = this.x + 1;
			newY = this.y;
		} else if(direction == 5) { //xParentAway
			if((this.x > this.parentX))
				newX = this.x + 1;
			else
				newX = this.x - 1;
			newY = this.y;
		} else if(direction == 6) { //yParent
			if((this.y > this.parentY))
				newY = this.y - 1;
			else
				newY = this.y + 1;
			newX = this.x;
		} else if(direction == 7) {	 //yParentAway
			if((this.y > this.parentY))
				newY = this.y + 1;
			else
				newY = this.y - 1;
			newX = this.x;
		}
		System.out.printf("into location: (%d,%d)\n", newX, newY);
		
		//Apply action with location
		if(chooseAction == 0) {
			return growAdjacent(newX, newY);
		}
		else if(chooseAction == 1) {
			return shareHealth(newX, newY);
		}
		else {
			;
		}
		
		return null;
	}
	
	//TODO: Complete
	//Postcondition: Null corresponds to no cell needs to be added to 
	public PlantCell shareHealth(int newX, int newY)
	{
		
		int healthIncr = (int) (this.health * genes.getEnergyShareAmnt()); //Should always be positive
		
		//TODO: Rethink this rule
//		if(this.health <= healthIncr)
//			return null;
		
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
						
//						System.out.printf("Gave %d health!\n", healthIncr);
						
						return null;
					}
				}
			}
		}
		
		return null;
	}
	
	public PlantCell growAdjacent(int newX, int newY)
	{
		int sacrificeAmnt = (int) (this.health * genes.getEnergyGrowAmnt()); //Should always be positive
		
		//TODO: Rethink feature for cells not to end themselves
		if(this.health - sacrificeAmnt <= 0)
			return null;
		
		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < Environment.map.length && newY > 0 && newY < Environment.map[0].length) {
			if(Environment.map[newX][newY] == null)
			{
				//TODO: Check 'genes' addition
				newPlant = new PlantCell(myPlant, genes, this.x, this.y, newX, newY, sacrificeAmnt);
				
				Environment.map[newX][newY] = newPlant;
				
				this.health -= sacrificeAmnt; //Spawning a new plant reduces health
				return newPlant;
			}
		}
		
		return null;
	}
	
//	//Private functions
//	private int pickX(int chooseAction)
//	{		
//		//TODO: Change directionality to refer to parent cell
//		//Logic for deciding a direction
//		int xDiff = 0;
//		if(this.parentX < 0) {
//			xDiff = 1;
//		} else {
//			xDiff = (this.parentX - this.x);// / Math.abs(this.parentX - this.x);
//		}
//		
//		//Diffs are already oriented towards parent, if we want to grow new, we should go other way
//		if(chooseAction == 0) {
//			xDiff *= -1;
//		}
//	
//		return this.x + xDiff;
//	}
//	private int pickY(int chooseAction)
//	{		
//		//TODO: Change directionality to refer to parent cell
//		//Logic for deciding a direction
//		int yDiff = 0;
//		if(this.parentX < 0 || this.parentY < 0) {
//			yDiff = 0;
//		} else {
//			yDiff = (this.parentY - this.y);// / Math.abs(this.parentY - this.y);
//		}
//		
//		//Diffs are already oriented towards parent, if we want to grow new, we should go other way
//		if(chooseAction == 0) {
//			yDiff *= -1;
//		}
//		
//		return this.y + yDiff;
//	}
	
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

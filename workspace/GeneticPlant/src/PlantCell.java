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
	
	int plantCellType; //0- stem, 1-leaf, 2-flower
	
	
	public PlantCell(Plant plantPointer, GeneticInfo genes, int parentX, int parentY)
	{
		//TODO: Handle different plant cell cases here by copying previous genetics, then modifying this newer one
		
		
		//TODO: Handle generic cell creation
		
		health = 5; //TODO: Make random
		
		this.plantCellType = 0; //TODO: Default plant type, is this okay?
		this.genes = genes;
		this.myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
	}
	
	public PlantCell(Plant plantPointer, GeneticInfo genes, int plantType, int parentX, int parentY, int x, int y, int health)
	{
		this.x = x;
		this.y = y;
		plantPointer.getEnvironment().map[x][y] = this;
		
		this.health = health; //TODO: Make random
		
		this.genes = genes;
		this.plantCellType = plantType;
		this.myPlant = plantPointer;
		this.parentX = parentX;
		this.parentY = parentY;
		
		if(this.plantCellType == 0) {
			; //Do nothing
		}
		else if(this.plantCellType == 1) {
			this.health *= 0.8;
			this.genes.applyLeafModifiers();
		}
		else if(this.plantCellType == 2) {
			this.health *= 0.7;
			this.genes.applyFlowerModifiers();
		}
	}
	
	//Decodes genetics into specific action
	public PlantCell doAction()
	{
		//Will handle cells passively interacting with the environment
		interactWithEnvironment();
		
		//System.out.printf("cell (%d,%d) ", this.x, this.y);
		//0-grow, 1-share, 2-obstain
		int chooseAction = genes.chooseAction();
		
		//Get direction based on action
		int direction = -1;
		int newCellType = -1;
		if(chooseAction == 0) {
			direction = genes.chooseGrowDirection();
			newCellType = genes.chooseCellType();
//			System.out.print("Chose to grow cell: " + newCellType);
		}
		else if(chooseAction == 1) {
			direction = genes.chooseShareDirection();
			//System.out.print("Chose to share ");
		}
		else {
			//System.out.print("Chose to obstain ");
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
		//System.out.printf("into location: (%d,%d)\n", newX, newY);
		
		//Apply action with location
		if(chooseAction == 0) {
			return growAdjacent(newX, newY, newCellType);
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
		if(newX > 0 && newX < myPlant.getEnvironment().map.length && newY > 0 && newY < myPlant.getEnvironment().map[0].length) {
			if(myPlant.getEnvironment().map[newX][newY] != null)
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
	
	public PlantCell growAdjacent(int newX, int newY, int newCellType)
	{
		int sacrificeAmnt = (int) (this.health * genes.getEnergyGrowAmnt()); //Should always be positive
		
		//TODO: Rethink feature for cells not to end themselves
		if(this.health - sacrificeAmnt <= 0)
			return null;
		
		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < myPlant.getEnvironment().map.length && newY > 0 && newY < myPlant.getEnvironment().map[0].length) {
			if(myPlant.getEnvironment().map[newX][newY] == null)
			{				
				//TODO: Test copying genes, originally is a reference to original
				GeneticInfo newGenes = new GeneticInfo(genes);
				newPlant = new PlantCell(myPlant, newGenes, newCellType, this.x, this.y, newX, newY, sacrificeAmnt);
				
				myPlant.getEnvironment().map[newX][newY] = newPlant;
				
				this.health -= sacrificeAmnt; //Spawning a new plant reduces health
				return newPlant;
			}
		}
		
		return null;
	}
	
	private void interactWithEnvironment() {		
		//System.out.printf("\tCell (%d,%d) ", this.x, this.y);
		int sharedAmnt = Environment.getSun(this.x, this.y);
		//System.out.printf(" got %d health from the sun\n", sharedAmnt);
		
		Random rand = new Random();
		//System.out.printf("\tCell (%d,%d) ", this.x, this.y);
		int starvedAmnt = 5;
//		if(rand.nextInt(5) == 0)
		//System.out.printf(" starved %d health from the sun\n", starvedAmnt);
		
		//TODO: Add modifiers for leaves and flowers
		if(this.plantCellType == 0) {
			sharedAmnt *= 0.75;
			starvedAmnt *= 1;
		}
		else if(this.plantCellType == 1) {
			sharedAmnt *= 1.5;
			starvedAmnt *= 1.7;
		}
		else if(this.plantCellType == 2) {
			sharedAmnt *= 1.7;
			starvedAmnt *= 2.5;
		}
		
		int num = rand.nextInt(5);
		if(num != 0)
			this.health += sharedAmnt;
		this.health -= starvedAmnt;
	}
	
	//TODO: Remove self function
	public void removeSelf() {
		for(int i = 0; i < myPlant.listOfCells.size(); i++) {
			if(myPlant.listOfCells.get(i) == this)
			{
				myPlant.listOfCells.remove(i);
				return;
			}
		}
		
		System.out.println("Error! Could not remove self from list");
		throw new Error();
	}
	
	//Setters and getters
	public GeneticInfo getGenes() { return new GeneticInfo(this.genes); }
	public int getPlantType() { return this.plantCellType; }
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getHealth() { return this.health; }
	public int getParentX() { return this.parentX; }
	public int getParentY() { return this.parentY; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setHealth(int health) { this.health = health; }
}

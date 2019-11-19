import java.awt.Point;
import java.util.ArrayList;
import java.util.Random; 

public class PlantCell extends EnvObject{
	int x;
	int y;
	
	int health;
	
	Plant myPlant;
	
	//TODO: Add genetic ticket information here
	//	possibly meta data class? For easy genetic handling later?
	
	
	public PlantCell(Plant plantPointer)
	{
		//TODO: Handle generic cell creation
		
		health = 5; //TODO: Make random
		
		myPlant = plantPointer;
	}
	
	public PlantCell(Plant plantPointer, int x, int y)
	{
		this.x = x;
		this.y = y;
		
		health = 5; //TODO: Make random
		
		Environment.map[x][y] = this;
		myPlant = plantPointer;
	}
	
	//Actions
	//TODO: Implement random action - you can add arguments back to grow adjacent and pass health
	//	this function will probably be the one that handles genetic ticket handling
	public PlantCell doAction()
	{
		//TODO: Change directionality to refer to parent cell
		//Logic for deciding a direction
		Random rand = new Random();
//		int xDiff = -1 + rand.nextInt(2);
//		int yDiff = -1 + rand.nextInt(2);
		int newX = this.x;
		int newY = this.y;
		
		//This should probably be handled in another function
		int chosen_bucket = rand.nextInt(35);
		if(chosen_bucket > 0 && chosen_bucket <= 5)
			newY -= 1;
		if(chosen_bucket > 5 && chosen_bucket <= 10)
			newY += 1;
		if(chosen_bucket > 10 && chosen_bucket <= 30)
			newX -= 1;
		if(chosen_bucket > 30 && chosen_bucket <= 35)
			newX += 1;
		
		
		int chooseAction = rand.nextInt(2);
		if(chooseAction == 0)
			return growAdjacent(newX, newY);
		else if(chooseAction == 1)
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
		
		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < Environment.map.length && newY > 0 && newY < Environment.map[0].length) {
			if(Environment.map[newX][newY] != null)
			{
				//Code to ensure adjacent cell is part of this plant
				for(PlantCell cell: myPlant.getCellList())
				{
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

		PlantCell newPlant;
		// Make sure proposed spot is in bounds, then ensure that there isn't an entity there already
		if(newX > 0 && newX < Environment.map.length && newY > 0 && newY < Environment.map[0].length) {
			if(Environment.map[newX][newY] == null)
			{
				newPlant = new PlantCell(myPlant, newX, newY);
				
				Environment.map[newX][newY] = newPlant;
				
				this.health -= 2; //Spawning a new plant reduces health
				return newPlant;
			}
		}
		
		return null;
	}
	
	//Setters and getters
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getHealth() { return this.health; }
	
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setHealth(int health) { this.health = health; }
}

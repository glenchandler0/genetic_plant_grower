import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TODO: Checklist
// *- Pruning method
// - Fixing static Environment
// - Evaluation methods + metric
// - slideshow program
// - gene encoding and decoding
// - functions to find best genes

//TODO: What to tweak to create good evolution environment
//	- Time to evolve
//	- Starvation amount
//	- Sunlight health amount
//	- Evaluation metric

public class Main {
	public static void main(String[] args) throws IOException
	{
		System.out.println("Hello world!");
		
		//Choose main function here
		runApplication();
	}
	
	//---------- Potential main functions to run ---------- 
	private static void runApplication() throws IOException
	{		
		Environment e = new Environment();
		System.out.println(e.getMapString());
		
		int num_iterations = 20;
		for(int i = 0; i < num_iterations; i++)
		{
			e.iterate();
			e.pruneTrees();
			
			String mapString = e.getHealthString();
			System.out.println(mapString);
			
//			BufferedWriter writer = new BufferedWriter(new FileWriter("map_files/map" + i + ".csv"));
//		    writer.write(mapString);
//		    writer.close();
		}
		
		System.out.printf("Total cells: %.2f\n", totalCells(e));
		System.out.printf("Total cell health: %.2f\n", totalHealth(e));
	}
	
	private static void testGeneticTicketSystem() {
		GeneticInfo gi = new GeneticInfo();
		
		int growDirection = gi.chooseShareDirection();
		System.out.printf("Chosen action: %d\n",  growDirection);
	}
	
	//--------- Meta testing functions ---------
	private static double totalHealth(Environment e) {
		//Just count all cells
		double count = 0;
		
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null)
					count += ((PlantCell)e.map[i][j]).getHealth();
			}
		}
		
		return count;
	}
	private static double totalCells(Environment e) {
		//Just count all cells
		double count = 0;
		
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null)
					count++;
			}
		}
		
		return count;
	}
	
	//TODO: Metric that reflects plant-like structures?
	//	-Maybe high ratio of #cells : total health??
	private static double evaluateEnvironment(Environment e) {
		return -1;
	}
	
	//TODO: Function to get best iteration of a given gene
	//	-maybe return encoded gene?
	
	//TODO: Function to iterate over multiple versions of a gene
}

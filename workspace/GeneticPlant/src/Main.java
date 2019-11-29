import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TODO: Checklist
// *- Pruning method
// - Fixing static Environment
// - Evaluation methods

public class Main {
	public static void main(String[] args) throws IOException
	{
		System.out.println("Hello world!");
		
		//Choose main function here
		runApplication();
	}
	
	//---------- Potential main functions to run ---------- 
	private static void runApplication()
	{		
		Environment e = new Environment();
		System.out.println(e.getMapString());
		
		int num_iterations = 30;
		for(int i = 0; i < num_iterations; i++)
		{
			e.iterate();
			e.pruneTrees();
			
			String mapString = e.getMapString();
			System.out.println(mapString);
			
//			BufferedWriter writer = new BufferedWriter(new FileWriter("map_files/map" + i + ".csv"));
//		    writer.write(mapString);
//		    writer.close();
		}
	}
	
	private static void testGeneticTicketSystem() {
		GeneticInfo gi = new GeneticInfo();
		
		int growDirection = gi.chooseShareDirection();
		System.out.printf("Chosen action: %d\n",  growDirection);
	}
	
	//--------- Meta testing functions
	//TODO: Overall evaluation method
	//	-total number of cells
	private double evaluateEnvironment() {
		
		//Just count all cells
		for(int i = 0; i < e.map.length; i++) {
			
		}
	}
	
	//TODO: Function to get best iteration of a given gene
	
	//TODO: Function to iterate over multiple versions of a gene
}

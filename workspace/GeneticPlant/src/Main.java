import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
		
		int num_iterations = 25;
		for(int i = 0; i < num_iterations; i++)
		{
			e.iterate();
			
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
}

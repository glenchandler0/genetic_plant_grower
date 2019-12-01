import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TODO: Checklist
// *- Pruning method
// *- Fixing static Environment
// *- Evaluation methods + metric
// *- slideshow program
// - 
//
// !- gene encoding and decoding
// !- private class for environment run - extracting info
//		-Just use the environment class
// *- functions to find best genes
//		-Randomly
//		-w/ genetic algorithm

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
		findBestRandomGene();
//		printBestPlant();
//		runApplicationOld();
	}
	
	//---------- Potential main functions to run ----------
	private static void findBestRandomGene() {
		StringBuilder sb = new StringBuilder();
		double bestScore = bestGeneRandom(1000, 100, 10, sb);
		
		System.out.print(sb.toString());
		System.out.println("Best Score: " + bestScore);
	}
	
	private static void printBestPlant() throws IOException {
		GeneticInfo gi = new GeneticInfo();
		
		StringBuilder bestMap = new StringBuilder();
		double bestScore = geneBestScore(gi, 100, 20, bestMap);
		System.out.print(bestMap.toString());
		System.out.println("Best Score: " + bestScore);
	}
	
	private static void runApplicationOld() throws IOException
	{		
		GeneticInfo gi = new GeneticInfo();
		Environment e = new Environment(gi);
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
		System.out.printf("Env Evaluation: %.4f\n", evaluateEnvironment(e));
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
		double numCells = totalCells(e);
		double totalHealthAdj = totalHealth(e);
		
		return totalHealthAdj + numCells/totalHealthAdj;
//		return 1/(Math.pow((numCells - totalHealthAdj + 1), 2));
//		return numCells;
	}
	
	//------------- Meta testing ----------------
	private static double runEnvironment(Environment e, int numIterations) {
//		System.out.println(e.getMapString());
		
		for(int i = 0; i < numIterations; i++)
		{
			e.iterate();
			e.pruneTrees();
			
//			String mapString = e.getHealthString();
//			System.out.println(mapString);
			
//			BufferedWriter writer = new BufferedWriter(new FileWriter("map_files/map" + i + ".csv"));
//		    writer.write(mapString);
//		    writer.close();
		}
		
//		System.out.print(e.getHealthString());
//		System.out.printf("Total cells: %.2f\n", totalCells(e));
//		System.out.printf("Total cell health: %.2f\n", totalHealth(e));
//		System.out.printf("Env Evaluation: %.4f\n", evaluateEnvironment(e));
		
		return evaluateEnvironment(e);
	}
	
	//TODO: Function to get best iteration of a given gene
	private static double geneBestScore(GeneticInfo gi, int numTrials, int numIterations, StringBuilder bestMapString) {
		
		double bestScore = 0;
		String bestMap = "no map chosen!";
		for(int i = 0; i < numTrials; i++) {
			Environment e = new Environment(gi);
			
			double score = runEnvironment(e, numIterations);
			if(score > bestScore) {
				bestScore = score;
				bestMap = e.getHealthString();
			}
		}
		
		bestMapString.append(bestMap);
		
		return bestScore;
	}
	
	//TODO: Function to iterate over multiple versions of a gene
	private static double bestGeneRandom(int numGenes, int numTrials, int numIterations, StringBuilder bestMap) {
		GeneticInfo gi = new GeneticInfo();
		
		double bestScore = 0;
		String bestMapString = "no map chosen!";
		for(int i = 0; i < numGenes; i++) {
			gi.randomizeGenes();
			
			StringBuilder mapRes = new StringBuilder();
			double score = geneBestScore(gi, numTrials, numIterations, mapRes);
			
			if(score > bestScore) {
				bestScore = score;
				bestMapString = mapRes.toString();
			}
		}
		
		bestMap.append(bestMapString);
		
		return bestScore;
	}
}

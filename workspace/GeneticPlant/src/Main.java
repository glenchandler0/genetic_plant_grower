import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//TODO: Checklist
// *- Pruning method
// *- Fixing static Environment
// *- Evaluation methods + metric
// *- slideshow program
// - 
//
// Adding different types of plants
// *- give children a copy of your genes
// *- when giving certain type of new plant, modify child genetics
// ~- change starvation and sun absorption
// *- ensure random selection of type of plant
// - stems can only absorb a small amount of sun
//
// !- gene encoding and decoding
// *- private class for environment run - extracting info
//		*-Just use the environment class
// *- functions to find best genes
//		*-Randomly
//		-w/ genetic algorithm

//TODO: What to tweak to create good evolution environment
//	- Time to evolve
//	- Starvation amount
//	- Sunlight health amount
//	- Evaluation metric
//	- "cloudy day" mechanic where sun turns off

public class Main {
	public static void main(String[] args) throws IOException
	{
		System.out.println("Hello world!");
		
		//Choose main function here
//		findBestRandomGene(true);
		
		GeneticInfo gi = new GeneticInfo();
//		gi.randomizeGenes();
		printBestPlant(gi, true);
		
//		runApplicationOld();
	}
	
	//---------- Potential main functions to run ----------
	private static void findBestRandomGene(boolean trackPlant) throws IOException {
		Environment bestEnv = bestGeneRandom(500, 50, 20, trackPlant);
		double bestScore = evaluateEnvironment(bestEnv);
		
		PlantCell sampleCell = bestEnv.getSamplePlantCell();
		if(sampleCell != null)
			System.out.print(sampleCell.getGenes().printGeneticInfo());
		System.out.print(bestEnv.getHealthString());
		System.out.println("Best Score: " + bestScore);
	}
	
	private static void printBestPlant(GeneticInfo gi, boolean trackPlant) throws IOException {		
		Environment bestEnv = geneBestScore(gi, 1000, 20, trackPlant);
		double bestScore = evaluateEnvironment(bestEnv);

		PlantCell sampleCell = bestEnv.getSamplePlantCell();
		if(sampleCell != null)
			System.out.print(sampleCell.getGenes().printGeneticInfo());
		System.out.print(bestEnv.getHealthString());
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
	//	-Maybe biodiversity score?
	private static double evaluateEnvironment(Environment e) {
		double numCells = totalCells(e);
		double totalHealthAdj = totalHealth(e);
		
		return totalHealthAdj + numCells/totalHealthAdj;
//		return 1/(Math.pow((numCells - totalHealthAdj + 1), 2));
//		return numCells;
	}
	
	//------------- Meta testing ----------------
	
	private static double runEnvironment(Environment e, int numIterations, boolean trackPlant) throws IOException {
//		System.out.println(e.getMapString());
		
		for(int i = 0; i < numIterations; i++)
		{
			e.iterate();
			e.pruneTrees();
			
//			System.out.println(mapString);
			
			if(trackPlant == true) {
				String mapString = e.getHealthString();
				BufferedWriter writer = new BufferedWriter(new FileWriter("map_files/map" + i + ".csv"));
				writer.flush();
			    writer.write(mapString);
			    writer.close();
			}
		}
		
//		System.out.print(e.getHealthString());
//		System.out.printf("Total cells: %.2f\n", totalCells(e));
//		System.out.printf("Total cell health: %.2f\n", totalHealth(e));
//		System.out.printf("Env Evaluation: %.4f\n", evaluateEnvironment(e));
		
		return evaluateEnvironment(e);
	}
	
	//Function to get best iteration of a given gene
	private static Environment geneBestScore(GeneticInfo gi, int numTrials, int numIterations, /*StringBuilder bestMapString,*/ boolean trackPlant) throws IOException {
		
		Environment bestEnv = null;
		double bestScore = 0;
		for(int i = 0; i < numTrials; i++) {
			Environment e = new Environment(gi);
			
			double score = runEnvironment(e, numIterations, trackPlant);
			
			if(score > bestScore) {
				bestScore = score;
				bestEnv = e;
				
				if(trackPlant) {
					runMarkBestFiles(numIterations);
				}
			}
		}
		
		return bestEnv;
	}
	
	//Function to iterate over multiple versions of a gene
	//TODO: Genetic cross-over functionality
	private static Environment bestGeneRandom(int numGenes, int numTrials, int numIterations, /*StringBuilder bestMap,*/ boolean trackPlant) throws IOException {
		GeneticInfo gi = new GeneticInfo();
		
		Environment bestEnv = null;
		double bestScore = 0;
		for(int i = 0; i < numGenes; i++) {
			gi.randomizeGenes();
			
			Environment currEnv = geneBestScore(gi, numTrials, numIterations, trackPlant);
			double score = evaluateEnvironment(currEnv);
			
			if(score > bestScore) {
				bestScore = score;
				bestEnv = currEnv;
				
				if(trackPlant) {
					geneMarkBestFiles(numIterations);
				}
			}
		}
		
		return bestEnv;
	}
	
	// ------------------------ RENAME FUNCTIONS ------------------------------
	private static void runMarkBestFiles(int numIterations) {
		
		for(int i = 0; i < numIterations; i++) {
			File fOld = new File("map_files/map" + i + ".csv");
			File fNew = new File("map_files/bestRunMap" + i + ".csv");
			
			fOld.renameTo(fNew);
		}
	}
	private static void geneMarkBestFiles(int numIterations) {
		
		for(int i = 0; i < numIterations; i++) {
			File fOld = new File("map_files/bestRunMap" + i + ".csv");
			File fNew = new File("map_files/bestGeneMap" + i + ".csv");
			
			fOld.renameTo(fNew);
		}
	}
}

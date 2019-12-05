import java.util.ArrayList;

public class EnvStats {
	public double numTrials;
	public double avgScore;
	public double highestScore;
	public double lowestScore;
	
	
	
	public EnvStats() {
		numTrials = 0;
		avgScore = 0;
		highestScore = Integer.MIN_VALUE;
		lowestScore = Integer.MAX_VALUE;
	}
	
//	public void processDataPoint(Environment e) {
//		double score = 0;//evaluateEnvironment(e);
//		
//		numTrials++;
//		
//		if(score > highestScore)
//			highestScore = score;
//		if(score < lowestScore)
//			lowestScore = score;
//		avgScore = (avgScore + score) / numTrials;
//	}
//	
	
	//--------- Meta testing functions ---------
	//TODO: Check accuracy
	public static double height(Environment e) {
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null)
					return e.map.length - i;
			}
		}
		
		return 0;
	}
	
	public static double countStem(Environment e) {
		double countStem = 0;
		
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null) {
					PlantCell pc = (PlantCell) e.map[i][j];
					if(pc.getPlantType() == 0)
						countStem++;
				}
			}
		}
		
		return countStem;
	}
	public static double countLeaf(Environment e) {
		double countLeaf = 0;
		
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null) {
					PlantCell pc = (PlantCell) e.map[i][j];
					if(pc.getPlantType() == 1)
						countLeaf++;
				}
			}
		}
		
		return countLeaf;
	}
	public static double countFlower(Environment e) {
		double countFlower = 0;
		
		for(int i = 0; i < e.map.length; i++) {
			for(int j = 0; j < e.map[i].length; j++) {
				if(e.map[i][j] != null) {
					PlantCell pc = (PlantCell) e.map[i][j];
					if(pc.getPlantType() == 2)
						countFlower++;
				}
			}
		}
		
		return countFlower;
	}
	
	public static double totalHealth(Environment e) {
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
	public static double totalCells(Environment e) {
		
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
	
	public static void breakDownSeries(ArrayList<Double> list) {
		double sum = 0;
		double numPts = 0;
		double min = Integer.MAX_VALUE;
		double max = Integer.MIN_VALUE;
		double stdDev = 0;
		double avg = 0;
		
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			numPts++;
			
			if(list.get(i) > max)
				max = list.get(i);
			if(list.get(i) < min) 
				min = list.get(i);
		}
		
		avg = sum / numPts;
		
		for(int i = 0; i < list.size(); i++) {
			stdDev += Math.pow((list.get(i) - avg), 2);
		}
		stdDev /= (numPts-1);
		stdDev = Math.sqrt(stdDev);
		
		System.out.printf("NumPts: %.2f, sum: %.2f, min: %.2f, max: %.2f, avg: %.2f, stdDev: %.2f\n", numPts, sum, min, max, avg, stdDev);
	}
}

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
	public EnvObject[][] map; //TODO: Change to more generic object
	public static ArrayList<EnvEntity> entityList;
	
	static int sizeX;
	static int sizeY;
	
	static int sunX;
	static int sunY;
	
	public Environment(GeneticInfo gi)
	{
		sizeX = 25;
		sizeY = 25;
		sunX = 0;
		sunY = 0;
		
		map = new EnvObject[sizeX][sizeY];
		entityList = new ArrayList<>();
		
//		GeneticInfo gi = new GeneticInfo();
//		System.out.println(gi.printGeneticInfo());
		
		Plant plant0 = new Plant(gi, this);
		entityList.add(plant0);
	}
	
	// Decides what will happen at every timestep to meta objects
	public void iterate()
	{
		for(EnvEntity entity: entityList)
			entity.step();
		
//		Plant p = (Plant) entityList.get(0);
//		p.printCellsHealth();
	}
	
	//------- Environment interaction functions --------
	
	//TODO: Tree pruning function here
	//	- BFS, mark everything you find, ?start where?
	//	- O(n^2) find all things you want to keep
	//	- set null anything not marked
	
	private void pruneHelper(int x, int y, char[][] tempMap) {
		tempMap[x][y] = '+';
		
		//TODO: BFS logic starting at (x,y) mark all cells found with '+'
		if(x-1 > 0 && map[x-1][y] != null && tempMap[x-1][y] == '-')
			pruneHelper(x-1,y,tempMap);
		if(y-1 > 0 && map[x][y-1] != null && tempMap[x][y-1] == '-')
			pruneHelper(x,y-1,tempMap);
		if(x+1 < sizeX && map[x+1][y] != null && tempMap[x+1][y] == '-')
			pruneHelper(x+1,y,tempMap);
		if(y+1 < sizeY && map[x][y+1] != null && tempMap[x][y+1] == '-')
			pruneHelper(x,y+1,tempMap);
		
		return;		
	}
	public void pruneTrees() {
		char[][] tempMap = new char[sizeX][sizeY];
		
		//Intialize map as all null marks
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				tempMap[i][j] = '-';
			}
		}
		
		//Scrape bottom of map and start BFS from roots
		for(int i = 0; i < sizeY; i++) {
			if(map[sizeX-1][i] != null)
				pruneHelper(sizeX-1, i, tempMap);
		}
		
		//TODO: Look for other objects
		
		//Remove cell from plant list and from map
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(tempMap[i][j] == '-' && map[i][j] != null)
				{
					((PlantCell)map[i][j]).removeSelf();
					map[i][j] = null;
				}
			}
		}
		
		//Print resulting map after BFS
//		for(int i = 0; i < tempMap.length; i++)
//		{
//			for(int j = 0; j < tempMap[i].length; j++)
//			{
//				System.out.print(tempMap[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
	
	public static int getSun(int x, int y) {
		final double maxGain = 20;
		
		double dist = Math.sqrt(Math.pow((x - sunX),2) + Math.pow((y - sunY), 2));
		final double maxDist = Math.sqrt(Math.pow((sizeX),2) + Math.pow((sizeY), 2));
		
		int sunShareAmnt = (int)(maxGain - (maxGain * (Math.pow((dist / maxDist),2))));
		
		return sunShareAmnt;
		
	}
	
	//------- ETC functions -------
	public String getMapString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if(map[i][j] == null)
					sb.append(" - ");
				else
					sb.append(" # ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	//TODO: Test this instead of typical map
	public String getHealthString()
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				if(map[i][j] == null)
					sb.append(" ---- ");
				else {
					int entityHealth;
					entityHealth = ((PlantCell)map[i][j]).getHealth();
					sb.append(String.format(" %3d# ", entityHealth));
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

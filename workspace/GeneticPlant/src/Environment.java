import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Environment {
	public static EnvObject[][] map; //TODO: Change to more generic object
	public static ArrayList<EnvEntity> entityList;
	
	
	public Environment()
	{
		map = new EnvObject[25][25];
		entityList = new ArrayList<>();
		
		Plant plant0 = new Plant();
		entityList.add(plant0);
	}
	
	// Decides what will happen at every timestep to meta objects
	public static void iterate()
	{
		for(EnvEntity entity: entityList)
			entity.step();
		
		Plant p = (Plant) entityList.get(0);
		p.printCellsHealth();
	}
	
	
	//------- ETC functions -------
	public static String getMapString()
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
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

//TODO: Remove parent relativity
public class GeneticInfo 
{	
	//Used for calculating distribution of direction
	//for growing a new cell
	int leftGrowTickets;
	int rightGrowTickets;
	int upGrowTickets;
	int downGrowTickets;
	int xParentGrowTickets;
	int xAwayParentGrowTickets;
	int yParentGrowTickets;
	int yAwayParentGrowTickets;
	
	//Used for caclulating distribution of direction
	//for sharing energy to adjacent cell
	int leftShareTickets;
	int rightShareTickets;
	int upShareTickets;
	int downShareTickets;
	int xParentShareTickets;
	int xAwayParentShareTickets;
	int yParentShareTickets;
	int yAwayParentShareTickets;
	
	//How much energy would be shared to adjacent cell
	double energyShareAmnt;
	//How much energy would be given to newly grown cell
	double energyGrowAmnt;
	
	//Used for determining what action to take
	int growTickets;
	int shareTickets;
	int obstainTickets;

	int stemTickets;
	int leafTickets;
	int flowerTickets;
	
	//----- Constructors ----
//	public GeneticInfo(ArrayList<Integer> distributions) {
//		
//	}
	
	//By default gives all tickets even distribution
	public GeneticInfo() {
		setOptimalGenes();
	}
	
	public void setControlGenes() {
		leftGrowTickets = 50;
		rightGrowTickets = 50;
		upGrowTickets = 50;
		downGrowTickets = 50;
		xParentGrowTickets = 50;
		xAwayParentGrowTickets = 50; //TODO: Remove
		yParentGrowTickets = 50;
		yAwayParentGrowTickets = 50; //TODO: Remove
		
		leftShareTickets = 50;
		rightShareTickets = 50;
		upShareTickets = 50;
		downShareTickets = 50;
		xParentShareTickets = 50;
		xAwayParentShareTickets = 50; //TODO: Remove
		yParentShareTickets = 50;
		yAwayParentShareTickets = 50; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = 0.50;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = 0.50;
		
		growTickets = 50;
		shareTickets = 50;
		obstainTickets = 50;
		
		stemTickets = 50;
		leafTickets = 50;
		flowerTickets= 50;
	}
	
	//----- Functionality ------
	public void randomizeGenes() {
		Random rand = new Random();
		
		leftGrowTickets = rand.nextInt(200);
		rightGrowTickets = rand.nextInt(200);
		upGrowTickets = rand.nextInt(200);
		downGrowTickets = rand.nextInt(200);
		xParentGrowTickets = rand.nextInt(200);
		xAwayParentGrowTickets = rand.nextInt(200); //TODO: Remove
		yParentGrowTickets = rand.nextInt(200);
		yAwayParentGrowTickets = rand.nextInt(200); //TODO: Remove
		
		leftShareTickets = rand.nextInt(200);
		rightShareTickets = rand.nextInt(200);
		upShareTickets = rand.nextInt(200);
		downShareTickets = rand.nextInt(200);
		xParentShareTickets = rand.nextInt(200);
		xAwayParentShareTickets = rand.nextInt(200); //TODO: Remove
		yParentShareTickets = rand.nextInt(200);
		yAwayParentShareTickets = rand.nextInt(200); //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = rand.nextDouble();
		//How much energy would be given to newly grown cell
		energyGrowAmnt = rand.nextDouble();
		
		growTickets = rand.nextInt(200);
		shareTickets = rand.nextInt(200);
		obstainTickets = rand.nextInt(200);
		
		stemTickets = rand.nextInt(200);
		leafTickets = rand.nextInt(200);
		flowerTickets= rand.nextInt(200);
	}
	
	public void setOptimalGenes() {
		leftGrowTickets = 50;
		rightGrowTickets = 50;
		upGrowTickets = 200;
		downGrowTickets = 10;
		xParentGrowTickets = 10;
		xAwayParentGrowTickets = 50; //TODO: Remove
		yParentGrowTickets = 10;
		yAwayParentGrowTickets = 100; //TODO: Remove
		
		leftShareTickets = 50;
		rightShareTickets = 50;
		upShareTickets = 10;
		downShareTickets = 100;
		xParentShareTickets = 100;
		xAwayParentShareTickets = 10; //TODO: Remove
		yParentShareTickets = 100;
		yAwayParentShareTickets = 10; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = 0.1;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = 0.25;
		
		growTickets = 50;
		shareTickets = 100;
		obstainTickets = 100;
		
		stemTickets = 50;
		leafTickets = 50;
		flowerTickets= 50;
	}
	
	//To be used in collection sort
	private class Ticket {
		private int id; //left, right, up, down
		private int numTickets;
		
		public Ticket(int direction, int numTickets) {
			this.id = direction;
			this.numTickets = numTickets;
		}
		
		public int getNumTickets() {
			return numTickets;
		}
		public int getId() {
			return id;
		}
	}
	//Abstractable function which takes a list of tickets (4 for now) and returns which ticket was chosen
	private int chooseTicket(ArrayList<Integer> ticketList)//int ticket0, int ticket1, int ticket2, int ticket3)
	{
		//Create bucketed list relating to tickets
		ArrayList<Ticket> tickets = new ArrayList<>();
		int prev = 0;
		for(int i = 0; i < ticketList.size(); i++) {
			tickets.add(new Ticket(i, prev + ticketList.get(i)));
			prev = tickets.get(i).getNumTickets();
		}
		
		//Sort list of tickets to create buckets
		Collections.sort(tickets, new Comparator<Ticket>() {
	        public int compare(Ticket o1, Ticket o2) {
	            // compare two instance of `Score` and return `int` as result.
	            if(o1.getNumTickets() < o2.getNumTickets()) return -1;
	            else if(o1.getNumTickets() > o2.getNumTickets()) return 1;
	            else return 0;
	        }
	    });
		
		//Random ticket number is somewhere between 0 and maxNumTickets
		int maxTickets = tickets.get(tickets.size() - 1).getNumTickets();
		Random rand = new Random();
		int chosenTicket = rand.nextInt(maxTickets);
		
//		//Print helpful stats
//		System.out.println("Max tickets: " + maxTickets);
//		System.out.println("Sorted list: ");
//		for(int i = 0; i < tickets.size(); i++)
//			System.out.printf("(%d, %d)\t", tickets.get(i).getId(), tickets.get(i).getNumTickets());
//		System.out.println();
//		System.out.println("Chosen ticket: " + chosenTicket);
		
		//Find which bucket ticket lands in
		for(int i = 0; i < tickets.size(); i++) {
			if(chosenTicket < tickets.get(i).getNumTickets())
				return tickets.get(i).getId();
		}
		
		System.out.println("ticket selector ERROR");
		return -1; //Error, could not select ticket
	}
	
	
	
	
	
	// ------------ Functions for interpreting genetics ----------
	public int chooseAction() {
		//Since chooseTicket takes 4 arguments
		ArrayList<Integer> ticketList = new ArrayList<Integer>();
		ticketList.add(growTickets);
		ticketList.add(shareTickets);
		ticketList.add(obstainTickets);
		
		return chooseTicket(ticketList);
	}
	
	public int chooseGrowDirection() {		
		ArrayList<Integer> ticketList = new ArrayList<Integer>();
		ticketList.add(leftGrowTickets);
		ticketList.add(rightGrowTickets);
		ticketList.add(upGrowTickets);
		ticketList.add(downGrowTickets);
		ticketList.add(xParentGrowTickets);
		ticketList.add(xAwayParentGrowTickets); //TODO: Remove
		ticketList.add(yParentGrowTickets);
		ticketList.add(yAwayParentGrowTickets); //TODO: Remove
		
		return chooseTicket(ticketList);
	}
	
	public int chooseShareDirection() {		
		ArrayList<Integer> ticketList = new ArrayList<Integer>();
		ticketList.add(leftShareTickets);
		ticketList.add(rightShareTickets);
		ticketList.add(upShareTickets);
		ticketList.add(downShareTickets);
		ticketList.add(xParentShareTickets);
		ticketList.add(xAwayParentShareTickets); //TODO: Remove
		ticketList.add(yParentShareTickets);
		ticketList.add(yAwayParentShareTickets); //TODO: Remove
		
		return chooseTicket(ticketList);
	}
	
	// -------- House Keeping functions --------
	public String printGeneticInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("leftGrowTickets:\t" + leftGrowTickets + "\n");
		sb.append("rightGrowTickets:\t" + rightGrowTickets + "\n");
		sb.append("upGrowTickets:\t\t" + upGrowTickets + "\n");
		sb.append("downGrowTickets:\t" + downGrowTickets + "\n");
		
		sb.append("leftShareTickets:\t" + leftShareTickets + "\n");
		sb.append("rightShareTickets:\t" + rightShareTickets + "\n");
		sb.append("upGrowTickets:\t\t" + upGrowTickets + "\n");
		sb.append("downGrowTickets:\t" + downShareTickets + "\n");
		
		sb.append("energyGrowAmnt:\t\t" + energyGrowAmnt + "\n");
		sb.append("energyShareAmnt:\t" + energyShareAmnt + "\n");
		
		sb.append("growTickets:\t\t" + growTickets + "\n");
		sb.append("shareTickets:\t\t" + shareTickets + "\n");
		sb.append("obstainTickets:\t\t" + obstainTickets + "\n");
		
		sb.append("stemTickets:\t\t" + stemTickets + "\n");
		sb.append("leafTickets:\t\t" + leafTickets + "\n");
		sb.append("flowerTickets:\t\t" + flowerTickets + "\n");
		
		return sb.toString();
	}
	
	public double getEnergyShareAmnt() {
		return this.energyShareAmnt;
	}
	
	public double getEnergyGrowAmnt() {
		return this.energyGrowAmnt;
	}
}
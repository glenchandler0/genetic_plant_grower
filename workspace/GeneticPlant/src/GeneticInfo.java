import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

//TODO: Remove parent relativity
public class GeneticInfo 
{	
	static int minLimit = 0;
	static int maxLimit = 200;
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
	
	//Copies an existing genetic strand
	public GeneticInfo(GeneticInfo gi) {
		leftGrowTickets = gi.leftGrowTickets;
		rightGrowTickets = gi.rightGrowTickets;
		upGrowTickets = gi.upGrowTickets;
		downGrowTickets = gi.downGrowTickets;
		xParentGrowTickets = gi.xParentGrowTickets;
		xAwayParentGrowTickets = gi.xAwayParentGrowTickets; //TODO: Remove
		yParentGrowTickets = gi.yParentGrowTickets;
		yAwayParentGrowTickets = gi.yAwayParentGrowTickets; //TODO: Remove
		
		leftShareTickets = gi.leftShareTickets;
		rightShareTickets = gi.rightShareTickets;
		upShareTickets = gi.upShareTickets;
		downShareTickets = gi.downShareTickets;
		xParentShareTickets = gi.xParentShareTickets;
		xAwayParentShareTickets = gi.xAwayParentShareTickets; //TODO: Remove
		yParentShareTickets = gi.yParentShareTickets;
		yAwayParentShareTickets = gi.yAwayParentShareTickets; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = gi.energyShareAmnt;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = gi.energyGrowAmnt;
		
		growTickets = gi.growTickets;
		growTickets = gi.growTickets;
		obstainTickets = gi.obstainTickets;
		
		stemTickets = gi.stemTickets;
		leafTickets = gi.leafTickets;
		flowerTickets = gi.flowerTickets;
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
		
		leftGrowTickets = rand.nextInt(maxLimit);
		rightGrowTickets = rand.nextInt(maxLimit);
		upGrowTickets = rand.nextInt(maxLimit);
		downGrowTickets = rand.nextInt(maxLimit);
		xParentGrowTickets = rand.nextInt(maxLimit);
		xAwayParentGrowTickets = rand.nextInt(maxLimit); //TODO: Remove
		yParentGrowTickets = rand.nextInt(maxLimit);
		yAwayParentGrowTickets = rand.nextInt(maxLimit); //TODO: Remove
		
		leftShareTickets = rand.nextInt(maxLimit);
		rightShareTickets = rand.nextInt(maxLimit);
		upShareTickets = rand.nextInt(maxLimit);
		downShareTickets = rand.nextInt(maxLimit);
		xParentShareTickets = rand.nextInt(maxLimit);
		xAwayParentShareTickets = rand.nextInt(maxLimit); //TODO: Remove
		yParentShareTickets = rand.nextInt(maxLimit);
		yAwayParentShareTickets = rand.nextInt(maxLimit); //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = rand.nextDouble();
		//How much energy would be given to newly grown cell
		energyGrowAmnt = rand.nextDouble();
		
		growTickets = rand.nextInt(maxLimit);
		shareTickets = rand.nextInt(maxLimit);
		obstainTickets = rand.nextInt(maxLimit);
		
		stemTickets = rand.nextInt(maxLimit);
		leafTickets = rand.nextInt(maxLimit);
		flowerTickets= rand.nextInt(maxLimit);
	}
	
	public void setOptimalGenes() {
		leftGrowTickets = 50;
		rightGrowTickets = 50;
		upGrowTickets = 200;
		downGrowTickets = 10;
		//
		xParentGrowTickets = 5;
		xAwayParentGrowTickets = 75; //TODO: Remove
		yParentGrowTickets = 5;
		yAwayParentGrowTickets = 100; //TODO: Remove
		
		leftShareTickets = 50;
		rightShareTickets = 50;
		upShareTickets = 10;
		downShareTickets = 75;
		//
		xParentShareTickets = 100;
		xAwayParentShareTickets = 10; //TODO: Remove
		yParentShareTickets = 100;
		yAwayParentShareTickets = 25; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = 0.20;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = 0.25;
		
		growTickets = 100;
		shareTickets = 25;
		obstainTickets = 50;
		
		stemTickets = 20;
		leafTickets = 50;
		flowerTickets= 65;
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
		if(maxTickets < 1)
			maxTickets = 1;
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
		
		//If bucket searching fails, look for exact matches
		for(int i = 0; i < tickets.size(); i++) {
			if(chosenTicket == tickets.get(i).getNumTickets())
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
		
		int chosenTicket = chooseTicket(ticketList);
		if(chosenTicket < 0) {
			System.out.println("Error choosing ticket in chooseAction!");
		}
		return chosenTicket;
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
		
		int chosenTicket = chooseTicket(ticketList);
		if(chosenTicket < 0) {
			System.out.println("Error choosing ticket in chooseAction!");
		}
		return chosenTicket;
	}
	
	public int chooseCellType() {
		//Since chooseTicket takes 4 arguments
		ArrayList<Integer> ticketList = new ArrayList<Integer>();
		ticketList.add(stemTickets);
		ticketList.add(leafTickets);
		ticketList.add(flowerTickets);
		
		int chosenTicket = chooseTicket(ticketList);
		if(chosenTicket < 0) {
			System.out.println("Error choosing ticket in chooseAction!");
		}
		return chosenTicket;
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
		
		int chosenTicket = chooseTicket(ticketList);
		if(chosenTicket < 0) {
			System.out.println("Error choosing ticket in chooseAction!");
		}
		return chosenTicket;
	}
	
// -------- House Keeping functions --------
	public String printGeneticInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("leftGrowTickets:\t" + leftGrowTickets + "\n");
		sb.append("rightGrowTickets:\t" + rightGrowTickets + "\n");
		sb.append("upGrowTickets:\t\t" + upGrowTickets + "\n");
		sb.append("downGrowTickets:\t" + downGrowTickets + "\n\n");
		
		sb.append("xParentGrowTickets:\t" + xParentGrowTickets + "\n");
		sb.append("xAwayParentGrowTickets:\t" + xAwayParentGrowTickets + "\n");
		sb.append("yParentGrowTickets:\t\t" + yParentGrowTickets + "\n");
		sb.append("yAwayParentGrowTickets:\t" + yAwayParentGrowTickets + "\n\n");
		
		sb.append("leftShareTickets:\t" + leftShareTickets + "\n");
		sb.append("rightShareTickets:\t" + rightShareTickets + "\n");
		sb.append("upShareTickets:\t\t" + upGrowTickets + "\n");
		sb.append("downShareTickets:\t" + downShareTickets + "\n\n");
		
		sb.append("xParentShareTickets:\t" + xParentShareTickets + "\n");
		sb.append("xAwayParentShareTickets:\t" + xAwayParentShareTickets + "\n");
		sb.append("yParentShareTickets:\t\t" + yParentShareTickets + "\n");
		sb.append("yAwayParentShareTickets:\t" + yAwayParentShareTickets + "\n\n");
		
		sb.append("energyGrowAmnt:\t\t" + energyGrowAmnt + "\n");
		sb.append("energyShareAmnt:\t" + energyShareAmnt + "\n\n");
		
		sb.append("growTickets:\t\t" + growTickets + "\n");
		sb.append("shareTickets:\t\t" + shareTickets + "\n");
		sb.append("obstainTickets:\t\t" + obstainTickets + "\n\n");
		
		sb.append("stemTickets:\t\t" + stemTickets + "\n");
		sb.append("leafTickets:\t\t" + leafTickets + "\n");
		sb.append("flowerTickets:\t\t" + flowerTickets + "\n\n");
		
		return sb.toString();
	}
	
	public double getEnergyShareAmnt() {
		return this.energyShareAmnt;
	}
	
	public double getEnergyGrowAmnt() {
		return this.energyGrowAmnt;
	}
	
//------- Modification functions -------
	public void applyLeafModifiers() {
		if(this.growTickets/1.2 >= minLimit)
			this.growTickets /= 1.2;
		if(this.shareTickets*1.2 < maxLimit) {
			this.shareTickets *= 1.2;
		}
		if(this.obstainTickets*1.2 < maxLimit) {
			this.obstainTickets *= 1.2;
		}
		this.stemTickets = 0;
		this.leafTickets = 10;
		this.flowerTickets = 0;
	}
	public void applyFlowerModifiers() {
		if(this.growTickets / 2 >= minLimit)
			this.growTickets /= 2;
		if(this.shareTickets * 1.3 < maxLimit) {
			this.shareTickets *= 1.3;
		}
		if(this.obstainTickets / 1.3 < maxLimit) {
			this.obstainTickets /= 1.3;
		}
		this.stemTickets = 0;
		this.leafTickets = 0;
		this.flowerTickets = 10;
	}
	
//------- Porting functions -------
	//TODO: Implement probably by comma seperated integers
	public static int[] encodeGenes() {
		return new int[0];
	}
	
	public static GeneticInfo decodeGenes(int[] input) {
		return null;
	}
	
//-------- Functions for genetic encoding ------
}
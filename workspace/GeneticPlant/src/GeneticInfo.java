import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GeneticInfo 
{	
	//Used for calculating distribution of direction
	//for growing a new cell
	int leftGrowTickets;
	int rightGrowTickets;
	int upGrowTickets;
	int downGrowTickets;
	
	//Used for caclulating distribution of direction
	//for sharing energy to adjacent cell
	int leftShareTickets;
	int rightShareTickets;
	int upShareTickets;
	int downShareTickets;
	
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
		leftGrowTickets = 50;
		rightGrowTickets = 50;
		upGrowTickets = 52;
		downGrowTickets = 50;
		
		leftShareTickets = 51;
		rightShareTickets = 50;
		upShareTickets = 50;
		downShareTickets = 50;
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = 0.25;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = 0.25;
		
		growTickets = 50;
		shareTickets = 50;
		obstainTickets = 50;
		stemTickets = 50;
		leafTickets = 50;
		flowerTickets= 50;
	}
	
	//----- Functionality ------
	public void randomizeGenes() {
		;
	}
	
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
		
		return chooseTicket(ticketList);
	}
	
	public int chooseShareDirection() {		
		ArrayList<Integer> ticketList = new ArrayList<Integer>();
		ticketList.add(leftShareTickets);
		ticketList.add(rightShareTickets);
		ticketList.add(upShareTickets);
		ticketList.add(downShareTickets);
		
		return chooseTicket(ticketList);
	}
}
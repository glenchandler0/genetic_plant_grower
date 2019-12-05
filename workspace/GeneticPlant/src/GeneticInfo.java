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
		shareTickets = gi.shareTickets;
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
		xAwayParentGrowTickets = 75; 
		yParentGrowTickets = 5;
		yAwayParentGrowTickets = 100;
		
		leftShareTickets = 50;
		rightShareTickets = 50;
		upShareTickets = 10;
		downShareTickets = 75;
		//
		xParentShareTickets = 100;
		xAwayParentShareTickets = 10; 
		yParentShareTickets = 100;
		yAwayParentShareTickets = 25;
		
		//How much energy would be shared to adjacent cell
		energyShareAmnt = 0.20;
		//How much energy would be given to newly grown cell
		energyGrowAmnt = 0.25;
		
		growTickets = 70;
		shareTickets = 25;
		obstainTickets = 50;
		
		stemTickets = 50;
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
		sb.append("upShareTickets:\t\t" + upShareTickets + "\n");
		sb.append("downShareTickets:\t" + downShareTickets + "\n\n");
		
		sb.append("xParentShareTickets:\t" + xParentShareTickets + "\n");
		sb.append("xAwayParentShareTickets:\t" + xAwayParentShareTickets + "\n");
		sb.append("yParentShareTickets:\t\t" + yParentShareTickets + "\n");
		sb.append("yAwayParentShareTickets:\t" + yAwayParentShareTickets + "\n\n");
		
		sb.append("energyShareAmnt:\t" + energyShareAmnt + "\n\n");
		sb.append("energyGrowAmnt:\t\t" + energyGrowAmnt + "\n");
		
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
		this.growTickets -= 15;
		this.shareTickets += 15;
		this.obstainTickets += 10;
		
		if(this.growTickets < minLimit) {
			this.growTickets = minLimit;
		}
		if(this.shareTickets > maxLimit) {
			this.shareTickets = maxLimit;
		}
		if(this.obstainTickets > maxLimit) {
			this.obstainTickets = maxLimit;
		}
		
		this.stemTickets = 0;
		this.leafTickets = 10;
		this.flowerTickets = 0;
	}
	public void applyFlowerModifiers() {
		this.growTickets -= 30;
		this.shareTickets += 30;
		this.obstainTickets += 30;
		
		if(this.growTickets < minLimit) {
			this.growTickets = minLimit;
		}
		if(this.shareTickets > maxLimit) {
			this.shareTickets = maxLimit;
		}
		if(this.obstainTickets > maxLimit) {
			this.obstainTickets = maxLimit;
		}
		
		this.stemTickets = 0;
		this.leafTickets = 0;
		this.flowerTickets = 10;
	}
	
//------- Porting functions -------
	public static String stringExport(GeneticInfo gi) {
		int[] giArr = encodeGenes(gi);
		return stringExport(giArr);
	}
	public static String stringExport(int[] input) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length; i++) {
			sb.append(String.format("%3d,", input[i]));
		}
		return sb.toString();
	}
	
	public void stringImport(String input) throws Exception{
		if(input.length() != 24*4) {
			System.out.println("String import error! " + input.length());
			throw new Exception();
		}
		
		int[] arr = new int[24];
		
		int i = 0;
		int leftPointer = 0;
		while(i < 24) {
			String formatted = input.substring(leftPointer, leftPointer + 3);
			formatted = formatted.replaceAll(" ","");
			arr[i] = (int)Integer.parseInt(formatted);
			i++;
			leftPointer += 4;
		}
		
		arrayImport(arr);
	}
	public void arrayImport(int[] input) {
		this.leftGrowTickets = input[0];
		this.rightGrowTickets = input[1];
		this.upGrowTickets = input[2];
		this.downGrowTickets = input[3];
		this.xParentGrowTickets = input[4];
		this.xAwayParentGrowTickets = input[5]; //TODO: Remove
		this.yParentGrowTickets = input[6];
		this.yAwayParentGrowTickets = input[7]; //TODO: Remove
		
		this.leftShareTickets = input[8];
		this.rightShareTickets = input[9];
		this.upShareTickets = input[10];
		this.downShareTickets = input[11];
		this.xParentShareTickets = input[12];
		this.xAwayParentShareTickets = input[13]; //TODO: Remove
		this.yParentShareTickets = input[14];
		this.yAwayParentShareTickets = input[15]; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		this.energyShareAmnt = ((double)input[16]) / 100.0;
		//How much energy would be given to newly grown cell
		this.energyGrowAmnt = ((double)input[17]) / 100.0;
		
		this.growTickets = input[18];
		this.shareTickets = input[19];
		this.obstainTickets = input[20];
		
		this.stemTickets = input[21];
		this.leafTickets = input[22];
		this.flowerTickets = input[23];
	}
	
	public static int[] encodeGenes(GeneticInfo gi) {
		int[] retArr = new int[24];
		
		retArr[0] = gi.leftGrowTickets;
		retArr[1] = gi.rightGrowTickets;
		retArr[2] = gi.upGrowTickets;
		retArr[3] = gi.downGrowTickets;
		retArr[4] = gi.xParentGrowTickets;
		retArr[5] = gi.xAwayParentGrowTickets; //TODO: Remove
		retArr[6] = gi.yParentGrowTickets;
		retArr[7] = gi.yAwayParentGrowTickets; //TODO: Remove
	
		retArr[8] = gi.leftShareTickets;
		retArr[9] = gi.rightShareTickets;
		retArr[10] = gi.upShareTickets;
		retArr[11] = gi.downShareTickets;
		retArr[12] = gi.xParentShareTickets;
		retArr[13] = gi.xAwayParentShareTickets; //TODO: Remove
		retArr[14] = gi.yParentShareTickets;
		retArr[15] = gi.yAwayParentShareTickets; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		retArr[16] = (int)(gi.energyShareAmnt * 100);
		//How much energy would be given to newly grown cell
		retArr[17] = (int)(gi.energyGrowAmnt * 100);
		
		retArr[18] = gi.growTickets;
		retArr[19] = gi.shareTickets;
		retArr[20] = gi.obstainTickets;
		
		retArr[21] = gi.stemTickets;
		retArr[22] = gi.leafTickets;
		retArr[23] = gi.flowerTickets;
		
		return retArr;
	}
	
	public static GeneticInfo decodeGenes(int[] input) {
		GeneticInfo gi = new GeneticInfo();
		
		gi.leftGrowTickets = input[0];
		gi.rightGrowTickets = input[1];
		gi.upGrowTickets = input[2];
		gi.downGrowTickets = input[3];
		gi.xParentGrowTickets = input[4];
		gi.xAwayParentGrowTickets = input[5]; //TODO: Remove
		gi.yParentGrowTickets = input[6];
		gi.yAwayParentGrowTickets = input[7]; //TODO: Remove
		
		gi.leftShareTickets = input[8];
		gi.rightShareTickets = input[9];
		gi.upShareTickets = input[10];
		gi.downShareTickets = input[11];
		gi.xParentShareTickets = input[12];
		gi.xAwayParentShareTickets = input[13]; //TODO: Remove
		gi.yParentShareTickets = input[14];
		gi.yAwayParentShareTickets = input[15]; //TODO: Remove
		
		//How much energy would be shared to adjacent cell
		gi.energyShareAmnt = ((double)input[16]) / 100.0;
		//How much energy would be given to newly grown cell
		gi.energyGrowAmnt = ((double)input[17]) / 100.0;
		
		gi.growTickets = input[18];
		gi.shareTickets = input[19];
		gi.obstainTickets = input[20];
		
		gi.stemTickets = input[21];
		gi.leafTickets = input[22];
		gi.flowerTickets = input[23];
		
		return gi;
	}
	
//-------- Functions for genetic encoding ------
}
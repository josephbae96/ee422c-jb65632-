/* Critters 1 MyCritter6.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Joseph Bae>
 * <jb65632>
 * <16235>
 * <Vibhu Appalaraju>
 * <vka249>
 * <16235>
 * Slip days used: <0>
 * Git URL: https://github.com/josephbae96/ee422c-jb65632-.git
 * Spring 2017
 */


package assignment4;

import assignment4.Critter.TestCritter;

public class MyCritter6 extends TestCritter {
	
	@Override
	public String toString() { return "6"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public MyCritter6() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public void doTimeStep() {
		
		if(dir>this.getEnergy()){//picks bewteen two directions based on your energy
			dir=1;
		}
		else{dir = 2;}
			
		
	}

	@Override
	public boolean fight(String opponent) {
		run(dir);
		return false;
	}

	public static void runStats(java.util.List<Critter> critters1) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critters1) {
			MyCritter6 c = (MyCritter6) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + critters1.size() + " total MyCritter1s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critters1.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critters1.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critters1.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critters1.size()) + "% left   ");
		System.out.println();
	}
}

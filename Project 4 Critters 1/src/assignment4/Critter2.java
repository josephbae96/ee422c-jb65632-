/* Critters 1 MyCritter1.java
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

public class Critter2 extends Critter {

	public String toString() {
		return "2";
	}
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	@Override
	public boolean fight(String opponent) {
		return false;
	}
	
	public void doTimeStep() {
		if(dir > 8){
			dir = dir % 8;
		}
		walk(dir);
	}
	
	public static void runStats(java.util.List<Critter> critter2) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter2) {
			Critter2 c = (Critter2) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + critter2.size() + " total MyCritter1s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter2.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter2.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter2.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter2.size()) + "% left   ");
		System.out.println();
	}
}

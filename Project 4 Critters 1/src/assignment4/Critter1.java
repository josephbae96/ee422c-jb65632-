/* Critters 1 MyCritter1.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Vibhu Appalaraju
 * vka249
 * 16235
 * Joseph Bae
 * jb65632
 * 16235
 * Git URL: https://github.com/josephbae96/ee422c-jb65632-.git
 * Slip days used: <0>
 * Spring 2017
 */

package assignment4;

import java.util.*;

public class Critter1 extends Critter {

	//Critter1 will print a 1 to show where it exists
	public String toString() {
		return "1";
	}
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	//constructor for critter 1, puts in 4 into each gene from 0-7 on which direction they'll go
	//dir will hold a random integer up to 8
	public Critter1() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}

	//fight method for Critter 1, will try to fight opponent no matter what
	@Override
	public boolean fight(String opponent) {
		return true;
	}
	
	//doTimeStep for Critter1, will walk in direction of dir mod 8
	public void doTimeStep() {
		if(dir > 8){
			dir = dir % 8;
		}
		walk(dir);
	}
	
	//runstats of Critter1, shows what percentage Critter1 will try to go in a direction
	public static void runStats(java.util.List<Critter> critter1) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : critter1) {
			Critter1 c = (Critter1) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + critter1.size() + " total MyCritter1s    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * critter1.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * critter1.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * critter1.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * critter1.size()) + "% left   ");
		System.out.println();
	}


}

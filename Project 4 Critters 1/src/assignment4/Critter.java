
package assignment4;
/* CRITTERS Critter.java
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


import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private boolean hasMoved = false;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	/**
	 * Walk moves the critter once in a specified direction by changing it's specified x and y coordinates once by subtraction or addition.
	 * Some directions do not need to have their x or y direction changed
	 * 
	 * @param direction
	 * Doesn't return anything because it's void, changes position of the cirtter though
	 * Does not throw any exceptions
	 */
	protected final void walk(int direction) {
		this.energy -= Params.walk_energy_cost;
		
		if(!hasMoved){
			switch(direction){
				case 0:	this.x_coord ++;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						break;
				case 1:	this.x_coord ++;
						this.y_coord --;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 2:	this.y_coord--;
					if (this.y_coord <= 0){
						this.y_coord = Params.world_height + this.y_coord;
					}
						break;
				case 3:	this.x_coord --;
						this.y_coord --;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 4: this.x_coord --;
					if (this.x_coord <= 0){
						this.x_coord = Params.world_width + this.x_coord;
					}
						break;
				case 5: this.x_coord --;
						this.y_coord ++;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 6: this.y_coord ++;
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 7: this.x_coord ++;
						this.y_coord ++;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
			}
			hasMoved = true;
		}
	}
	
	/**
	 * Run moves the critter in a specified direction by adding or subtracting 2 to the appropriate x and y coordinates
	 * Some directions do not need their x or y direction to be changed
	 * 
	 * @param direction
	 * Doesn't return anything because it's void, changes the position of the critter though
	 * Does not throw any exceptions
	 */
	protected final void run(int direction) {
		this.energy -= Params.run_energy_cost;
		if(!hasMoved){
			switch(direction){
				case 0:	this.x_coord += 2;
					if (this.x_coord > Params.world_width){
						this.x_coord = this.x_coord%Params.world_width;
					}
				break;
				case 1:	this.x_coord += 2;
						this.y_coord -= 2;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 2:	this.y_coord -= 2;
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 3:	this.x_coord -= 2;
						this.y_coord -= 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord <= 0){
							this.y_coord = Params.world_height + this.y_coord;
						}
						break;
				case 4: this.x_coord -= 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						break;
				case 5: this.x_coord -= 2;
						this.y_coord += 2;
						if (this.x_coord <= 0){
							this.x_coord = Params.world_width + this.x_coord;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 6: this.y_coord += 2;
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
				case 7: this.x_coord += 2;
						this.y_coord += 2;
						if (this.x_coord > Params.world_width){
							this.x_coord = this.x_coord%Params.world_width;
						}
						if (this.y_coord > Params.world_height){
							this.y_coord = this.y_coord%Params.world_height;
						}
						break;
			}
			hasMoved = true;
		}
	}
	
	/**
	* reproduce first checks if the critter has the minimum amount of energy to reproduce the critter
	* If there is enough energy, the baby is created and then moved into the spot the direction specified
	*
	* @param offspring, direction
	* Does not return anything since the method is void
	* 
	*/
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy<Params.min_reproduce_energy){
			return;
		}
		else{
			offspring.energy=(this.energy)/2;
			if(this.energy%2==1){
				this.energy=((this.energy)/2)+1;
			}
			else{this.energy=((this.energy)/2); 
		}
		offspring.x_coord=this.x_coord;
		offspring.y_coord=this.y_coord;		
		offspring.energy=offspring.energy + Params.walk_energy_cost;
		offspring.walk(direction);
		
			babies.add(offspring);
	}
	}
	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
//		Critter critters;
//		try{
//			Class<?> mycritter = Class.forName(Critter.myPackage +"."+ critter_class_name);
//			critters = (Critter)mycritter.newInstance();
//			
//		}
//		catch(Exception e){
//			throw new InvalidCritterException(critter_class_name);
//		}
//		critters.x_coord=Critter.getRandomInt(Params.world_width);
//		while(critters.x_coord==0 || critters.x_coord== Params.world_width){
//			critters.x_coord=Critter.getRandomInt(Params.world_width);
//		}
//		critters.y_coord=Critter.getRandomInt(Params.world_height);
//		while(critters.y_coord==0 || critters.y_coord== Params.world_height){
//			critters.y_coord=Critter.getRandomInt(Params.world_height);
//		}
//		critters.energy=Params.start_energy;
//		population.add(critters);
		
		Critter critters;
		try{
			Class<?> mycritter = Class.forName(Critter.myPackage +"."+ critter_class_name);
			critters = (Critter)mycritter.newInstance();
		}
		catch(Exception e){
			throw new InvalidCritterException(critter_class_name);
		}
		critters.x_coord=Critter.getRandomInt(Params.world_width);	
		if(critters.x_coord==0)  {
			critters.x_coord=critters.x_coord+Params.world_width;
		}
		if(critters.x_coord==Params.world_width+1)  {
			critters.x_coord=critters.x_coord-Params.world_width;
		}
		critters.y_coord=Critter.getRandomInt(Params.world_height);
		if(critters.y_coord==0)  {
			critters.y_coord=critters.y_coord+Params.world_height;
		}
		if(critters.y_coord==Params.world_height+1)  {
			critters.y_coord=critters.y_coord-Params.world_height;
		}
		critters.energy=Params.start_energy;
		population.add(critters);
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		for (Critter x : population) {
			try {
				if (x.getClass().equals(Class.forName(Critter.myPackage + "." + critter_class_name))) {
					result.add(x);
				}
			} 
			catch (ClassNotFoundException e) {
				throw new InvalidCritterException(critter_class_name);
			}
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		
		/**
		 * getPopulation returns the critter population list
		 * @return
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		
		/**
		 * getBabies returns the list of critter babies list
		 * @return
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.
		population.clear();	
	}
	
	/**
	 * worldTimeStep first does the doTimeStep for all the critters, then removes critters with energy <=0,then checks if two critters are in the same spot
	 * if two critters are in the same spot, it checks for three options, both critters fighting in which case it rolls a number and the high number wins and the lower number critter dies
	 * one critter not wanting to fight and the other critter wanting to fight, making the critter that doesn't want to fight die
	 * and the final case being neither critter wants to fight in which the first critter is just removed
	 * critters are then checked again for energy <= 0 in which case they're removed
	 * algae is then created and babies are then created
	 * the HasMoved boolean is then set to false for every critter in the population
	 * 
	 */
	public static void worldTimeStep() {
		for(Critter mybbycritter:population){
			mybbycritter.doTimeStep();
		}
		for(int y=0;y<population.size();y++){
			if(population.get(y).getEnergy()==0){
				population.remove(y);
				y--;
			}
		}
		
		for(int i=0; i<population.size();i++){
			Critter C=population.get(i);
			if (C.energy <= 0) {
				population.remove(i);
				i = 0;
				continue;
			}
			for(int j=0;j<population.size();j++){
				Critter Ch=population.get(j);
				if (Ch.energy <= 0) {
					population.remove(j);
					j = 0;
					continue;
				}
				if(i==j){}
				else if((C.x_coord==Ch.x_coord) &&(C.y_coord==Ch.y_coord)) {
					boolean firstfight = C.fight(Ch.toString());
					boolean secondfight = Ch.fight(C.toString());
					if((C.x_coord==Ch.x_coord) &&(C.y_coord==Ch.y_coord)){
						if(firstfight && secondfight){	
							int roll1 = Critter.getRandomInt(C.energy);
							int roll2 = Critter.getRandomInt(Ch.energy);
							if(roll1>=roll2){
								C.energy=C.energy + (Ch.energy)/2;
								population.remove(j);
								i=0;
								break;
							}
							else{
								Ch.energy=Ch.energy + (C.energy)/2;
								population.remove(i);
								i=0;
								break;
							}
						}
						else if(firstfight && !secondfight){
							C.energy=C.energy + (Ch.energy)/2;
							population.remove(j);
							i=0;
							break;
						}
						else if(!firstfight && secondfight){
							Ch.energy=Ch.energy + (C.energy)/2;
							population.remove(i);
							i=0;
							break;
						}		
					}
				}
			}	
		}
		for(Critter mybbycritter:population){
			mybbycritter.energy = mybbycritter.energy - Params.rest_energy_cost;
		}
		
		for(int y=0;y<population.size();y++){
			if(population.get(y).getEnergy()==0){
				population.remove(y);
				y--;
			}
		}
		for(int i = 0; i < Params.refresh_algae_count; i++){								//generate algae
			try{
			Critter.makeCritter("Algae");
			}
			catch(InvalidCritterException e){
				e.toString();
			}
		}
		for(Critter baby:babies){
			population.add(baby);
		}
		babies.clear();
		for(Critter c : population){			
			c.hasMoved = false;
		}
		//displayWorld();
		
		// Complete this method.
	} // end of world time step
	
	/**
	 * first the borders are created then all the critters are displayed
	 */
	public static void displayWorld() {
		
		String[][] displayofmyworld = new String[Params.world_width + 2][Params.world_height + 2];
		displayofmyworld[0][0] = "+";
		displayofmyworld[Params.world_width + 1][0] = "+";
		displayofmyworld[0][Params.world_height + 1] = "+";
		displayofmyworld[Params.world_width + 1][Params.world_height + 1] = "+";
		String dash = new String("-");
		String verticalbar = new String("|");

		for (int x = 0; x < Params.world_height + 1; x++) {
			 if (x != 0 && x != Params.world_height + 1) {
				displayofmyworld[0][x] = verticalbar;
				displayofmyworld[Params.world_width + 1][x] = verticalbar;
			}
		}
		for (int x = 0; x < Params.world_width + 1; x++) {
			 if (x != 0 && x != Params.world_width + 1) {
				displayofmyworld[x][0] = dash;
				displayofmyworld[x][Params.world_height + 1] = dash;
			}
		}
		for (int y = 1; y < Params.world_height + 1; y++) {
			for (int x = 1; x < Params.world_width + 1; x++) {
				displayofmyworld[x][y] = " ";
			}
		}
		for(int i=0;i<population.size();i++){
			displayofmyworld[population.get(i).x_coord][population.get(i).y_coord]=population.get(i).toString();	
		}
		for (int y =0; y<Params.world_height + 2; y++) {
			for (int x = 0; x < Params.world_width + 2; x++) {
				if (x == Params.world_width + 1) {
					System.out.println(displayofmyworld[x][y]);
				} else {
					System.out.print(displayofmyworld[x][y]);
				}
			}
		}	
	}
}


package assignment4;

/* Critters 1 Critter.java
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
	
	protected final void reproduce(Critter offspring, int direction) {
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
            } catch (ClassNotFoundException e) {
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
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
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
	}
	
	public static void worldTimeStep() {
		// Complete this method.
		
		
		//clears the fact that the critters moved, should be last
		for(Critter c : population){			
			c.hasMoved = false;
		}
	}
	
	public static void displayWorld() {
		// Complete this method.
	}
}

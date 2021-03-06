package assignment4;

import java.util.List;

/* Critters 1 Main.java
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

import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */


public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method. Checks a file for input and depending on which command the user inputs, calls the appropriate method.
     * If the user input is wrong, then it throws the appropriate exceptions
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     * @throws invalid command: or error processed: exceptions
     */
    public static void main(String[] args) { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        boolean shouldExit = true;
       // Scanner kb  = new Scanner(System.in);
        
        while(shouldExit){
        	System.out.print("critters>");
        	String response = kb.nextLine();
        	String [] inputArray = response.split("\\s+");
        	
        	try{
        		switch(inputArray[0]){
        			case "quit": 
        				if (inputArray.length > 1){
        					throw new Exception();
        				}
        				shouldExit = false;
        				break;
        	
        			case "show": 
        				if (inputArray.length> 1){
        					throw new Exception();
        				}
        				Critter.displayWorld();
        				break;
        				
        			case "step":
        				if (inputArray.length == 1){
        					Critter.worldTimeStep();
        				}
        				else if (inputArray.length > 2){
        					throw new Exception();
        				}
        				else if (inputArray.length == 2){
        					int countNumber = Integer.parseInt(inputArray[1]);
        					for(int i = 0; i < countNumber; i ++){
        						Critter.worldTimeStep();
        					}
        				}
        				else{
        					throw new Exception();
        				}
        				break;
        				
        			case "seed":
        				if (inputArray.length == 2){
        					Critter.setSeed(Integer.parseInt(inputArray[1]));
        				}
        				else{
        					throw new Exception();
        				}
        				break;
        				
        			case "make": 
        				if (inputArray.length == 2){
        					Critter.makeCritter(inputArray[1]);
        				}
        				else if(inputArray.length == 3){
        					for(int i = 0; i < Integer.parseInt(inputArray[2]); i++){
        						Critter.makeCritter(inputArray[1]);
        					}
        				}
        				else {
        					throw new Exception();
        				}
        				break;
        				
        			case "stats":	
        				if(inputArray.length == 2){
        					Class<?> critters = Class.forName(myPackage + "." + inputArray[1]);//retrieves the class from the string
        			        Method meth = critters.getMethod("runStats", List.class);    //this method runs the getMethod from critters
        			        meth.invoke(null, Critter.getInstances(inputArray[1])); //finally, the getInstances is called and uses the critter class that the user specified
        				}
        				else{
        					throw new Exception();
        				}
        				break;
        				
        			default:
        				System.out.println("invalid command: " + response);
        			}
        	}
        	catch  (Exception e){
        		System.out.println("error processing: " + response);
        	}
        }     
        // System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
}

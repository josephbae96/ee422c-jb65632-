/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Joseph Bae>
 * <jb65632>
 * <16235>
 * <Raiyan Chowdhury>
 * <rac4444>
 * <16235>
 * Slip days used: <0>
 * Git URL: https://github.com/josephbae96/ee422c-jb65632-.git
 * Spring 2017
 */


package assignment3;
import java.util.*;
import java.io.*;

public class Main {
	
	// static variables and constants only here.
	int counter;
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		
		ArrayList<String> words = parse(kb);
		
		while (words != null) {
			ArrayList<String> ladder = getWordLadderBFS(words.get(0), words.get(1));
			printLadder(ladder);
			words = parse(kb);
		}
		
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		
		
		
		
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> inputs = new ArrayList<String>();
		inputs.add(keyboard.next());
		inputs.add(keyboard.next());
		if(inputs.contains("/quit")){							
			return null;
		} 
		return inputs;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		
		
		return null; // replace this line later with real return
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	LinkedList<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();//store paths to end word
    	ArrayList<String> tempPath = new ArrayList<String>();
    	tempPath.add(start);
    	queue.add(tempPath);
		Set<String> dict = makeDictionary();
		dict.remove(start.toUpperCase());
		
		while (!queue.isEmpty()) {
			ArrayList<String> path = queue.remove();
			if (path.contains(end)) {
				return path;
			}
			Iterator<String> itr = dict.iterator();
			while (itr.hasNext()) {
				String wordInPath = path.get(path.size()-1);
				String wordInDict = itr.next();
				if (differByOne(wordInPath, wordInDict)) {
					ArrayList<String> newPath = new ArrayList<String>();
					newPath.addAll(path);
					newPath.add(wordInDict.toLowerCase());
					queue.add(newPath);
					itr.remove();
				}
				
			}
		}
		
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		ladder.add(end);
		return ladder;
	}
    
    public static boolean differByOne(String s1, String s2) {
		int letterChanges = 0;
		for (int i = 0; i < s1.length(); i++) {
			if ((s1.charAt(i) != (s2.charAt(i) + ('a' - 'A'))) && ++letterChanges > 1) {
				return false;
			}
		}
		return true;
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder.size() == 2) {
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		} else {
			System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
			for (int i = 0; i < ladder.size(); i++) {
				System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here
}

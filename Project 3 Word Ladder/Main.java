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
		
		//ArrayList<String> words = parse(kb);
		
		/*while (words != null) {
			ArrayList<String> ladder = getWordLadderDFS(words.get(0), words.get(1));
			printLadder(ladder);
			for (int i = 0; i < ladder.size()-1; i++) {
				String s1 = ladder.get(i);
				String s2 = ladder.get(i+1);
				if(differ_by_One(s1,s2)) {
					//System.out.println("u good");
				} else {
					System.out.println("u fukt up");
					System.out.println(s1);
					System.out.println(s2);
				}
			}
			System.out.println("done");
			words = parse(kb);
		}*/
		Set<String> dict = makeDictionary();
		Iterator<String> i = dict.iterator();
		int counter = 0;
		while (i.hasNext() && counter < 5000) {
			String s1 = i.next();
			String s2 = i.next();
			ArrayList<String> ladder1 = getWordLadderBFS(s1, s2);
			ArrayList<String> ladder2 = getWordLadderDFS(s1, s2);
			for (int j = 0; j < ladder1.size()-1; j++) {
				String s11 = ladder1.get(j);
				String s22 = ladder1.get(j+1);
				if (!differ_by_One(s11, s22) && (ladder1.size() > 2)) {
					System.out.print("u fukt up BFS word ladder for: ");
					System.out.print(s1);
					System.out.print(" ");
					System.out.println(s2);
					System.out.print(s11);
					System.out.print(" ");
					System.out.println(s22);
				}
			}
			for (int j = 0; j < ladder2.size()-1; j++) {
				String s11 = ladder2.get(j);
				String s22 = ladder2.get(j);
				if (!differ_by_One(s11, s22) && (ladder2.size() > 2)) {
					System.out.print("u fukt up DFS word ladder for: ");
					System.out.print(s1);
					System.out.print(" ");
					System.out.println(s2);
					System.out.print(s11);
					System.out.print(" ");
					System.out.println(s22);
				}
			}
			counter += 1;
			if (counter % 100 == 0) {
				System.out.println(counter + " tests done so far!");
			}
		}
		System.out.println("testing done");
	}
	
	public static boolean differ_by_One (String s1, String s2) {
		int diff = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				diff += 1;
				if (diff > 1) {
					return false;
				}
			}
		}
		return true;
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
		inputs.add(keyboard.next().toLowerCase());
		if(inputs.contains("/quit")){							
			return null;
		} 
		inputs.add(keyboard.next().toLowerCase());
		if(inputs.contains("/quit")){							
			return null;
		} 
		return inputs;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
			ArrayList ladderPath = new ArrayList<String>();
			//ladderPath.add(start);
			ArrayList usedWords = new ArrayList<String>();
			Set<String> dict = makeDictionary();
			ArrayList<String> pathDFS = makeDFSTree(start, end, dict, usedWords, ladderPath);
			//StringBuilder newString = new StringBuilder(start);

			if (pathDFS == null) {
				ArrayList<String> ladder = new ArrayList<String>();
				ladder.add(start);
				ladder.add(end);
				return ladder;
			} else {
				return pathDFS;
			}
	}
	
	public static ArrayList<String> makeDFSTree(String start, String end, Set<String> dict, ArrayList<String> usedWords, ArrayList<String> path){
		String[] wordCombinations = getAllNext(start, dict);
		usedWords.add(start);
		path.add(start);
		if (path.contains(end)){
			return path;
		}
		for(int i = 0; i < wordCombinations.length; i++){
			while (usedWords.contains(wordCombinations[i])){
				i++;
				if (i >= wordCombinations.length){
					//path.add(null);
					path.remove(start);
					return null;
				}
			}
			//else if(path.contains(null)){
				//path.remove(null);
				//path.remove(start);
				
			//}
			
			ArrayList<String> newPath = makeDFSTree(wordCombinations[i], end, dict, usedWords, path);
			if (newPath!=null && newPath.contains(end)){
				return newPath;
			}
		}
		path.remove(start);
		return null;	
	}
	
	public static String[] getAllNext(String s, Set<String> dict) {
		ArrayList<String> list = new ArrayList<String>();//new arraylist for mutants
		for (int i = 0; i < s.length(); i++) {
			StringBuilder next = new StringBuilder();
			int k;
			for (k = 0; k < i; k++) {
				//append all chars up until the one you want to change
				next.append(s.charAt(k));
			}
			for (char j = 0; j < 26; j++) {
				StringBuilder nextWord = new StringBuilder();
				nextWord.append(next);
				//start by changing to a, then b, etc
				char c = (char)('a'+j);
				nextWord.append(c);
				for (int l = k+1; l < s.length(); l++) {
					//append the rest of the original word
					nextWord.append(s.charAt(l));
				}
				String string = nextWord.toString();
				if (dict.contains(string.toUpperCase()) && !string.equals(s)) {
					//if word is in dict, add to list
					list.add(string);
				}
			}
		}
		//convert list to array, return array
		String[] arr = list.toArray(new String[list.size()]);
		return arr;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
    	if (start.equals(end)){
    		ArrayList<String> ladder = new ArrayList<String>();
    		ladder.add(start);
    		ladder.add(end);
    		return ladder;
    	}
    	//stores all possible paths to get to the end word
    	LinkedList<ArrayList<String>> queue = new LinkedList<ArrayList<String>>();
    	ArrayList<String> tempPath = new ArrayList<String>();
    	tempPath.add(start);
    	queue.add(tempPath);
		Set<String> dict = makeDictionary();
		dict.remove(start.toUpperCase());
		//runs queue runs out
		while (!queue.isEmpty()) {
			//takes out the current path so we can extend the path to the end word
			ArrayList<String> path = queue.remove();
			//checks to see if we found a path to the end word
			if (path.contains(end)) {
				return path;
			}
			//iterates through the dictionary to find one letter difference of word
			Iterator<String> itr = dict.iterator();
			//while the dictionary has more words to be able to iterate through
			while (itr.hasNext()) {
				//gets the last word in the path
				String wordInPath = path.get(path.size()-1);
				//gets the next in the dictionary
				String wordInDict = itr.next();
				//enters differByOne method which checks if there's a 1 letter difference between words
				if (differByOne(wordInPath, wordInDict)) {
					//if it is, creates a new path
					ArrayList<String> newPath = new ArrayList<String>();
					//takes all the words from the new path, adds them and then adds the new word
					newPath.addAll(path);
					//adds the dictionary word and makes it all lowercase
					newPath.add(wordInDict.toLowerCase());
					//adds the new path to the queue so it can be checked later
					queue.add(newPath);
					//removes the word from the dictionary
					itr.remove();
				}
				
			}
		}
		//iff the end word was not found, it just returns the start word and the end word
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);
		ladder.add(end);
		return ladder;
	}
    
    //checks to see if the dictionary word and our current word is different by one letter
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
		//checks the ladder size to see if there were only two words, the start and the end word which means it doesn't exist
		if (ladder.size() == 2) {
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		} 
		//
		else {
			System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size()-1) + ".");
			for (int i = 0; i < ladder.size(); i++) {
				System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here
}

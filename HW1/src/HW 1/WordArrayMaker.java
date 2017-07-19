import java.io.*;

public class WordArrayMaker {
	/** This program reads a given text file and creates an array containing each unique word
	 * separated by spaces or punctuation.*/
	
	//static class variables that can be used in any method in the class and are repeatedly used
	static int sizeOfArray = 16; //initial size of the array
	static int incrementSize = 16; //once the array reaches it's max size, it's size is incremented by this number
	static String [] uniqueWords = new String[sizeOfArray];
	static int numberOfWords = 0; //number of words the array contains at any given point
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main (String [] args) throws IOException{
		readWordsFromFile(); //reads the file, separates each word, and adds it to the array
		trimTheArray(); //ensures the array only contains word elements, and no elements with null values
		//prints each element of the array followed by a space
		for(String s: uniqueWords)
			System.out.print(s + " ");	
	}//end main method

	//reads the file and separates each word to be added to the array
	public static void readWordsFromFile() throws IOException{
		BufferedReader buf = getFile(); //retrieves the file and ensures a valid file name was entered
		String input = buf.readLine();
		String wordSeparator = createWordSeparator();
		//Adds each line's words to the uniqueWords array
		while(input != null){ //stops once the line to be read is empty
			String [] arrayOfWords = input.split(wordSeparator); //splits the array around either a punctuation or a white space.
			//adds each word of a given line to the uniqueWords array
			for(String s: arrayOfWords)
				addWordToArray(s);
			input = buf.readLine();
		}
		buf.close();
	}//end readWordsFromFile method
	
	//prompts the user for the file name and returns a buffered reader to store the file
	public static BufferedReader getFile() {
		System.out.println("Please enter the name of the text file you'd like to read from:");
		BufferedReader buf = null;
		int numberOfTries = 3;
		//gives the user 3 tries to enter a valid file name, after which the program terminates
		while (numberOfTries>0 && buf == null) {
			try {
				String fileName = br.readLine();
				InputStream is = new FileInputStream(fileName);
				InputStreamReader sr = new InputStreamReader(is);
				buf = new BufferedReader(sr);
			} catch (FileNotFoundException e) {
				System.out.print("The file was not found. Please enter a valid file name: ");
				numberOfTries--;
			} catch (IOException e) {
				System.out.print("The file is not valid. Please enter a valid file name: ");
				numberOfTries--;
			} 
		}
		
		//if the user never enters a valid file name, they receive the following message and program terminates
		if(buf == null){
			System.out.println ("You failed to enter a valid file name. The program will now terminate.");
			System.exit(-1);
		}
		return buf;
	}//end getFile method

	//creates each element that can be used to separate a word in the program
	//returns a String (which will be used in a split method)
	public static String createWordSeparator() {
		String sentenceTerminator = "\\.\\p{Space}+"; //period followed by any number of white space
		String punctuationWithoutPeriod = "[\\p{Space}*\\p{Punct}&&[^\\.\\']]+"; //0 or more spaces and one or more punctuation (not including periods and apostrophes) in any order
		String spaces = "\\p{Space}"; //white space
		String wordSeparator = sentenceTerminator + "|" + punctuationWithoutPeriod + "|" + spaces + "+" + "|\\.$"; //checks for any of the above listed values
		return wordSeparator;
	}//end createWordSeparator method
	
	//trims the uniqueWords array so it only has elements that are words, and no elements that are null
	private static void trimTheArray() {
		String [] temp = new String[numberOfWords]; 
		//only copies as many elements as are words and not null
		for(int i = 0; i<numberOfWords; i++)
			temp[i] = uniqueWords[i];
		uniqueWords = temp;
	}//end trimTheArray method

	//checks to make sure each word is unique and adds it to the uniqueWords array
	public static void addWordToArray(String s) {
		//if the word does not already exist in the array, it is added and the number of words in the array is incremented
		if(!arrayContainsWord(uniqueWords, s)){  
			uniqueWords[numberOfWords] = s;
			numberOfWords++;
		//when the uniqueWords array reaches it's capacity size, it's size is increased by incrementSize
		if(numberOfWords == uniqueWords.length)
			sizeOfArray = sizeOfArray + incrementSize;
		String [] temp = new String [sizeOfArray];
		for(int i = 0; i < uniqueWords.length; i++)
			temp[i] = uniqueWords[i];
		uniqueWords = temp;
		}
	}//end addWordToArray method
	
	//check if the array contains a specific word and returns a boolean value
	public static boolean arrayContainsWord(String [] words, String word){
		for (int i = 0; i < numberOfWords; i++)
			if(word.equals(words[i]))
				return true;
		return false;
	}//end arrayContainsWord method

}//end class

import java.io.*;
import java.util.*;

public class DataAnalyzer {
	/**This program Prompts the user to enter whether they want to read data from a file or simply
	 * enter it manually, then analyzes the data by finding it's min, max, mean, 
	 * and standard deviation, asks the user if they want the analysis
	 * copied into a file, and finally displays the analysis on the screen*/

	//static class variables that can be used in any method in the class and are repeatedly used
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

	public static void main(String[] args) throws IOException {

		List<Double> listOfNumbers = null; 
		listOfNumbers = getListOfNumbers();

		double max = findMaxValue(listOfNumbers);
		double min = findMinValue(listOfNumbers);
		double mean = findMeanValue(listOfNumbers);
		double stdDev = findStdDev(listOfNumbers, mean);
		double variance = findVariance(listOfNumbers, stdDev);

		String response = evaluateUserInput("Do you want to save this analysis into a new file? (Y/N)");

		if (response.equalsIgnoreCase("Y")) {
			writeResultToFile(max, min, mean, stdDev, variance);
		}

		writeResultToConsole(max, min, mean, stdDev, variance);

	}// end main method

	//Displays the analyzed data on the console
	public static void writeResultToConsole(double max, double min, double mean, double stdDev, double variance) {
		System.out.println("Max = " + max);
		System.out.println("Min = " + min);
		System.out.println("Mean = " + mean);
		System.out.println("Standard Deviation = " + stdDev);
		System.out.println("Variance = " + variance);
	}//end writeResultToConsole method

	/**Depending on the user response with whether or not they want data read from a file,
	 *  adds numbers from either the console or the file and returns the list of numbers*/
	public static List<Double> getListOfNumbers() throws IOException {
		List<Double> listOfNumbers;
		String response = evaluateUserInput("Would you like to provide the name of a file with data? (Y/N)");
		if (response.equalsIgnoreCase("Y"))
			listOfNumbers = readAListOfNumbersFromFile(br);
		else
			listOfNumbers = readAListOfNumbersFromConsole(br);
		return listOfNumbers;
	}//end getListOfNumbers method

	//reads the name of the file analysis data should be written in and writes to that file
	public static void writeResultToFile(double max, double min, double mean, double stdDev, double variance)
			throws IOException {
		PrintWriter pw = new PrintWriter(System.out);
		System.out.println("Please enter the name of the file you want to write to:");
		String analysisFileName = br.readLine();
		FileOutputStream fos = new FileOutputStream(analysisFileName); //writes to the file with the specified name
		pw = new PrintWriter(fos, true);

		//prints analyzed data to the file
		pw.println("Max = " + max);
		pw.println("Min = " + min);
		pw.println("Mean = " + mean);
		pw.println("Stardard Deviation = " + stdDev);
		pw.println("Variance = " + variance);

		pw.close();
	}//end writeResultToFile method

	//gives user 3 tries to enter either "Y" or "N" and returns a String response to the message
	public static String evaluateUserInput(String message) throws IOException {
		int numberOfTries = 3;
		boolean inputProvided = false;
		System.out.println(message);

		String response = "";
		while (numberOfTries > 0 && !inputProvided) {
			response = br.readLine();
			if (response.equalsIgnoreCase("Y")) {
				inputProvided = true;
			} else if (response.equalsIgnoreCase("N")) {
				inputProvided = true;
			} else {
				System.out.println("I'm sorry that was not in a valid entry. Please type either Y or N.");
				numberOfTries--;
			}
		}
		//terminates program once user has entered 3 strings that are not "Y" or "N"
		if (numberOfTries == 0) {
			System.out.println("I'm sorry, you failed to enter an appropriate input. Program will now terminate.");
			System.exit(-1);
		}
		return response;
	}

	/**reads, separates, and converts to a double each number in the file and adds it to
	 *  the listOfNumbers array and then returns the array*/
	public static List<Double> readAListOfNumbersFromFile(BufferedReader br) throws IOException {
		List<Double> listOfNumbers = new ArrayList<Double>();
		BufferedReader fr = getFileName();
		String input = fr.readLine();
		while (input != null) {//stops reading once the an empty line is reached
			String[] numbers = input.split(" "); //splits each number separated by a space
			for (String s : numbers) {
				//if a non-number is in the file, the program will simply ignore it and display the following message
				try {
					listOfNumbers.add(Double.parseDouble(s));
				} catch (NumberFormatException e) {
					System.out.println(s + " is not a valid number. This was ignored while analyzing the data.");
				}
			}
			input = fr.readLine(); //reads next line in the file
		}

		fr.close();
		return listOfNumbers;
	}//end readAListOfNumbersFromFile method

	//prompts the user for the file name and returns a buffered reader to read the text
	public static BufferedReader getFileName() {
		BufferedReader fr = null;
		int numberOfTries = 3;
		//allows to attempt to enter a valid file name 3 times before terminating program
		while (fr == null && numberOfTries > 0) {
			try {
				System.out.println("Please enter the file name:");
				String fileName = br.readLine();
				InputStream is = new FileInputStream(fileName);
				InputStreamReader isr = new InputStreamReader(is);
				fr = new BufferedReader(isr);
			} catch (FileNotFoundException e) {
				System.out.println("This is not a valid file name.");
				numberOfTries--;
			} catch (IOException e) {
				System.out.println("This is not a valid file name.");
				numberOfTries--;
			}
		}

		if (numberOfTries == 0) {
			System.out
					.println("I'm sorry, you typed invalid file names too many times. The program will now terminate.");
			System.exit(-1);
		}
		return fr; //returns buffered reader which will read data from given file
	}//ends getFileName method

	/**reads, separates, and converts to a double each number entered on the console and 
	 * adds it to the listOfNumbers array and then returns the array*/
	public static List<Double> readAListOfNumbersFromConsole(BufferedReader br) throws IOException {
		System.out.println("Please enter a series of numbers. Press enter after each number.");
		String input = br.readLine().trim(); // gets rid of leading or trailing spaces
		List<Double> listOfNumbers = new ArrayList<Double>();

		//if user enters non-number, asks user to re-enter number and then adds the number to the list of numbers
		while (input.length() != 0) {
			try {
				listOfNumbers.add(Double.parseDouble(input));
			} catch (NumberFormatException e) {
				System.out.println(input + " is not a valid number. Please enter a new number.");
			}
			input = br.readLine().trim();
		}
		return listOfNumbers;
	}//end readAListOfNumbersFromConsole method

	// returns the max of the list of numbers
	public static double findMaxValue(List<Double> list) {
		double max = Double.NEGATIVE_INFINITY; // Need small negative number but Double.MIN_VALUE is the smallest positive double so use Double.NEGATIVE_INFINITY instead
		for (double d : list) {
			if (d > max)
				max = d;
		}
		return max;
	}//end findMaxValue method

	// returns the min of the list of numbers
	public static double findMinValue(List<Double> list) {
		double min = Double.MAX_VALUE;
		for (double d : list) {
			if (d < min)
				min = d;
		}
		return min;
	}//end findMinValue method

	// returns the mean of the list of numbers
	public static double findMeanValue(List<Double> list) {
		double sum = 0;
		for (double d : list) {
			sum += d;
		}
		double mean = sum / list.size();
		return mean;
	}//end findMeanValue method

	// returns the standard deviation of the list of numbers
	public static double findStdDev(List<Double> list, double mean) {
		double sumOfDifferences = 0;
		double difSquared = 0;
		for (double d : list) {
			difSquared = Math.pow((d - mean), 2);
			sumOfDifferences += difSquared;
		}
		double stdDev = sumOfDifferences / list.size();
		return stdDev;
	}//end findStdDev method
	
	//returns the variance of the list of numbers
	public static double findVariance(List<Double> list, double stdDev){
		double variance = Math.pow(stdDev, 2);
		return variance;
	}//end findVariance method

}// end class DataCollection

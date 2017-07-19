import java.io.*;
import java.util.*;

public class DataCollection {

	public static void main(String[] args) throws IOException {
		
		InputStreamReader asker = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(asker);
		
		System.out.println("Would you prefer to type in data  or give the "
				+ "name of a file holding the data? (Please use either data or file as your answer)");
		List<Double> listOfNumbers = null;
		
		if(br.readLine().equals("data"))
			listOfNumbers = readAListOfNumbersFromConsole(br);
		else
			listOfNumbers = readAListOfNumbersFromFile(br);

		
		System.out.println("Do you want to save this analysis into a new file? (Y/N)");
		String response = br.readLine();
		PrintWriter pw = new PrintWriter(System.out);
		if(response.equals("Y")){
			System.out.println("Please enter the name of the file you want to write to:");
			String analysisFileName = br.readLine();
			FileOutputStream fos = new FileOutputStream(analysisFileName);
			pw = new PrintWriter(fos, true);
		}
		
		double max = findMaxValue(listOfNumbers);
		double min = findMinValue(listOfNumbers);
		double mean = findMeanValue(listOfNumbers);
		double stdDev = findStdDev(listOfNumbers, mean);
		
		pw.println("Max = " + max);
		pw.println("Min = " + min);
		pw.println("Mean = " + mean);
		pw.println("Stardard Deviation = " + stdDev);
		
		System.out.println("Max = " + max);
		System.out.println("Min = " + min);
		System.out.println("Mean = " + mean);
		System.out.println("Standard Deviation = " + stdDev);
		
	}//end main method

	private static List<Double> readAListOfNumbersFromFile(BufferedReader br) throws IOException{
		List <Double> listOfNumbers = new ArrayList<Double>();
		System.out.println("Please enter the file name:");
		String fileName = br.readLine();
		InputStream is = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader fr = new BufferedReader(isr);
		String input = fr.readLine();
		while(input != null){
			String [] numbers = input.split(" ");
			for (String s: numbers){
				listOfNumbers.add(Double.parseDouble(s));
			}
			input = fr.readLine();
		}
			
		fr.close();
		return listOfNumbers;
	}

	public static List<Double> readAListOfNumbersFromConsole(BufferedReader br) throws IOException {
		System.out.println("Please enter a series of numbers. Press enter after each number.");
		String input = br.readLine().trim(); // gets rid of leading or trailing spaces
		List <Double> listOfNumbers = new ArrayList<Double>();
		
		while(input.length() != 0){
			double number = Double.parseDouble(input);
			listOfNumbers.add(number);
			input = br.readLine().trim();
		}
		return listOfNumbers;
	}
	
		//this will find the max of the list of numbers
	public static double findMaxValue(List<Double> list){
		double max = Double.NEGATIVE_INFINITY; //Need small negative number but Double.MIN_VALUE is the smallest positive double
		for(double d: list){
			if(d>max)
				max = d;
		}
		return max;
	}
		
		//this will find the min of the list of numbers
	public static double findMinValue(List<Double> list){
		double min = Double.MAX_VALUE;
		for(double d: list){
			if(d<min)
				min = d;
		}
		return min;
	}
		
		//this will find the mean of the list of numbers
	public static double findMeanValue(List<Double> list){
		double sum = 0;
		for(double d: list){
			sum += d;
		}
		double mean = sum/list.size();
		return mean;
	}
		
		//this will find the standard deviation of the list of numbers
	public static double findStdDev(List<Double> list, double mean){
		double sumOfDifferences = 0;
		double difSquared = 0;
		for(double d: list){
			difSquared = Math.pow((d-mean), 2);
			sumOfDifferences+=difSquared;
		}
		double stdDev = sumOfDifferences/list.size();
		return stdDev;	
	}

}//end class DataCollection

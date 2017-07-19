import java.io.*;

public class DraftDataCollection {

	public static void main(String[] args) throws IOException{
		
		/** 
		 * Write a program which will offer two ways to handle data to be analysed; 
		 * the data being a collection of numbers (doubles). The 'analysis' will 
		 * comprise finding the max and the min of the collection of numbers, as well 
		 * as the mean* and the standard deviation*. 
		 * 
		 * The first way of gathering the 
		 * data should invite the user to type in numbers at the keyboard once the 
		 * program has been started, with the 'typing' of an empty string or the typing 
		 * of a non-number being used to terminate the list of numbers. The second 
		 * choice to be provided should ask the user for the name of a file to be read 
		 * from, this file containing a space separated list of 'doubles' spread over 
		 * several lines. When the program starts, it should ask the user if they'd 
		 * prefer to type in data or give the name of a file holding the data, and then 
		 * run accordingly. In both cases the answer should be displayed on the screen, 
		 * though an option should be provided to write the analysis data to a file 
		 * whose name has also been asked for by the program. (The goal here is to 
		 * develop a program for data anaylsis of large files, so the 'first case' is 
		 * just a way of encouraging you to start simply!) */

		//The program asks the user a question, and decides whether to print the answer on the screen or in a file.
		InputStreamReader asker = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(asker);
		
		/**System.out.println("Would you prefer to type in data  or give the "
				+ "name of a file holding the data? (Please use either data or file as your answer)");
		
		String input = br.readLine();
		double [] number = new double [input.length()];
		
		if (input.equals("data")){
			System.out.println("Please enter a series of numbers separated by a space: ");
			String numberEntry = br.readLine();
			numberEntry = numberEntry.replaceAll("\\s","");
			for(int i = 0; i<numberEntry.length(); i++)
				number[i]=Double.parseDouble(numberEntry.substring(i,i+1));
			}
		
		if (input.equals("file")){
			System.out.println("Please enter the name of the file to be read from: ");
			String fileToBeReadFrom = br.readLine();
			FileReader fr = new FileReader(fileToBeReadFrom);
			System.out.println("Please enter the name of the new file you would like to write the analysis data to:");
			String newFile = br.readLine();
			FileOutputStream fos = new FileOutputStream(newFile);
			PrintWriter pw = new PrintWriter(fos, true);
		}*/
			
		System.out.println("Would you prefer to type in data  or give the "
		+ "name of a file holding the data? (Please use either data or file as your answer)");

		String input = br.readLine();
		String numberEntry = "";
		double [] number = new double [input.length()];

		if (input.equals("data")){
			System.out.println("Please enter a series of numbers separated by a space: ");
			numberEntry = br.readLine();
			numberEntry = numberEntry.replaceAll("\\s","");
			for(int i = 0; i<numberEntry.length(); i++)
				number[i]=Double.parseDouble(numberEntry.substring(i,i+1));
		}
		
		for (int i = 0; i < number.length; i++){
			System.out.println(number[i]);
		}
		br.close();

	} //end main method

} //end class DataCollection

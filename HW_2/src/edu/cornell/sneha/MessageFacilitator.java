package edu.cornell.sneha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import edu.cornell.sneha.exception.InvalidEntryException;
import edu.cornell.sneha.exception.NoMessageFoundException;
import edu.cornell.sneha.exception.QuitApplicationException;

public class MessageFacilitator {
	/**
	 * This class creates the UserInterface to facilitate sending, receiving,
	 * and searching for messages. Eventually this can be extended to include a
	 * GUI or a web-based interface.
	 **/

	public static void main(String[] args) {
		PostOffice center = new PostOffice();

		try {
			createMessageDetails(center);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} // end try/catch

	}// end of main method

	public static void createMessageDetails(PostOffice center) throws IOException {
		/**This method handles the exceptions thrown by the method selectUserAction
		 *  and provides the loop so that the user can continue to interact with
		 *  the messaging system**/
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		while (true) {
			try {
				selectUserAction(center, br);
			} catch (NoMessageFoundException e) {//Handles the exception by displaying a message and continuing in the while loop
				System.out.println(e.getMessage());
			} catch (InvalidEntryException e) { //Handles the exception by displaying a message and continuing in the while loop
				System.out.println(e.getMessage());
			} catch (QuitApplicationException e) {//Handles the exception by displaying a message and continuing in the while loop
				System.out.println(e.getMessage());
				System.exit(-1);
			} catch (ParseException e) {//Handles the exception by displaying a message and continuing in the while loop
				System.out.println("This is an invalid date. " + e.getMessage());
			} catch (IOException e) {//Handles the exception by displaying a message and continuing in the while loop
				System.out.println(e.getMessage());
			} // end of try/catch
			
		} // end of while loop

	}// end of creatingMessageDetails method

	
	public static void selectUserAction(PostOffice center, BufferedReader br)
			throws IOException, NoMessageFoundException, ParseException, InvalidEntryException,
			QuitApplicationException {
		/**This is the main UI for the program. The user can choose to send,
		 *  receive. or search for multiple message. Or they can choose to
		 *  quit the program.**/
		
		System.out.println("Select your action (1, 2, or 3, or q to quit):");
		System.out.println("\t1. Send a message.");
		System.out.println("\t2. Check for unread messages.");
		System.out.println("\t3. Would you like to search for a specific message?");
		String response = br.readLine();
		if (response.equals("1")) {
			sendAMessage(center, br);
		} else if (response.equals("2")) {
			checkAndReadMessages(br, center);
		} else if (response.equals("3")) {
			searchForMessage(br, center);
		} else if (response.equalsIgnoreCase("q")) {
			throw new QuitApplicationException(); 
		} else {
			throw new InvalidEntryException(); 
		}//end of if statements
	}//end of selectUserAction method

	private static void searchForMessage(BufferedReader br, PostOffice center)
			throws IOException, ParseException, NoMessageFoundException {
		/**This method displays the search categories, calls the method 
		 * searchByCategory, and then displays the resulting messages. **/
		System.out.println("Which category would you like to search by? ");
		System.out.println("\t1. Sender");
		System.out.println("\t2. Receiver");
		System.out.println("\t3. Subject");
		System.out.println("\t4. Content");
		System.out.println("\t5. Date Sent");
		MessageList searchedMessages = searchByCategory(br, center);
		
		//printing out search results
		if (searchedMessages != null && searchedMessages.getSize() > 0) {
			for (int i = 0; i < searchedMessages.getSize(); i++) {
				System.out.println(searchedMessages.get(i));
			} // end of for loop
		} // end of if statement
		else {
			throw new NoMessageFoundException(searchedMessages.toString()); //throws this exception when the search provides no results
		} // end of else
	}// end of searchForMessage method

	/**
	 * @param br
	 * @param center
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static MessageList searchByCategory(BufferedReader br, PostOffice center)
			throws IOException, ParseException {
		/**Takes in the search criteria from the user and  goes through stored
		 *  messages in post office, adding the results to the
		 *  searchedMessages list**/
		String response = br.readLine(); //provides the search criterion
		MessageList searchedMessages = null; //will store results in this list
		switch (response) {
		case "1":
			String senderName = promptUser(br, "Please enter sender's name: ");
			searchedMessages = center.searchMessagesBySender(senderName);
			break;
		case "2":
			String receiverName = promptUser(br, "Please enter receiver's name: ");
			searchedMessages = center.searchMessagesByReceiver(receiverName);
			break;
		case "3":
			String subject = promptUser(br, "Please enter subject: ");
			searchedMessages = center.searchMessagesBySubject(subject);
			break;
		case "4":
			String content = promptUser(br,
					"Please enter some part of the content of the message: ");
			searchedMessages = center.searchMessagesByContent(content);
			break;
		case "5":
			String date = promptUser(br, "Please enter the date(mm/dd/yyyy): ");
			searchedMessages = center.searchMessagesByDate(date);
			break;
		default:
			break;
		}// end of switch
		return searchedMessages;
	}//end searchByCategory method

	public static String promptUser(BufferedReader br, String prompt) throws IOException {
		/**Utility method to get a response for a given prompt**/
		System.out.print(prompt);
		String receiverName = br.readLine();
		return receiverName;
	}//end promptUser method

	private static void checkAndReadMessages(BufferedReader br, PostOffice center)
			throws IOException, NoMessageFoundException {
		/**This method allows the recipient to check their messages and displays
		 *  the alert if the recipient has new messages.**/
		System.out.println("Please enter your name:");
		String receiverName = br.readLine();
		Person receiver = Person.getInstance(receiverName);
		if (receiver.hasUnreadMessageAlert()) {
			System.out.println(receiverName + " has unread messages!");
			System.out.println("Would you like to read these messages?(Y/N)");
			String response = br.readLine();
			if (response.equalsIgnoreCase("Y"))
				receiver.readMessages();
		} // end if statement
		else {
			throw new NoMessageFoundException(receiverName + " has no unread messages.");
		}// end else

	}// end checkAndReadMessages method

	public static void sendAMessage(PostOffice center, BufferedReader br) throws IOException {
		/**This method gathers all the information to create and send a message.**/
		System.out.println("Please enter your name: ");
		String senderName = br.readLine();
		System.out.println(
				"Please enter the name of the person you'd like to send your message to: ");
		String receiverName = br.readLine();
		System.out.println("Please enter a brief subject for your message: ");
		String subject = br.readLine();
		System.out.println("Please enter your message: ");
		String content = br.readLine();
		createAndSendMessage(senderName, receiverName, subject, content, center);
	}//end of sendAMessage method

	public static void createAndSendMessage(String senderName, String receiverName, String subject,
			String content, PostOffice center) {
		/**This method actually creates the message and sends the message to
		 *  the Post office.**/
		
		//Use getInstance so that a new object is not created every time the same name
		//is entered
		Person sender = Person.getInstance(senderName);
		Person receiver = Person.getInstance(receiverName);
		Message message = new Message();

		message.setSubject(subject);
		message.setContent(content);
		message.setSender(sender);
		message.setReceiver(receiver);
		center.sendMessage(message);

	}// end of createAndSendMessage method

}// end of class

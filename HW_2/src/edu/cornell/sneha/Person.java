package edu.cornell.sneha;

import java.util.HashMap;
import java.util.Map;

public class Person {
	/**
	 * This class represents the sender and receiver of the messages. It also
	 * keeps track of unique people through getInstance. It also keeps track of
	 * the unread messages and unread message alerts for each recipient.
	 */

	private String name;
	private int id;
	private MessageList unreadMessages = new MessageList(); // list of unread
															// messages that
															// every receiver
															// has
	private static int currentID = 10000; // starting id assigned to the first
											// person created
	private static Map<String, Person> personStore = new HashMap<>();// helps
																		// keep
																		// track
																		// of
																		// unique
																		// people
	private boolean unreadMessagesAlert = false; // alert is set to true once a
													// recipient has unread
													// messages
	private String lastReceiptReceived;// holds the receipt the sender receives

	public String getLastReceiptReceived() {
		return lastReceiptReceived;
	}// end getLastReceiptReceived method

	public void clear() {
		/** Clears the store of people created */
		personStore.clear();
	}// end clear method

	public String getName() {
		return name;
	}// end of getName method

	public void setName(String name) {
		this.name = name;
	}// end of setName method

	public MessageList getUnreadMessages() {
		return unreadMessages;
	}// end of getUnreadMessages method

	public void setUnreadMessageAlert(boolean value) {
		this.unreadMessagesAlert = value;
	}// end of setUnreadMessageAlert method

	private Person(String name) {
		this.name = name;
		this.id = currentID++;
	}// end of constructor

	public void receiveMessages(Message message) {
		/**
		 * This method adds the message a recipient receives to an
		 * unreadMessages list and alerts the recipient that they have a new
		 * message.
		 */
		unreadMessages.add(message);
		setUnreadMessageAlert(true);// creates the alert once the recipient has
									// new unread messages
		System.out.println("**Alert: " + getName() + " has a new message!**");
	}// end receiveMessages method

	public void readMessages() {
		/**
		 * This method allows the recipient to read each message, send a receipt
		 * to the sender , and clears the unread message alert.
		 */
		for (int i = 0; i < unreadMessages.getSize(); i++) {
			System.out.println(unreadMessages.get(i));// prints out each unread
														// message
			Receipt receipt = new Receipt(unreadMessages.get(i));// creates a
																	// new
																	// receipt
																	// for each
																	// message
			unreadMessages.get(i).getSender().sendReceipt(receipt);// send the
																	// receipt
																	// to each
																	// sender
		} // end for loop
		unreadMessages.clear();
		setUnreadMessageAlert(false);

	}// end readMessages method

	private void sendReceipt(Receipt receipt) {
		/** This method is used by readMessages to display the receipt. */
		Message message = receipt.getMessage();
		lastReceiptReceived = "**Attention " + message.getSender() + " here is your receipt!**\n"
				+ "\t" + message.getReceiver() + " read the message on " + receipt.getTimeReceived()
				+ ".";
		System.out.println(lastReceiptReceived);
	}// end sendReceipt method

	// ensures that a new person is not created every time given a name
	// when the person with a specific has already been assigned an id, the
	// person will keep that id
	public static Person getInstance(String name) {
		/**
		 * This method ensures that a new person is not created every time a
		 * name is given. When the person with that specific name has already
		 * been assigned an id, the person will keep that id
		 */
		if (personStore.containsKey(name))
			return personStore.get(name);
		Person person = new Person(name);
		personStore.put(name, person);
		return person;
	}// end of getInstance method

	public String toString() {
		return name + "(" + id + ")";
	}// end of toString method

	public boolean hasUnreadMessageAlert() {
		return unreadMessagesAlert;
	}// end of hasUnreadMessageAlert method

}// end of class

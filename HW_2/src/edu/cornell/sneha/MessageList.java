package edu.cornell.sneha;

public class MessageList {
	/** This class stores and manages a list of Messages **/

	private static final int INCREMENT_SIZE = 10;// This is the initial size of
													// the array as well as
													// number by which the array
													// size increments
	private int size = 0; // This is the number of elements added to the array
	public Message[] internalStore = new Message[INCREMENT_SIZE];// This array
																	// holds the
																	// messages

	public int getSize() {
		return size;
	}// end of getSize method

	public void setSize(int size) {
		this.size = size;
	}// end of setSize method

	public Message[] getMessageList() {
		return internalStore;
	}// end of getMessageList method

	public void setMessageList(Message[] messageList) {
		this.internalStore = messageList;
	}// end of setMessageList method

	public static int getIncrementSize() {
		return INCREMENT_SIZE;
	}// end of getIncrementSize method

	public void add(Message message) {
		/** This method allows for messages to be added to the internalStore */
		internalStore[size++] = message;
		if (size >= internalStore.length) {// once the internalStore's capacity
											// is reached, the internalStore
											// size is increased
			increaseStoreSize();
		} // end of if statement

	}// end of add method

	private void increaseStoreSize() {
		/**
		 * This method resizes internalStore once its capacity has been reached
		 **/
		Message[] temp = new Message[internalStore.length + INCREMENT_SIZE];
		for (int i = 0; i < internalStore.length; i++) {
			temp[i] = internalStore[i]; // stores the internalStore value in the
										// new, larger array
		} // end of for loop
		internalStore = temp;

	}// end of increaseStoreSize method

	public Message get(int i) {
		/**
		 * This method allows for a specific element of the array to be
		 * retrieved
		 **/
		return internalStore[i];
	}// end of get method

	public void clear() {
		/** This method clears the array **/
		size = 0;
	}// end of clear method

	@Override
	public String toString() {
		/**
		 * This method overrides the objects toString method so that a String
		 * representation of the object can be printed
		 **/
		StringBuilder message = new StringBuilder();
		message.append("[");
		for (int i = 0; i < size; i++) {
			message.append(internalStore[i].toString());
		} // end of for loop
		message.append("]");
		return message.toString();
	}// end of toString method

}// end of class

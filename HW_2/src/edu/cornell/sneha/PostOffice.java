package edu.cornell.sneha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostOffice {
	/**
	 * This class is the message center that connects the sender of a message to
	 * the receiver of the message. It provides facilities to store and search
	 * for the message(s).
	 */

	private MessageList messageList = new MessageList();// stores all sent
														// messages

	public void sendMessage(Message message) {
		/**
		 * This message sets the date the message was sent, adds it to the list
		 * of messages the post office stores, and send the message to the
		 * receiver.
		 */
		message.setDateSent(new Date());
		messageList.add(message);
		message.getReceiver().receiveMessages(message);
	}// end of sendMessage method

	public MessageList getMessageList() {
		return this.messageList;
	}// end of getMessageList method

	public MessageList searchMessagesBySender(String search) {
		/**
		 * This method allows for messages to be searched by the sender name.
		 * Case is ignored but exact name is expected.
		 */
		MessageList messagesBySender = new MessageList();
		for (int i = 0; i < messageList.getSize(); i++) {
			if (messageList.get(i).getSender().getName().equalsIgnoreCase(search))
				messagesBySender.add(messageList.get(i));
		} // end of for loop

		return messagesBySender;
	}// end of searchMessageBySender method

	public MessageList searchMessagesByReceiver(String receiver) {
		/**
		 * This method allows for messages to be searched by the recipient's
		 * name. Case is ignored but exact name is expected.
		 */
		MessageList messagesByReceiver = new MessageList();
		for (int i = 0; i < messageList.getSize(); i++) {
			if (messageList.get(i).getReceiver().getName().equalsIgnoreCase(receiver))
				messagesByReceiver.add(messageList.get(i));
		} // end of for loop

		return messagesByReceiver;
	}// end of searchMessageByReceiver method

	public MessageList searchMessagesBySubject(String subject) {
		/**
		 * This method allows for messages to be searched by the subject. Case
		 * is ignored but exact words are expected.
		 */
		MessageList messagesBySubject = new MessageList();
		for (int i = 0; i < messageList.getSize(); i++) {
			if (messageList.get(i).getSubject().equalsIgnoreCase(subject))
				messagesBySubject.add(messageList.get(i));
		} // end of for loop

		return messagesBySubject;
	}// end of searchMessageBySubject method

	public MessageList searchMessagesByContent(String content) {
		/**
		 * This method allows for messages to be searched by the content. Any
		 * part of the content can be searched.
		 */
		MessageList messagesByContent = new MessageList();
		for (int i = 0; i < messageList.getSize(); i++) {
			if (messageList.get(i).getContent().toLowerCase().contains(content.toLowerCase()))
				messagesByContent.add(messageList.get(i));
		} // end of for loop

		return messagesByContent;
	}// end of searchMessageByContent message

	public MessageList searchMessagesByDate(String dateStr) throws ParseException {
		/** This method allows for messages to be searched by the date. */
		MessageList messagesByDate = new MessageList();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");// specifies
																	// the date
																	// format
																	// needed
		Date date = sdf.parse(dateStr);// takes a string written in the
										// specified format and converts it to a
										// Date.
		Date nextDay = new Date(date.getTime() + 24 * 60 * 60 * 1000); // Used
																		// this
																		// to
																		// add
																		// milliseconds
																		// in a
																		// day
		for (int i = 0; i < messageList.getSize(); i++) {
			Date dateSent = messageList.get(i).getDateSent();
			// ensures that the date of the messages is between the date and the
			// next day, regarless of time
			if (dateSent.after(date) && dateSent.before(nextDay))
				messagesByDate.add(messageList.get(i));
		} // end of for loop

		return messagesByDate;
	}// end of searchMessagesByDate method

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\tNumber of Messages: " + messageList.getSize() + "\n");
		for (int i = 0; i < messageList.getSize(); i++) {
			Message message = messageList.get(i);
			builder.append("\t\tMessage " + i + ": From " + message.getSender() + ", To: "
					+ message.getReceiver());
			builder.append(", Subject: " + message.getSubject() + "\n\t\tContent: "
					+ message.getContent() + "\n");
		} // end of for loop
		return builder.toString();
	}// end of toString method

}// end of class

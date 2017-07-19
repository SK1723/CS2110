package edu.cornell.sneha;

import java.util.Date;

public class Receipt {
	/**
	 * This class represents that the recipient has received the message. It
	 * also time stamps when the message was received.
	 */

	private Date timeReceived;
	private Message message;

	public Receipt(Message message) {
		setMessage(message);
		setTimeReceived(new Date());
	}// end of constructor

	public Date getTimeReceived() {
		return timeReceived;
	}// end of getTimeReceived

	public void setTimeReceived(Date timeReceived) {
		this.timeReceived = timeReceived;
	}// end of setTimeReceived

	public Message getMessage() {
		return message;
	}// end of getMessage

	public void setMessage(Message message) {
		this.message = message;
	}// end of setMessage

}

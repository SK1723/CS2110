package edu.cornell.sneha;

import java.util.Date;

public class Message {
	// This class holds the information about the message.

	private Person sender, receiver;
	String subject, content;
	private Date dateSent;

	public Message() {

	}// end default constructor

	public Message(Person sender, Person receiver, String subject, String content) {
		// sets a sender, receiver, subject, and content for this message
		this.sender = sender;
		this.receiver = receiver;
		this.subject = subject;
		this.content = content;
	}// end constructor

	public Person getSender() {
		return sender;
	}// end of getSender method

	public void setSender(Person sender) {
		this.sender = sender;
	}// end of setSender method

	public String getContent() {
		return content;
	}// end of getContent method

	public void setContent(String content) {
		this.content = content;
	}// end of setContent method

	public Date getDateSent() {
		return dateSent;
	}// end of getDateSent method

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}// end of setDateSent method

	public String getSubject() {
		return subject;
	}// end of getSubject method

	public Person getReceiver() {
		return receiver;
	}// end of getReceiver method

	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}// end of setReceiver method

	public void setSubject(String subject) {
		this.subject = subject;
	}// end of setSubject method

	public String toString() {
		return "From: " + sender + ", To: " + receiver + " on " + dateSent + ", Subject: " + subject
				+ "\n\tMessage: " + content;
	}// end of toString method

}// end of class

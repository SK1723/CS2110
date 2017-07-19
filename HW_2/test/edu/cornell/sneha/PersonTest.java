package edu.cornell.sneha;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	/** This has the suite of unit tests for the Person class */

	private PostOffice center;
	Person sneha;
	Person swati;
	Person vimlesh;
	Person madhuri;

	@Before
	public void setUp() throws Exception {
		// These variables are reset for each test.

		center = new PostOffice();
		sneha = Person.getInstance("Sneha");
		swati = Person.getInstance("Swati");
		vimlesh = Person.getInstance("Vimlesh");
		madhuri = Person.getInstance("Madhuri");
	}

	@Test
	public void testReceiveMessages() {
		// When the recipient receives messages
		// 1. adds the message to the unreadMessagesList
		// 2. recipient is notified that they have unread messages

		Message message = new Message(sneha, swati, "Test", "I am making test cases.");
		swati.receiveMessages(message);

		assertTrue(swati.hasUnreadMessageAlert());
		assertEquals(1, swati.getUnreadMessages().getSize());
	}

	@Test
	public void testReadMessages() {
		// When the recipient reads the message
		// 1. reads each message and sends a receipt to the sender for each
		// message
		// 2. clears the Unread Messages list
		// 3. clears the Unread Messages Alert

		Message message1 = new Message(sneha, swati, "Test", "I am making test cases.");

		swati.receiveMessages(message1);
		swati.readMessages();

		assertTrue(sneha.getLastReceiptReceived().contains("receipt"));
		assertEquals(0, swati.getUnreadMessages().getSize());
		assertFalse(swati.hasUnreadMessageAlert());
	}

	@After
	public void tearDown() {
		// This resets each variable.

		sneha.clear();
		swati.clear();
		vimlesh.clear();
		madhuri.clear();
	}
}

package edu.cornell.sneha;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostOfficeTest {
	/** This has the suite of unit tests for the PostOffice class */

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
	public void testSendMessage() {
		// When a message is sent to the post office,
		// 1. the message is added to the messageList
		// 2. the receiver receives the message.
		// 3. and the receiver gets an Unread Message Alert

		Message message = new Message(sneha, swati, "Java", "I am taking CS 2110.");
		center.sendMessage(message);

		assertEquals(message, center.getMessageList().get(0));
		assertTrue(swati.hasUnreadMessageAlert());
		assertEquals(message, swati.getUnreadMessages().get(0));

	}

	@Test
	public void testSearchMessageBySender() {
		// When a message has been sent and when the user searches for a sender,
		// all the messages from that sender should be returned
		Message message1 = new Message(sneha, swati, "Test", "I am making test cases.");
		Message message2 = new Message(sneha, vimlesh, "Ithaca",
				"Ithaca is beautiful in the summer.");
		center.sendMessage(message1);
		center.sendMessage(message2);
		MessageList messagesFromSender = center.searchMessagesBySender("Sneha");

		assertEquals(2, messagesFromSender.getSize());
	}

	@Test
	public void testSearchMessageByReceiver() {
		// When a message has been received and when the user searches for a
		// receiver,
		// all the messages to that recipient should be returned
		Message message1 = new Message(sneha, swati, "Test", "I am making test cases.");
		Message message2 = new Message(sneha, vimlesh, "Ithaca",
				"Ithaca is beautiful in the summer.");
		center.sendMessage(message1);
		center.sendMessage(message2);
		MessageList messagesToSwati = center.searchMessagesByReceiver("Swati");
		MessageList messagesToVimlesh = center.searchMessagesByReceiver("Vimlesh");
		MessageList messagesToSneha = center.searchMessagesByReceiver("Sneha");

		assertEquals(1, messagesToSwati.getSize());
		assertEquals(0, messagesToSneha.getSize());
		assertEquals(1, messagesToVimlesh.getSize());
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

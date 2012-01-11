/**
 * 
 */
package de.fhb.twitterCalendar.server.threads;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.fhb.twitterCalendar.server.connector.DatabaseConnector;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * @author Tony
 * 
 */
public class ServerThreadTest {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	public static ServerThread server;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		server = new ServerThread();
		context.setImposteriser(ClassImposteriser.INSTANCE);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#compute()}.
	 */
	@Test
	public void testCompute() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#ServerThread()}
	 * .
	 */
	@Test
	public void testServerThread() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#processCommand(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testZeroProcessCommand() {
		server.database = context.mock(DatabaseConnector.class);
		final ReminderObject remindMock = context.mock(ReminderObject.class);

		context.checking(new Expectations() {
			{
				oneOf(server.database).databaseInsert("", "", null, 0);
				// allowing(server.database).databaseDelete(0);
				// allowing(server.database)
				// .databaseUpdate(0, null, null, null, 0);
				oneOf(remindMock).getCommand();
				will(returnValue(0));
				oneOf(remindMock).getUser();
				will(returnValue(""));
				oneOf(remindMock).getContent();
				will(returnValue(""));
				oneOf(remindMock).getDate();
				will(returnValue(null));
				oneOf(remindMock).getRepeat();
				will(returnValue(0));

			}
		});
		server.processCommand(remindMock);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#processCommand(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testOneProcessCommand() {
		server.database = context.mock(DatabaseConnector.class);
		final ReminderObject remindMock = context.mock(ReminderObject.class);

		context.checking(new Expectations() {
			{
				oneOf(server.database).databaseDelete(0);
				// allowing(server.database).databaseDelete(0);
				// allowing(server.database)
				// .databaseUpdate(0, null, null, null, 0);
				oneOf(remindMock).getCommand();
				will(returnValue(1));
				oneOf(remindMock).getId();

			}
		});
		server.processCommand(remindMock);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#processCommand(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testTwoProcessCommand() {
		server.database = context.mock(DatabaseConnector.class);
		final ReminderObject remindMock = context.mock(ReminderObject.class);

		context.checking(new Expectations() {
			{
				oneOf(server.database).databaseUpdate(0, "", "", null, 0);
				// allowing(server.database).databaseDelete(0);
				// allowing(server.database)
				// .databaseUpdate(0, null, null, null, 0);
				oneOf(remindMock).getCommand();
				will(returnValue(2));
				oneOf(remindMock).getId();
				will(returnValue(0));
				oneOf(remindMock).getUser();
				will(returnValue(""));
				oneOf(remindMock).getContent();
				will(returnValue(""));
				oneOf(remindMock).getDate();
				will(returnValue(null));
				oneOf(remindMock).getRepeat();
				will(returnValue(0));
			}
		});
		server.processCommand(remindMock);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#send(java.lang.Object)}
	 * .
	 */
	@Test
	public void testSend() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.ServerThread#recieve()}.
	 */
	@Test
	public void testRecieve() {
	}

}

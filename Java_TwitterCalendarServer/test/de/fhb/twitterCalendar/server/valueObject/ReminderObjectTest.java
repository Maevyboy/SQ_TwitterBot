/**
 * 
 */
package de.fhb.twitterCalendar.server.valueObject;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Tony Hoffmann 
 * @author Maciej Gorski
 *
 */
public class ReminderObjectTest {

	ReminderObject r = new ReminderObject();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getCommand()}
	 * .
	 */
	@Test
	public void testReminderObject() {
		r = new ReminderObject("test", "test2", new GregorianCalendar(3, 2, 1),
				0);
		assertEquals("test", r.user);
		assertEquals("test2", r.content);
		assertEquals(0, r.repeat);
		assertEquals(new GregorianCalendar(3, 2, 1), r.date);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getCommand()}
	 * .
	 */
	@Test
	public void testIdReminderObject() {
		r = new ReminderObject(1, "test", "test2", new GregorianCalendar(3, 2,
				1), 0);
		assertEquals(1, r.id);
		assertEquals("test", r.user);
		assertEquals("test2", r.content);
		assertEquals(0, r.repeat);
		assertEquals(new GregorianCalendar(3, 2, 1), r.date);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getCommand()}
	 * .
	 */
	@Test
	public void testGetCommand() {
		r.command = 0;
		assertEquals(0, r.getCommand());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setCommand(int)}
	 * .
	 */
	@Test
	public void testSetCommand() {
		r.setCommand(1);
		assertEquals(1, r.command);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getUser()}
	 * .
	 */
	@Test
	public void testGetUser() {
		r.user = "test";
		assertEquals("test", r.getUser());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setUser(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetUser() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getContent()}
	 * .
	 */
	@Test
	public void testGetContent() {
		r.content = "testtext";
		assertEquals("testtext", r.getContent());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setContent(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetContent() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getRepeat()}
	 * .
	 */
	@Test
	public void testGetRepeat() {
		r.repeat = 1;
		assertEquals(1, r.getRepeat());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setRepeat(int)}
	 * .
	 */
	@Test
	public void testSetRepeat() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getDate()}
	 * .
	 */
	@Test
	public void testGetDate() {
		r.date = new GregorianCalendar(1, 2, 3);
		assertEquals(new GregorianCalendar(1, 2, 3), r.getDate());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setDate(java.util.Calendar)}
	 * .
	 */
	@Test
	public void testSetDate() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#toString()}
	 * .
	 */
	@Test
	public void testToString() {

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#getId()}.
	 */
	@Test
	public void testGetId() {
		r.id = 123;
		assertEquals(123, r.getId());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.valueObject.ReminderObject#setId(int)}
	 * .
	 */
	@Test
	public void testSetId() {

	}

}

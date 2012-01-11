/**
 * 
 */
package de.fhb.twitterCalendar.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.fhb.twitterCalendar.client.TwitterClient;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * @author Tony
 * 
 */
public class ModelTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	protected Model model = new Model();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context.setImposteriser(ClassImposteriser.INSTANCE);
		model.client = context.mock(TwitterClient.class);
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#getMonth()}.
	 */
	@Test
	public void testGetMonth() {
		model.month = 6;
		assertEquals(model.month, model.getMonth());
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#setMonth(int)}.
	 */
	@Test
	public void testSetMonth() {
		model.setMonth(2);
		assertEquals(2, model.month);
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#getYear()}.
	 */
	@Test
	public void testGetYear() {
		model.year = 6;
		assertEquals(model.year, model.getYear());
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#setYear(int)}.
	 */
	@Test
	public void testSetYear() {
		model.setYear(2);
		assertEquals(2, model.year);
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#getDay()}.
	 */
	@Test
	public void testGetDay() {
		model.day = 6;
		assertEquals(model.day, model.getDay());
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#setDay(int)}.
	 */
	@Test
	public void testSetDay() {
		model.setDay(2);
		assertEquals(2, model.day);
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#getDate()}.
	 */
	@Test
	public void testGetDate() {
		Calendar c = new GregorianCalendar(model.year, model.month, model.day);
		assertTrue(c.compareTo(model.getDate()) == 0);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#setDate(int, int, int)}.
	 */
	@Test
	public void testSetDate() {
		model.setDate(3, 2, 5);
		assertEquals(3, model.day);
		assertEquals(2, model.month);
		assertEquals(5, model.year);
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#isLeap(int)}.
	 */
	@Test
	public void testIsLeap() {
		assertTrue(model.isLeap(2012));
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#isLeap(int)}.
	 */
	@Test
	public void testfalseIsLeap() {
		assertFalse(model.isLeap(2011));
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#reloadReminds()}.
	 */
	@Test
	public void testReloadReminds() {

		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).getReminds();

				}
			});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.reloadReminds();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#getRemindCount()}.
	 */
	@Test
	public void testNullGetRemindCount() {
		model.currentReminds = null;

		model.setDate(Calendar.getInstance().get(Calendar.DATE), Calendar
				.getInstance().get(Calendar.MONTH),
				Calendar.getInstance().get(Calendar.YEAR));
		assertEquals(null, model.getRemindCount());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#getRemindCount()}.
	 */
	@Test
	public void testGetRemindCount() {
		model.currentReminds = new ArrayList<ReminderObject>();
		Calendar c = Calendar.getInstance();
		c.set(2012, 0, 1);
		int[] a = new int[31];
		a[0] = 3;
		model.currentReminds.add(new ReminderObject("", "", c, 0));
		model.currentReminds.add(new ReminderObject("", "", c, 0));
		model.currentReminds.add(new ReminderObject("", "", c, 0));

		model.setDate(5, 0, 2012);
		assertArrayEquals(a, model.getRemindCount());
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#getReminds()}.
	 */
	@Test
	public void testGetReminds() {
		model.currentReminds = new ArrayList<ReminderObject>();
		Calendar c = Calendar.getInstance();
		c.set(2012, 0, 1);
		model.setDate(1, 0, 2012);
		ReminderObject r = new ReminderObject("", "", c, 0);
		ReminderObject r2 = new ReminderObject("", "", c, 0);
		ReminderObject r3 = new ReminderObject("", "", c, 0);
		model.currentReminds.add(r);
		model.currentReminds.add(r2);
		model.currentReminds.add(r3);

		Vector<ReminderObject> v = new Vector<ReminderObject>();
		v.add(r);
		v.add(r2);
		v.add(r3);

		assertArrayEquals(v.toArray(), model.getReminds().toArray());

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#getCurrentReminderObject()}.
	 */
	@Test
	public void testGetCurrentReminderObject() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#setCurrentReminderObject(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testSetCurrentReminderObject() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#sendUpdatedRemind()}.
	 */
	@Test
	public void testSendUpdatedRemind() {

		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).updateRemind(null);

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.sendUpdatedRemind();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#sendUpdatedRemind()}.
	 */
	@Test
	public void testFalseSendUpdatedRemind() {

		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).updateRemind(null);
					will(throwException(new IOException()));

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(model.sendUpdatedRemind());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#sendNewRemind()}.
	 */
	@Test
	public void testSendNewRemind() {

		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).sendRemind(null);

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.sendNewRemind();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#sendNewRemind()}.
	 */
	@Test
	public void testFalseSendNewRemind() {
		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).sendRemind(null);
					will(throwException(new IOException()));

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(model.sendNewRemind());
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#deleteRemind()}
	 * .
	 */
	@Test
	public void testDeleteRemind() {
		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).deleteRemind(null);

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.deleteRemind();
	}

	/**
	 * Test method for {@link de.fhb.twitterCalendar.model.Model#deleteRemind()}
	 * .
	 */
	@Test
	public void testFalseDeleteRemind() {
		try {
			context.checking(new Expectations() {
				{
					oneOf(model.client).deleteRemind(null);
					will(throwException(new IOException()));

				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertFalse(model.deleteRemind());
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#addToCurrentReminds(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testAddToCurrentReminds() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.model.Model#deleteOfCurrentReminds(de.fhb.twitterCalendar.server.valueObject.ReminderObject)}
	 * .
	 */
	@Test
	public void testDeleteOfCurrentReminds() {
	}

}

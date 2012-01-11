/**
 * 
 */
package de.fhb.twitterCalendar.server.threads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Tony
 * 
 */
public class DateCheckThreadTest {

	private static DateCheckThread dateChecker;
	private static final long DAY_IN_MILLIS = 86400000;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateChecker = new DateCheckThread();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#compute()}.
	 */
	@Test
	public void testCompute() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#check(java.sql.Date, int, java.sql.Date)}
	 * .
	 */
	@Test
	public void testNoneRepeatDateCheck() {

		assertEquals(true, dateChecker.check(new Date(Calendar.getInstance()
				.getTimeInMillis()), 3, null));

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#check(java.sql.Date, int, java.sql.Date)}
	 * .
	 */
	@Test
	public void testOneDayRepeatDateCheck() {

		assertEquals(
				true,
				dateChecker.check(new Date(Calendar.getInstance()
						.getTimeInMillis() - DAY_IN_MILLIS), 0, null));

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#check(java.sql.Date, int, java.sql.Date)}
	 * .
	 */
	@Test
	public void testThreeDaysRepeatDateCheck() {

		assertEquals(
				true,
				dateChecker.check(new Date(Calendar.getInstance()
						.getTimeInMillis() - DAY_IN_MILLIS * 3), 1, null));

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#check(java.sql.Date, int, java.sql.Date)}
	 * .
	 */
	@Test
	public void testSevenDaysRepeatDateCheck() {

		assertEquals(
				true,
				dateChecker.check(new Date(Calendar.getInstance()
						.getTimeInMillis() - DAY_IN_MILLIS * 7), 2, null));

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#check(java.sql.Date, int, java.sql.Date)}
	 * .
	 */
	@Test
	public void testOneDayRepeatDateLastSendCheck() {

		assertEquals(false, dateChecker.check(new Date(Calendar.getInstance()
				.getTimeInMillis() - DAY_IN_MILLIS), 0, new Date(Calendar
				.getInstance().getTimeInMillis())));

	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#delete(java.sql.Statement, int)}
	 * .
	 */
	@Test
	public void testDelete() {
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.server.threads.DateCheckThread#setLastDate(java.sql.Statement, java.util.Calendar, int)}
	 * .
	 */
	@Test
	public void testSetLastDate() {
	}

}

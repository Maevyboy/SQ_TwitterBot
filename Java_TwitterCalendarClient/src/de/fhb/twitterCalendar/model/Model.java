/**
 * 
 */
package de.fhb.twitterCalendar.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import de.fhb.twitterCalendar.client.TwitterClient;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * 
 * @author Tony Hoffmann & Maciej Gorski
 * 
 */
public class Model {

	public final static String[] MONTH = { "Januar", "Februar", "März",
			"April", "Mai", "Juni", "Juli", "August", "September", "Oktober",
			"November", "Dezember" };

	public final static String[] DAYS_OF_WEEK = new String[] { "Sonntag",
			"Montag", "Diensttag", "Mittwoch", "Donnerstag", "Freitag",
			"Samstag" };

	public final static int DAYS_OF_MONTH[] = { 31, 28, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 };

	public final static String[] REPEAT_CHOICES = { "jeden Tag",
			"jeden 3. Tag", "jede Woche", "keine Wiederholung" };

	protected int month;
	protected int year;
	protected int day;

	protected ReminderObject currentReminderObject;

	protected ArrayList<ReminderObject> currentReminds;

	protected TwitterClient client;

	/**
	 * 
	 */
	public Model() {
		client = new TwitterClient("localhost", 9876);
	}

	/**
	 * 
	 * @return
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 
	 * @return
	 */
	public int getDay() {
		return day;
	}

	/**
	 * 
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * 
	 * @return
	 */
	public Calendar getDate() {
		Calendar c = new GregorianCalendar(year, month, day);
		return c;
	}

	/**
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	/**
	 * 
	 * @param year
	 * @return
	 */
	public boolean isLeap(int year) {
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		return false;
	}

	public boolean reloadReminds() {
		try {
			currentReminds = client.getReminds();
		} catch (UnknownHostException e) {
			return false;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public int[] getRemindCount() {
		if (currentReminds == null)
			return null;

		int[] counts;
		if (isLeap(year) && month == 1) {
			counts = new int[DAYS_OF_MONTH[month + 1]];
		} else {
			counts = new int[DAYS_OF_MONTH[month]];
		}

		for (ReminderObject r : currentReminds) {
			for (int i = 1; i <= counts.length; i++) {
				// System.out.println(i + "." + month + "." + year + "---"
				// + r.getDate().get(Calendar.DATE) + "."
				// + r.getDate().get(Calendar.MONTH) + "."
				// + r.getDate().get(Calendar.YEAR));
				if (i == r.getDate().get(Calendar.DATE)
						&& month == r.getDate().get(Calendar.MONTH)
						&& year == r.getDate().get(Calendar.YEAR)) {

					counts[i - 1]++;
				}
			}
		}
		return counts;
	}

	/**
	 * 
	 * @param day
	 * @return
	 */
	public Vector<ReminderObject> getReminds() {
		if (currentReminds == null)
			return null;

		Vector<ReminderObject> items = new Vector<ReminderObject>();
		for (ReminderObject r : currentReminds) {
			if (day == r.getDate().get(Calendar.DATE)
					&& month == r.getDate().get(Calendar.MONTH)
					&& year == r.getDate().get(Calendar.YEAR)) {
				items.add(r);
			}
		}

		return items;
	}

	/**
	 * @return the currentReminderObject
	 */
	public ReminderObject getCurrentReminderObject() {
		return currentReminderObject;
	}

	/**
	 * @param currentReminderObject
	 *            the currentReminderObject to set
	 */
	public void setCurrentReminderObject(ReminderObject currentReminderObject) {
		this.currentReminderObject = currentReminderObject;
	}

	public boolean sendUpdatedRemind() {
		try {
			client.updateRemind(currentReminderObject);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean sendNewRemind() {
		try {
			client.sendRemind(currentReminderObject);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public boolean deleteRemind() {
		try {
			client.deleteRemind(currentReminderObject);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public void addToCurrentReminds(ReminderObject r) {
		currentReminds.add(r);
	}

	public void deleteOfCurrentReminds(ReminderObject r) {
		currentReminds.remove(r);
	}

}

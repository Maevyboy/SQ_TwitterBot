/**
 * 
 */
package de.fhbrandenburg.kalenderapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.fhb.twitterCalendar.server.constants.DatabaseConstants;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * @author Tony
 * 
 */
public class InsertTestData {

	private static String DATE_FORMAT = "yyyy-MM-dd";
	public static ArrayList<ReminderObject> testData = new ArrayList<ReminderObject>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testData.add(new ReminderObject("TestUser", "Hy was geht ab",
				ReminderObject.REPEATABLE_NONE, new Date(2012, 0, 19)));

		testData.add(new ReminderObject("TestUser", "Da bin ich wieder",
				ReminderObject.REPEATABLE_ONEDAY, new Date(2012, 1, 0)));

		testData.add(new ReminderObject("TestUser2", "Hy was geht ab",
				ReminderObject.REPEATABLE_THREEDAYS, new Date(2012, 1, 10)));

		testData.add(new ReminderObject("TestUser2", "Da bin ich wieder",
				ReminderObject.REPEATABLE_SEVENDAYS, new Date(2012, 1, 2)));

		testData.add(new ReminderObject("TestUser4", "Hy was geht ab",
				ReminderObject.REPEATABLE_THREEDAYS, new Date(2012, 0, 19)));

		testData.add(new ReminderObject("TestUser3", "Da bin ich wieder",
				ReminderObject.REPEATABLE_THREEDAYS, new Date(2012, 0, 12)));

		testData.add(new ReminderObject("TestUser4", "Hy was geht ab",
				ReminderObject.REPEATABLE_NONE, new Date(2012, 0, 15)));

		testData.add(new ReminderObject("TestUser", "Da bin ich wieder",
				ReminderObject.REPEATABLE_NONE, new Date(2012, 0, 16)));

		Connection con;
		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement mystatement = con.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

			for (ReminderObject r : testData) {
				mystatement
						.executeUpdate("INSERT INTO `sqdb`.`remind` (`id`, `user`, `content`, `date`, `repeat`) VALUES (NULL, '"
								+ r.getFuser()
								+ "', '"
								+ r.getContent()
								+ "', '"
								+ sdf.format(r.getDatum())
								+ "', '"
								+ r.getArt() + "')");
			}
			mystatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

/**
 * 
 */
package de.fhb.twitterCalendar.server.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import de.fhb.twitterCalendar.server.constants.DatabaseConstants;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * @author Tony
 * 
 */
public class DatabaseConnector {

	protected Connection con;

	public DatabaseConnector() {

	}

	public synchronized ArrayList<ReminderObject> databaseGet() {
		ArrayList<ReminderObject> databaseData = new ArrayList<ReminderObject>();
		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement sqlStatement = con.createStatement();
			databaseData = new ArrayList<ReminderObject>();

			ResultSet r = sqlStatement
					.executeQuery("SELECT * FROM `sqdb`.`remind`");

			while (r.next()) {
				Calendar c = Calendar.getInstance();
				c.setTime(r.getDate(4));
				ReminderObject ro = new ReminderObject(r.getInt(1),
						r.getString(2), r.getString(3), c, r.getInt(5));
				databaseData.add(ro);
			}

			sqlStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return databaseData;
	}

	public synchronized boolean databaseInsert(String user, String content,
			Calendar date, int repeat) {
		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement sqlStatement = con.createStatement();
			java.sql.Date sqlDate = new java.sql.Date(date.getTimeInMillis());

			sqlStatement
					.executeUpdate("INSERT INTO `sqdb`.`remind` (`id`, `user`, `content`, `date`, `repeat`) VALUES (NULL, '"
							+ user
							+ "', '"
							+ content
							+ "', '"
							+ sqlDate
							+ "', '" + repeat + "')");
			sqlStatement.close();

		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	public synchronized boolean databaseUpdate(int id, String user,
			String content, Calendar date, int repeat) {
		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement sqlStatement = con.createStatement();
			java.sql.Date sqlDate = new java.sql.Date(date.getTimeInMillis());

			sqlStatement.executeUpdate("UPDATE `sqdb`.`remind` SET `user`='"
					+ user + "', `content`='" + content + "', `date`='"
					+ sqlDate + "', `repeat`='" + repeat + "' WHERE `id`='"
					+ id + "'");
			sqlStatement.close();

		} catch (SQLException e) {
			return false;
		}

		return true;
	}

	public synchronized boolean databaseDelete(int id) {
		System.out.println(id);
		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement sqlStatement = con.createStatement();

			sqlStatement
					.executeUpdate("DELETE FROM `sqdb`.`remind` WHERE id ='"
							+ id + "'");
			sqlStatement.close();

		} catch (SQLException e) {
			return false;
		}

		return true;
	}

}

package de.fhb.twitterCalendar.server.threads;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import de.fhb.twitterCalendar.server.connector.TwitterConnector;
import de.fhb.twitterCalendar.server.constants.DatabaseConstants;

public class DateCheckThread extends Thread {

	protected long dateDistance;
	protected Calendar actualDay;
	protected Calendar targetDay;
	/**
	 * 
	 * @author Tony Hoffmann 
	 * @author Maciej Gorski
	 *
	 */
	public DateCheckThread() {
	}

	@Override
	public void run() {

		while (true) {

			compute();
			try {
				sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected void compute() {
		Connection con = null;

		try {
			con = DriverManager.getConnection(DatabaseConstants.DATABASE_URL,
					DatabaseConstants.USER, DatabaseConstants.PASS);
			Statement mystatement = con.createStatement();
			ResultSet mrs = mystatement
					.executeQuery("SELECT `user`, `date`, `repeat`, content, id, lastSend From `sqdb`.`remind`");

			while (mrs.next()) {

				if (check(mrs.getDate(2), mrs.getInt(3), mrs.getDate(6))) {

					TwitterConnector twitt = new TwitterConnector(
							mrs.getString(1), mrs.getString(4));

					twitt.readKeys();
					twitt.twitterMessageSend();

					System.out.println(mrs.getInt(5));
					if (mrs.getInt(3) != 3) {

						setLastDate(con.createStatement(), actualDay,
								mrs.getInt(5));
					} else {
						System.out.println("löschen");
						delete(con.createStatement(), mrs.getInt(5));
					}

				}

				if (mrs.getInt(3) == 3
						&& (actualDay.getTimeInMillis() - mrs.getDate(2)
								.getTime()) / (1000 * 60 * 60 * 24) > 0) {
					System.out.println("löschen");
					delete(con.createStatement(), mrs.getInt(5));
				}

			}
			mystatement.close();
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
	}

	protected boolean check(Date date, int repeat, Date lastSend) {
		actualDay = Calendar.getInstance();
		targetDay = Calendar.getInstance();
		boolean nowLastSend = false;

		targetDay.setTime(date);
		dateDistance = (actualDay.getTimeInMillis() - targetDay
				.getTimeInMillis()) / (1000 * 60 * 60 * 24);

		System.out.println(System.getProperty("line.separator"));
		System.out.println("Target: " + targetDay.get(Calendar.DATE) + "."
				+ targetDay.get(Calendar.MONTH) + "."
				+ targetDay.get(Calendar.YEAR));
		System.out.println("Actual: " + actualDay.get(Calendar.DATE) + "."
				+ actualDay.get(Calendar.MONTH) + "."
				+ actualDay.get(Calendar.YEAR));
		System.out.println("----------------------------------------");
		System.out.println("Tagesabstand: " + dateDistance);

		if (lastSend != null) {
			nowLastSend = (actualDay.getTimeInMillis() - lastSend.getTime())
					/ (1000 * 60 * 60 * 24) == 0;
			System.out.println("LastSend: " + lastSend);
			System.out.println("nowLastSend: " + nowLastSend);
		}

		if (repeat != 3) {

			if (dateDistance >= 0 && !nowLastSend) {

				switch (repeat) {
				case 0:
					System.out.println("Modulo: " + dateDistance % 1);
					if ((dateDistance % 1) == 0) {
						System.out.println("send oneDay");
						return true;
					}
					break;
				case 1:
					System.out.println("Modulo: " + dateDistance % 3);
					if ((dateDistance % 3) == 0) {
						System.out.println("send threeDays");
						return true;
					}
					break;
				case 2:
					System.out.println("Modulo: " + dateDistance % 7);
					if ((dateDistance % 7) == 0) {
						System.out.println("send sevenDays");
						return true;
					}
					break;
				}
			}
		} else {
			if (dateDistance == 0) {
				System.out.println("send oneTime");
				return true;
			}
		}
		return false;
	}

	protected boolean delete(Statement statement, int id) {
		try {
			statement.executeUpdate("DELETE FROM `sqdb`.`remind` WHERE id ='"
					+ id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	protected boolean setLastDate(Statement statement, Calendar date, int id) {
		try {
			statement.executeUpdate("UPDATE `sqdb`.`remind` SET `lastSend`='"
					+ new java.sql.Date(date.getTimeInMillis())
					+ "' WHERE `id`='" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

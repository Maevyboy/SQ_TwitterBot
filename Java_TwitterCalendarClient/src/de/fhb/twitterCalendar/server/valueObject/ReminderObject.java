package de.fhb.twitterCalendar.server.valueObject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ReminderObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int COMMAND_INSERT = 0;
	public final static int COMMAND_DELETE = 1;
	public final static int COMMAND_UPDATE = 2;
	public final static int COMMAND_GET = 3;

	public final static int REPEATABLE_ONEDAY = 0;
	public final static int REPEATABLE_THREEDAYS = 1;
	public final static int REPEATABLE_SEVENDAYS = 2;
	public final static int REPEATABLE_NONE = 3;

	private int id;
	private String user;
	private String content;
	private int repeat;
	private Calendar date;
	private int command;

	/**
	 * 
	 */
	public ReminderObject() {
	}

	/**
	 * 
	 * @param fuser
	 * @param content
	 * @param art
	 * @param datum
	 */
	public ReminderObject(String user, String content, Calendar date, int repeat) {
		this.user = user;
		this.content = content;
		this.repeat = repeat;
		this.date = date;
	}
	
	public ReminderObject(int id, String user, String content, Calendar date, int repeat) {
		this.id = id;
		this.user = user;
		this.content = content;
		this.repeat = repeat;
		this.date = date;
	}

	/**
	 * 
	 * @param command
	 */
	public ReminderObject(int command) {
		this.command = command;
	}

	/**
	 * 
	 * @return
	 */
	public int getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(int command) {
		this.command = command;
	}

	/**
	 * 
	 * @return
	 */
	public String getUser() {
		return user;
	}

	/**
	 * 
	 * @param fuser
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return
	 */
	public int getRepeat() {
		return repeat;
	}

	/**
	 * 
	 * @param art
	 */
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	/**
	 * 
	 * @return
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * 
	 * @param datum
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "An: "+user + " Inhalt: "+content;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}

package de.fhb.twitterCalendar.server.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import de.fhb.twitterCalendar.server.connector.DatabaseConnector;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

public class ServerThread extends Thread {

	protected ServerSocket serverSocket;
	protected Socket socket;
	protected DatabaseConnector database;
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;

	public ServerThread() {
		database = new DatabaseConnector();
	}

	@Override
	public void run() {

		compute();

	}

	protected void compute() {
		try {

			serverSocket = new ServerSocket(9876);

			while (true) {
				socket = serverSocket.accept();
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());

				processCommand(recieve());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	protected void processCommand(ReminderObject r) {
		switch (r.getCommand()) {
		case ReminderObject.COMMAND_INSERT:
			database.databaseInsert(r.getUser(), r.getContent(), r.getDate(),
					r.getRepeat());
			break;
		case ReminderObject.COMMAND_DELETE:
			database.databaseDelete(r.getId());
			break;
		case ReminderObject.COMMAND_UPDATE:
			database.databaseUpdate(r.getId(), r.getUser(), r.getContent(),
					r.getDate(), r.getRepeat());
			break;
		case ReminderObject.COMMAND_GET:
			send(database.databaseGet());
			break;
		default:
			break;
		}
	}

	protected boolean send(Object object) {
		try {
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	protected ReminderObject recieve() {
		try {
			ReminderObject ro = (ReminderObject) ois.readObject();
			return ro;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}

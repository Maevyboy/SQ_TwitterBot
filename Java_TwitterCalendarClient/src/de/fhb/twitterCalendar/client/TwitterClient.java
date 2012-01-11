/**
 * 
 */
package de.fhb.twitterCalendar.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * 
 * @author Tony Hoffmann and Maciej Gorski
 *
 */
public class TwitterClient {
	
	protected Socket server;
	protected String host;
	protected int port;

	/**
	 * 
	 * @param host
	 * @param port
	 */
	public TwitterClient(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	/**
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<ReminderObject> getReminds() throws UnknownHostException, IOException, ClassNotFoundException{
		
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try {
			
			server = new Socket(host, port);
			
			oos = new ObjectOutputStream(
					server.getOutputStream());

			ois = new ObjectInputStream(
					server.getInputStream());
			
			oos.writeObject(new ReminderObject(ReminderObject.COMMAND_GET));

			ArrayList<ReminderObject> ro = (ArrayList<ReminderObject>) ois.readObject();
			
			oos.close();
			ois.close();
			server.close();
			return ro;
			
		} catch (UnknownHostException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		}
	
	}
	
	/**
	 * 
	 * @param ro
	 * @throws IOException 
	 */
	public void sendRemind(ReminderObject ro) throws IOException{
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try {
			
			server = new Socket(host, port);
			
			oos = new ObjectOutputStream(
					server.getOutputStream());

			ois = new ObjectInputStream(
					server.getInputStream());
			
			ro.setCommand(ReminderObject.COMMAND_INSERT);
			oos.writeObject(ro);
			
			oos.close();
			ois.close();
			server.close();
			
		} catch (UnknownHostException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param ro
	 * @throws IOException 
	 */
	public void deleteRemind(ReminderObject ro) throws IOException{
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try {
			
			server = new Socket(host, port);
			
			oos = new ObjectOutputStream(
					server.getOutputStream());

			ois = new ObjectInputStream(
					server.getInputStream());
			
			ro.setCommand(ReminderObject.COMMAND_DELETE);
			oos.writeObject(ro);
			
			oos.close();
			ois.close();
			server.close();
			
		} catch (UnknownHostException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param ro
	 * @throws IOException
	 */
	public void updateRemind(ReminderObject ro) throws IOException{
		ObjectInputStream ois;
		ObjectOutputStream oos;
		try {
			
			server = new Socket(host, port);
			
			oos = new ObjectOutputStream(
					server.getOutputStream());

			ois = new ObjectInputStream(
					server.getInputStream());
			
			ro.setCommand(ReminderObject.COMMAND_UPDATE);
			oos.writeObject(ro);
			
			oos.close();
			ois.close();
			server.close();
			
		} catch (UnknownHostException e) {
			throw e;
		}
		catch (IOException e) {
			throw e;
		}
	}

}

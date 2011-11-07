package twitterbot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class SendMessage {
	
	private Twitter twitterMessage;
		
	public SendMessage(Twitter twitter, String fname){
		this.twitterMessage = twitter;
		String name = readLine();
		createFriendship(name);		
	}
	
	//Das mögliche followen eines Users
	
	public void createFriendship(String fname){
		try{
			
			twitterMessage.createFriendship(fname, true);
			System.out.println("Freund wurde hinzugefügt: "+fname);
		}catch(TwitterException te){
			System.out.println("Freund konnte nicht hinzugefügt werden :(");
			System.exit(0);
		}
	}
	
	// Eingabe des Users um zu followen
	
	public String readLine(){
		String s = "";
		try{
			InputStreamReader namereader = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(namereader);
			System.out.println("Geben Sie bitte den Namen der Person an, der sie followen wollen.");
			s = in.readLine();
		}catch(Exception e){
			System.out.println("Es ist ein Fehler aufgetreten beim followen");
		}
		
		return s;
	}
}

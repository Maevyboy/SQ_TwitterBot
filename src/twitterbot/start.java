package twitterbot;
import java.util.Timer;

import twitter4j.Twitter;


public class start {

	public static void main(String[] args){
		
		/* Startparameter */
		
		String consKey ="qNxVhvb0t4HAOIYbC7tA";
		String consSecret ="vAxzm9ENfvoOtC1zlWCOL99WIAyGvhD7ebMwngJpf6s";
		String token = "403771163-PcB2RvplspIvLsiWXplD07aYlOyq3W4qDeT1lkSB";
		String secretToken= "3FgHbmNFnIBpySw9q21h3ODAUwUMzcY7DjoksOE";
		
		
		//// OAuthLogin
		OAutoLogin twitterLog = new OAutoLogin(consKey, consSecret, token, secretToken);
		Twitter account = twitterLog.getTwitter();

		//// Initierung		
		new SendMessage(account,"TestObjekt1");

		//// Listening Wiederholung
		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(account), 0,5000);
		
	}
	
}

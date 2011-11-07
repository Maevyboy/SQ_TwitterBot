package twitterbot;
import java.util.Timer;

import twitter4j.Twitter;


public class start {

	public static void main(String[] args){
		String consKey ="qNxVhvb0t4HAOIYbC7tA";
		String consSecret ="vAxzm9ENfvoOtC1zlWCOL99WIAyGvhD7ebMwngJpf6s";
		String token = "403771163-PcB2RvplspIvLsiWXplD07aYlOyq3W4qDeT1lkSB";
		String secretToken= "3FgHbmNFnIBpySw9q21h3ODAUwUMzcY7DjoksOE";
		
		
		//// DELETE
		OAutoLogin twitterLog = new OAutoLogin(consKey, consSecret, token, secretToken);
		Twitter account = twitterLog.getTwitter();

		//// DELETE		
		SendMessage follower = new SendMessage(account,"TestObjekt1");

		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(account), 0,5000);
		
	}
	
}

package twitterbot;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class OAutoLogin {
	private Twitter twitterContainer;


	public OAutoLogin(String consumerKey, String consumerSecret, String token, String secretToken){
		
		// Aufbereitung der Login Daten zum CB und zum akzeptieren durch OAuth
		
		ConfigurationBuilder myCb = new ConfigurationBuilder();
		myCb.setDebugEnabled(true).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret).setOAuthAccessToken(token).setOAuthAccessTokenSecret(secretToken);
		
		TwitterFactory tf = new TwitterFactory(myCb.build());
		twitterContainer = tf.getInstance();
	}
	
	public Twitter getTwitter(){
		return twitterContainer;
		
	}
}

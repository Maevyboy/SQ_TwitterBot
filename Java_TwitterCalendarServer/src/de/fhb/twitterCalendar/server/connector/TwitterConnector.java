package de.fhb.twitterCalendar.server.connector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * 
 * @author Tony Hoffmann 
 * @author Maciej Gorski
 *
 */
public class TwitterConnector {

	String[] keys = new String[4];
	int i;
	protected Twitter account;
	protected String fuser;
	protected String content;

	public TwitterConnector(String fuser, String content) {
		this.fuser = fuser;
		this.content = content;
	}

	public void readKeys() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("keys.txt"));
			String lineReader = null;
			i = 0;

			while ((lineReader = in.readLine()) != null) {
				keys[i] = lineReader;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void twitterMessageSend() {
		ConfigurationBuilder myCb = new ConfigurationBuilder();
		myCb.setDebugEnabled(true).setOAuthConsumerKey(keys[0])
				.setOAuthConsumerSecret(keys[1]).setOAuthAccessToken(keys[2])
				.setOAuthAccessTokenSecret(keys[3]);
		TwitterFactory tf = new TwitterFactory(myCb.build());
		account = tf.getInstance();
		try {
			account.createFriendship(fuser, true);
			account.updateStatus("@" + fuser + " " + content);

		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}

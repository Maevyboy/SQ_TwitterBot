package de.fhb.twitterCalendar.server.connector;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Tony Hoffmann 
 * @author Maciej Gorski
 *
 */
public class TwitterConnectorTest {

	private static TwitterConnector Twitterchecker;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Twitterchecker = new TwitterConnector(null, null);
	}
	
	
	@Test
	public void testReadKeys(){
		String[] testkeys = new String[4];
		testkeys[0]= "qNxVhvb0t4HAOIYbC7tA";
		testkeys[1]= "vAxzm9ENfvoOtC1zlWCOL99WIAyGvhD7ebMwngJpf6s";
		testkeys[2]= "403771163-PcB2RvplspIvLsiWXplD07aYlOyq3W4qDeT1lkSB";
		testkeys[3]= "3FgHbmNFnIBpySw9q21h3ODAUwUMzcY7DjoksOE";
		
		try {
			
			
			for(int i=0;i<=testkeys.length;i++){
				Twitterchecker.readKeys();
				assertTrue(testkeys[i].equals(Twitterchecker.keys[i]));	
			}

		}catch (Exception e) {
				// TODO: handle exception
			}
		
	}
	
}

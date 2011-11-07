package twitterbot;
import java.util.List;
import java.util.TimerTask;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MyTimeTask extends TimerTask {
	
	
	private final static String answer = " Danke für den Tweet :)";
	private String fUser;
	private Twitter timeTwitter;
	
	
	public MyTimeTask(Twitter timetwitter){
		this.timeTwitter = timetwitter;
		
	}

	// Abschicken der Message und Excpetion
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			List<Status> status;
			status = timeTwitter.getMentions();
			for(Status s : status){
				
					fUser = "@"+s.getUser().getScreenName()+ answer;
					timeTwitter.updateStatus(fUser);
					System.out.println("Message wurde abgeschickt");				
			}
			
			
		}catch(TwitterException te){
			System.out.println("Status kann nicht erneut geschrieben");			
		}
	}
}

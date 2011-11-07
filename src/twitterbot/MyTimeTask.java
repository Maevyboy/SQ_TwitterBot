package twitterbot;
import java.util.List;
import java.util.TimerTask;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MyTimeTask extends TimerTask {
	
	
	private final static String answer = " Bot Test Antwort 1";
	private String fUser;
	private Twitter timetwitter;
	
	
	public MyTimeTask(Twitter timetwitter){
		this.timetwitter = timetwitter;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			List<Status> status;
			status = timetwitter.getMentions();
			for(Status s : status){
				
					fUser = "@"+s.getUser().getScreenName()+ answer;
					timetwitter.updateStatus(fUser);
					System.out.println("Message wurde abgeschickt");				
			}
			
			
		}catch(TwitterException te){
			System.out.println("Status kann nicht erneut geschrieben");			
		}
	}
}

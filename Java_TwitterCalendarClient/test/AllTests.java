import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.fhb.twitterCalendar.server.connector.TwitterConnectorTest;
import de.fhb.twitterCalendar.server.threads.DateCheckThreadTest;
import de.fhb.twitterCalendar.server.threads.ServerThreadTest;

@RunWith(Suite.class)
@SuiteClasses({ DateCheckThreadTest.class, TwitterConnectorTest.class,
		ServerThreadTest.class })
public class AllTests {
}

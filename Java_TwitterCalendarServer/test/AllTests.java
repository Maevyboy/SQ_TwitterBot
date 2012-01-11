import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.fhb.twitterCalendar.server.connector.TwitterConnectorTest;
import de.fhb.twitterCalendar.server.threads.DateCheckThreadTest;
import de.fhb.twitterCalendar.server.threads.ServerThreadTest;
import de.fhb.twitterCalendar.server.valueObject.ReminderObjectTest;

@RunWith(Suite.class)
@SuiteClasses({ DateCheckThreadTest.class, TwitterConnectorTest.class,
		ServerThreadTest.class, ReminderObjectTest.class })
public class AllTests {
}

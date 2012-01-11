
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.fhb.twitterCalendar.controller.Controller;
import de.fhb.twitterCalendar.controller.ControllerTest;
import de.fhb.twitterCalendar.model.ModelTest;

@RunWith(Suite.class)
@SuiteClasses({ ModelTest.class, ControllerTest.class })
public class AllTests {

}

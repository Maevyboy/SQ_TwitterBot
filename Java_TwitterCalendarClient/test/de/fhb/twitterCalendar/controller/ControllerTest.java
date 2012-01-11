/**
 * 
 */
package de.fhb.twitterCalendar.controller;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.fhb.twitterCalendar.model.Model;
import de.fhb.twitterCalendar.view.View;

/**
 * @author Tony
 * 
 */
public class ControllerTest {
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	protected Controller con;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context.setImposteriser(ClassImposteriser.INSTANCE);
		Model modelMock = context.mock(Model.class);
		View viewMock = context.mock(View.class);
		con = new Controller(modelMock, viewMock);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#init()}.
	 */
	@Test
	public void testInit() {
		context.checking(new Expectations() {
			{
				oneOf(con.model).setDate(0, 0, 0);
				oneOf(con.view.yearChoice.getSelectedItem());
				will(returnValue(2012));
			}
		});
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#reload()}.
	 */
	@Test
	public void testReload() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#addListener()}.
	 */
	@Test
	public void testAddListener() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#recompute()}.
	 */
	@Test
	public void testRecompute() {
		fail("Not yet implemented");
	}

}

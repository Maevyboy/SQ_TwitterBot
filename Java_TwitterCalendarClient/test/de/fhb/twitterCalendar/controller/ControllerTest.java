/**
 * 
 */
package de.fhb.twitterCalendar.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.fhb.twitterCalendar.controller.Controller.CancelRemindListener;
import de.fhb.twitterCalendar.controller.Controller.DayListener;
import de.fhb.twitterCalendar.controller.Controller.DeleteRemindListener;
import de.fhb.twitterCalendar.controller.Controller.EditRemindListener;
import de.fhb.twitterCalendar.controller.Controller.MonthListener;
import de.fhb.twitterCalendar.controller.Controller.NewRemindListener;
import de.fhb.twitterCalendar.controller.Controller.OkRemindChoiceListener;
import de.fhb.twitterCalendar.controller.Controller.OkRemindListener;
import de.fhb.twitterCalendar.controller.Controller.ReloadListener;
import de.fhb.twitterCalendar.controller.Controller.YearListener;
import de.fhb.twitterCalendar.model.Model;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;
import de.fhb.twitterCalendar.view.RemindChoiceDialog;
import de.fhb.twitterCalendar.view.RemindDialog;
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
		con.reminderChoiceDialog = context.mock(RemindChoiceDialog.class);
		con.remindDialog = context.mock(RemindDialog.class);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#init()}.
	 */
	@Test
	public void testInit() {
		// context.checking(new Expectations() {
		// {
		// oneOf(con.model).setDate(0, 0, 0);
		// oneOf(con.view.yearChoice.getSelectedItem());
		// will(returnValue(2012));
		// }
		// });
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#reload()}.
	 */
	@Test
	public void testReload() {
		context.checking(new Expectations() {
			{
				oneOf(con.model).reloadReminds();
				will(returnValue(true));

			}
		});

		con.reload();
		assertTrue(con.connectionSucces);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#reload()}.
	 */
	@Test
	public void testFalseReload() {

		context.checking(new Expectations() {
			{
				oneOf(con.model).reloadReminds();
				will(returnValue(false));
				oneOf(con.view).showDialog();

			}
		});

		con.reload();
		assertFalse(con.connectionSucces);
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#addListener()}.
	 */
	@Test
	public void testAddListener() {
		context.checking(new Expectations() {
			{
				oneOf(con.view).setDaysListener(with(any(DayListener.class)));
				oneOf(con.view)
						.setMonthListener(with(any(MonthListener.class)));
				oneOf(con.view).setYearListener(with(any(YearListener.class)));
				oneOf(con.view).setReloadListener(
						with(any(ReloadListener.class)));

				oneOf(con.reminderChoiceDialog).setListener(
						with(any(OkRemindChoiceListener.class)),
						with(any(NewRemindListener.class)),
						with(any(EditRemindListener.class)));
				oneOf(con.remindDialog).setListener(
						with(any(OkRemindListener.class)),
						with(any(DeleteRemindListener.class)),
						with(any(CancelRemindListener.class)));
			}
		});

		con.addListener();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#recompute()}.
	 */
	@Test
	public void testRecompute() {
		final int[] arr = new int[31];
		arr[0] = 3;
		final JButton[][] dayButtons = new JButton[6][7];

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++) {
				dayButtons[i][j] = new JButton("");
			}
		context.checking(new Expectations() {
			{

				oneOf(con.model).getRemindCount();
				will(returnValue(arr));
				allowing(con.model).getYear();
				will(returnValue(2012));
				allowing(con.model).getMonth();
				will(returnValue(0));
				allowing(con.model).getDay();
				will(returnValue(1));
				allowing(con.model).isLeap(2012);
				will(returnValue(false));

				allowing(con.view).getDayButtons();
				will(returnValue(dayButtons));

				ignoring(con.view).repaint();

			}
		});

		con.recompute();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#recompute()}.
	 */
	@Test
	public void testLeapRecompute() {
		final int[] arr = new int[31];
		arr[0] = 3;
		final JButton[][] dayButtons = new JButton[6][7];

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++) {
				dayButtons[i][j] = new JButton("");
			}
		context.checking(new Expectations() {
			{

				oneOf(con.model).getRemindCount();
				will(returnValue(arr));
				allowing(con.model).getYear();
				will(returnValue(2012));
				allowing(con.model).getMonth();
				will(returnValue(1));
				allowing(con.model).getDay();
				will(returnValue(1));
				allowing(con.model).isLeap(2012);
				will(returnValue(true));

				allowing(con.view).getDayButtons();
				will(returnValue(dayButtons));

				ignoring(con.view).repaint();

			}
		});

		con.recompute();
	}

	/**
	 * Test method for
	 * {@link de.fhb.twitterCalendar.controller.Controller#recompute()}.
	 */
	@Test(expected = Exception.class)
	public void testNullRecompute() {
		con.model = null;
		con.view = null;
		final int[] arr = new int[31];
		arr[0] = 3;
		final JButton[][] dayButtons = new JButton[6][7];

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++) {
				dayButtons[i][j] = new JButton("");
			}
		context.checking(new Expectations() {
			{

				oneOf(con.model).getRemindCount();
				// will(returnValue(arr));
				// allowing(con.model).getYear();
				// will(returnValue(2012));
				// allowing(con.model).getMonth();
				// will(returnValue(1));
				// allowing(con.model).getDay();
				// will(returnValue(1));
				// allowing(con.model).isLeap(2012);
				// will(returnValue(true));
				//
				// allowing(con.view).getDayButtons();
				// will(returnValue(dayButtons));
				//
				// ignoring(con.view).repaint();

			}
		});

		con.recompute();
	}

}

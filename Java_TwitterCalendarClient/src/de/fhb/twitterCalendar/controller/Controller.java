/**
 * 
 */
package de.fhb.twitterCalendar.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import de.fhb.twitterCalendar.model.Model;
import de.fhb.twitterCalendar.server.valueObject.ReminderObject;
import de.fhb.twitterCalendar.view.RemindChoiceDialog;
import de.fhb.twitterCalendar.view.RemindDialog;
import de.fhb.twitterCalendar.view.View;

/**
 * @author Tony
 * 
 */
public class Controller {

	protected Model model;
	protected View view;

	protected RemindChoiceDialog reminderChoiceDialog;
	protected RemindDialog remindDialog;

	protected Calendar calendar = new GregorianCalendar();

	protected boolean connectionSucces;

	/**
	 * 
	 */
	public Controller(Model m, View v) {

		this.model = m;
		this.view = v;

		init();

	}

	/**
	 * 
	 */
	protected void init() {
		reminderChoiceDialog = new RemindChoiceDialog(view, "Errinnerung", true);
		remindDialog = new RemindDialog(reminderChoiceDialog, "Errinnerung",
				true);
		remindDialog.setRepeatItems(Model.REPEAT_CHOICES);

		int selectedMonth = view.monthChoice.getSelectedIndex();

		model.setDate(calendar.get(Calendar.DAY_OF_MONTH), selectedMonth,
				Integer.parseInt(view.yearChoice.getSelectedItem().toString()));

		reload();

		addListener();
		recompute();
	}

	/**
	 * 
	 */
	protected void reload() {
		if (model.reloadReminds()) {
			connectionSucces = true;
		} else {
			JOptionPane
					.showMessageDialog(
							view,
							"Es konnte keine Verbindung hergestellst werden.\nÜberprüfen sie ihre Internet verbindung und klicken Sie erneut auf \"Neu Laden\" ",
							"Keine Verbindung", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 
	 */
	protected void addListener() {

		view.setDaysListener(new DayListener());
		view.setMonthListener(new MonthListener());
		view.setYearListener(new YearListener());
		view.setReloadListener(new ReloadListener());

		reminderChoiceDialog.setListener(new OkRemindChoiceListener(),
				new NewRemindListener(), new EditRemindListener());
		remindDialog.setListener(new OkRemindListener(),
				new DeleteRemindListener(), new CancelRemindListener());
	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class MonthListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = view.monthChoice.getSelectedIndex();
			if (i >= 0) {
				model.setMonth(i);
				recompute();
			}
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class YearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = view.yearChoice.getSelectedIndex();
			if (i >= 0) {
				model.setYear(Integer.parseInt(view.yearChoice
						.getSelectedItem().toString()));
				recompute();
			}
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class ReloadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			reload();
			recompute();
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class DayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String num = e.getActionCommand();
			if (!num.equals("")) {

				model.setDay(Integer.parseInt(num));
				reminderChoiceDialog.setAttributes(model.getReminds());
				reminderChoiceDialog.setVisible(true);
			}
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class NewRemindListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			remindDialog.setMode(RemindDialog.NEW_MODE);
			remindDialog.setVisible(true);

		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class EditRemindListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			model.setCurrentReminderObject(reminderChoiceDialog.remindList
					.getSelectedValue());
			if (model.getCurrentReminderObject() != null) {
				remindDialog.setAttributes(model.getCurrentReminderObject()
						.getContent(), model.getCurrentReminderObject()
						.getUser(), model.getCurrentReminderObject()
						.getRepeat());
				remindDialog.setMode(RemindDialog.EDIT_MODE);
				remindDialog.setVisible(true);
			} else {
				JOptionPane
						.showMessageDialog(
								reminderChoiceDialog,
								"Keine Erinnerung gewählt!\nWählen Sie eine Erinnerung aus der Liste und klicken Sie erneut auf \"Editieren!\"",
								"Keine Auswahl!", JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class OkRemindListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (remindDialog.getMode()) {
			case RemindDialog.NEW_MODE:
				ReminderObject r = new ReminderObject(
						remindDialog.getAttributes()[0],
						remindDialog.getAttributes()[1], model.getDate(),
						Integer.parseInt(remindDialog.getAttributes()[2]));
				model.setCurrentReminderObject(r);

				if (model.sendNewRemind()) {
					model.addToCurrentReminds(r);
					reminderChoiceDialog.setAttributes(model.getReminds());
					remindDialog.dispose();
					remindDialog.clear();
				} else {
					JOptionPane
							.showMessageDialog(
									remindDialog,
									"Es konnte keine Verbindung hergestellst werden.\nÜberprüfen sie ihre Internet verbindung und klicken Sie erneut auf \"Ok\" ",
									"Keine Verbindung",
									JOptionPane.ERROR_MESSAGE);
				}
				break;

			case RemindDialog.EDIT_MODE:
				model.getCurrentReminderObject().setUser(
						remindDialog.getAttributes()[0]);
				model.getCurrentReminderObject().setContent(
						remindDialog.getAttributes()[1]);
				model.getCurrentReminderObject().setRepeat(
						Integer.parseInt(remindDialog.getAttributes()[2]));
				if (model.sendUpdatedRemind()) {
					remindDialog.dispose();
					remindDialog.clear();
				} else {
					JOptionPane
							.showMessageDialog(
									remindDialog,
									"Es konnte keine Verbindung hergestellst werden.\nÜberprüfen sie ihre Internet verbindung und klicken Sie erneut auf \"Ok\" ",
									"Keine Verbindung",
									JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class OkRemindChoiceListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			recompute();
			reminderChoiceDialog.dispose();
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class DeleteRemindListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (model.deleteRemind()) {
				model.deleteOfCurrentReminds(model.getCurrentReminderObject());
				reminderChoiceDialog.setAttributes(model.getReminds());
				remindDialog.dispose();
				remindDialog.clear();
			} else {
				JOptionPane
						.showMessageDialog(
								remindDialog,
								"Es konnte keine Verbindung hergestellst werden.\nÜberprüfen sie ihre Internet verbindung und klicken Sie erneut auf \"Löschen\" ",
								"Keine Verbindung", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	/**
	 * 
	 * @author Tony
	 * 
	 */
	class CancelRemindListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			remindDialog.dispose();
			remindDialog.clear();
		}

	}

	/**
	 * 
	 */
	protected void recompute() {

		int[] remindsCount = model.getRemindCount();

		calendar = new GregorianCalendar(model.getYear(), model.getMonth(),
				model.getDay());

		int leadGap = new GregorianCalendar(model.getYear(), model.getMonth(),
				1).get(Calendar.DAY_OF_WEEK) - 1;

		int daysInMonth = Model.DAYS_OF_MONTH[model.getMonth()];
		if (model.isLeap(calendar.get(Calendar.YEAR)) && model.getMonth() == 1)
			++daysInMonth;

		for (int i = 0; i < leadGap; i++) {
			view.getDayButtons()[0][i].setText("");
		}

		for (int i = 1; i <= daysInMonth; i++) {
			JButton b = view.getDayButtons()[(leadGap + i - 1) / 7][(leadGap
					+ i - 1) % 7];
			b.setText("<html><span text-align:\"center\"> Tag "
					+ Integer.toString(i)
					+ "<span/><br/><span text-align:\"center\">"
					+ (connectionSucces ? remindsCount[i - 1] : 0)
					+ " Einträge<span/><html/>");
			b.setActionCommand(Integer.toString(i));
		}

		for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
			view.getDayButtons()[(i) / 7][(i) % 7].setText("");
		}

		view.repaint();
	}

}

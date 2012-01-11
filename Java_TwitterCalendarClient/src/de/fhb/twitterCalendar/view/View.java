/**
 * 
 */
package de.fhb.twitterCalendar.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.fhb.twitterCalendar.model.Model;

/**
 * @author Tony
 * 
 */
public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JComboBox monthChoice;
	public JComboBox yearChoice;
	public JButton reloadButton;
	public JButton[][] dayButtons;

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public View(String title) throws HeadlessException {
		super(title);

		init();
	}

	/**
	 *
	 */
	protected void init() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200, 200, 400, 400);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JPanel choicePanel = new GradientJPanel();
		GridBagConstraints gbc_choicePanel = new GridBagConstraints();
		gbc_choicePanel.insets = new Insets(0, 0, 5, 0);
		gbc_choicePanel.fill = GridBagConstraints.BOTH;
		gbc_choicePanel.gridx = 0;
		gbc_choicePanel.gridy = 0;
		getContentPane().add(choicePanel, gbc_choicePanel);

		choicePanel.add(monthChoice = new JComboBox());
		for (int i = 0; i < Model.MONTH.length; i++)
			monthChoice.addItem(Model.MONTH[i]);

		choicePanel.add(yearChoice = new JComboBox());
		for (int i = Calendar.getInstance().get(Calendar.YEAR) - 2; i < Calendar
				.getInstance().get(Calendar.YEAR) + 5; i++)
			yearChoice.addItem(i);

		choicePanel.add(reloadButton = new JButton("Neu Laden"));

		JPanel daysPanel = new GradientJPanel();
		GridBagConstraints gbc_daysPanel = new GridBagConstraints();
		gbc_daysPanel.fill = GridBagConstraints.BOTH;
		gbc_daysPanel.gridx = 0;
		gbc_daysPanel.gridy = 1;
		getContentPane().add(daysPanel, gbc_daysPanel);

		daysPanel.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < Model.DAYS_OF_WEEK.length; i++) {
			JButton b = new JButton(Model.DAYS_OF_WEEK[i]);
			b.setEnabled(false);
			daysPanel.add(b);
		}

		dayButtons = new JButton[6][7];

		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 7; j++) {
				daysPanel.add(dayButtons[i][j] = new JButton(""));
				// dayButtons[i][j].addActionListener(dateSetter);
			}

		this.setVisible(true);
	}

	/**
	 * 
	 * @param ActionListener
	 *            a
	 */
	public void setDaysListener(ActionListener a) {
		for (JButton x[] : dayButtons) {
			for (JButton y : x) {
				y.addActionListener(a);
			}
		}
	}

	/**
	 * 
	 * @param a
	 */
	public void setMonthListener(ActionListener a) {

		monthChoice.addActionListener(a);

	}

	/**
	 * 
	 * @param a
	 */
	public void setYearListener(ActionListener a) {

		yearChoice.addActionListener(a);

	}

	/**
	 * 
	 * @param a
	 */
	public void setReloadListener(ActionListener a) {

		reloadButton.addActionListener(a);

	}

	/**
	 * 
	 * @return
	 */
	public JButton[][] getDayButtons() {
		return dayButtons;
	}

}

package de.fhb.twitterCalendar.view;

/**
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import de.fhb.twitterCalendar.server.valueObject.ReminderObject;

/**
 * @author Tony Hoffmann and Maciej Gorski
 * 
 */
public class RemindChoiceDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public JList<ReminderObject> remindList;
	protected JButton okButton;
	protected JButton newRemindButton;
	protected JButton editRemindButton;
	protected JPanel panel = new GradientJPanel();// (JPanel)View.buildBlackPanel();
	protected JLabel headline;

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public RemindChoiceDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		init();
	}

	/**
	 * 
	 */
	protected void init() {

		setBounds(100, 100, 450, 300);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 0, 40 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		{
			headline = new JLabel("Errinnerungsliste");
			headline.setFont(new Font("Times New Roman", Font.BOLD, 17));
			headline.setForeground(Color.LIGHT_GRAY);
			panel.add(headline);
		}
		{
			remindList = new JList<ReminderObject>();
			remindList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			GridBagConstraints gbc_remindList = new GridBagConstraints();
			gbc_remindList.gridwidth = 5;
			gbc_remindList.insets = new Insets(5, 5, 5, 5);
			gbc_remindList.fill = GridBagConstraints.BOTH;
			gbc_remindList.gridx = 0;
			gbc_remindList.gridy = 1;
			getContentPane().add(remindList, gbc_remindList);
		}
		{
			okButton = new JButton("OK");
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 1;
			gbc_okButton.gridy = 2;
			getContentPane().add(okButton, gbc_okButton);
		}
		{
			newRemindButton = new JButton("Neue Erinnerung");
			GridBagConstraints gbc_newRemindButton = new GridBagConstraints();
			gbc_newRemindButton.insets = new Insets(0, 0, 0, 5);
			gbc_newRemindButton.gridx = 2;
			gbc_newRemindButton.gridy = 2;
			getContentPane().add(newRemindButton, gbc_newRemindButton);
		}
		{
			editRemindButton = new JButton("Editieren");
			GridBagConstraints gbc_editRemindButton = new GridBagConstraints();
			gbc_editRemindButton.insets = new Insets(0, 0, 0, 5);
			gbc_editRemindButton.gridx = 3;
			gbc_editRemindButton.gridy = 2;
			getContentPane().add(editRemindButton, gbc_editRemindButton);
		}
	}

	/**
	 * 
	 * @param okListener
	 * @param newRemindListener
	 * @param editRemindListener
	 */
	public void setListener(ActionListener okListener,
			ActionListener newRemindListener, ActionListener editRemindListener) {
		okButton.addActionListener(okListener);
		newRemindButton.addActionListener(newRemindListener);
		editRemindButton.addActionListener(editRemindListener);
	}

	/**
	 * 
	 * @param listData
	 */
	public void setAttributes(Vector<ReminderObject> listData) {
		remindList.setListData(listData);
	}

}

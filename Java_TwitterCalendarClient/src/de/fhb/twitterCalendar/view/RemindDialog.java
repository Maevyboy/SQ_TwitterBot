/**
 * 
 */
package de.fhb.twitterCalendar.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Tony Hoffmann & Maciej Gorski
 * 
 */
public class RemindDialog extends JDialog {

	public static final int NEW_MODE = 0;
	public static final int EDIT_MODE = 1;

	protected static final long serialVersionUID = 1L;

	protected JLabel lblReciever = new JLabel("Empf\u00E4nger");
	public JTextField reciever;
	protected JPanel panel = new GradientJPanel();
	protected JLabel headline;
	public JLabel lblContent;
	public JTextArea content;
	protected JLabel lblRepeat;
	public JComboBox<String> repeat;
	protected JButton okButton;
	protected JButton deleteButton;

	protected int mode = NEW_MODE;
	protected JButton cancelButton;

	/**
	 * 
	 * @param frame
	 * @param name
	 * @param modal
	 */
	public RemindDialog(Dialog frame, String name, boolean modal) {
		super(frame, name, modal);

		init();

	}

	/**
	 * 
	 */
	protected void init() {

		setBounds(100, 100, 450, 300);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 0, 0, 0, 40 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 7;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);

		headline = new JLabel("Erinnerung Berabeiten");
		headline.setFont(new Font("Times New Roman", Font.BOLD, 17));
		headline.setForeground(Color.LIGHT_GRAY);
		panel.add(headline);

		GridBagConstraints gbc_lblReciever = new GridBagConstraints();
		gbc_lblReciever.insets = new Insets(5, 0, 0, 0);
		gbc_lblReciever.gridx = 1;
		gbc_lblReciever.gridy = 1;
		getContentPane().add(lblReciever, gbc_lblReciever);

		reciever = new JTextField();
		GridBagConstraints gbc_reciever = new GridBagConstraints();
		gbc_reciever.fill = GridBagConstraints.HORIZONTAL;
		gbc_reciever.gridwidth = 4;
		gbc_reciever.insets = new Insets(5, 5, 0, 0);
		gbc_reciever.gridx = 2;
		gbc_reciever.gridy = 1;
		getContentPane().add(reciever, gbc_reciever);
		reciever.setColumns(10);

		lblContent = new JLabel("Inhalt");
		GridBagConstraints gbc_lblContent = new GridBagConstraints();
		gbc_lblContent.insets = new Insets(5, 0, 0, 0);
		gbc_lblContent.gridx = 1;
		gbc_lblContent.gridy = 2;
		getContentPane().add(lblContent, gbc_lblContent);

		content = new JTextArea();
		GridBagConstraints gbc_content = new GridBagConstraints();
		gbc_content.gridwidth = 4;
		gbc_content.fill = GridBagConstraints.BOTH;
		gbc_content.insets = new Insets(5, 5, 0, 0);
		gbc_content.gridx = 2;
		gbc_content.gridy = 2;
		getContentPane().add(content, gbc_content);

		lblRepeat = new JLabel("Wiederholung");
		GridBagConstraints gbc_lblRepeat = new GridBagConstraints();
		gbc_lblRepeat.insets = new Insets(5, 0, 0, 0);
		gbc_lblRepeat.gridx = 1;
		gbc_lblRepeat.gridy = 3;
		getContentPane().add(lblRepeat, gbc_lblRepeat);

		repeat = new JComboBox<String>();
		GridBagConstraints gbc_repeat = new GridBagConstraints();
		gbc_repeat.fill = GridBagConstraints.HORIZONTAL;
		gbc_repeat.gridwidth = 4;
		gbc_repeat.insets = new Insets(5, 5, 0, 0);
		gbc_repeat.gridx = 2;
		gbc_repeat.gridy = 3;
		getContentPane().add(repeat, gbc_repeat);

		okButton = new JButton("OK");
		GridBagConstraints gbc_okButton = new GridBagConstraints();
		gbc_okButton.insets = new Insets(0, 5, 0, 5);
		gbc_okButton.gridx = 2;
		gbc_okButton.gridy = 4;
		getContentPane().add(okButton, gbc_okButton);

		deleteButton = new JButton("L\u00F6schen");
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.insets = new Insets(0, 0, 0, 5);
		gbc_deleteButton.gridx = 3;
		gbc_deleteButton.gridy = 4;
		getContentPane().add(deleteButton, gbc_deleteButton);

		cancelButton = new JButton("Abrechen");
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridx = 4;
		gbc_cancelButton.gridy = 4;
		getContentPane().add(cancelButton, gbc_cancelButton);

	}

	/**
	 * 
	 * @param okListener
	 * @param deleteListener
	 */
	public void setListener(ActionListener okListener,
			ActionListener deleteListener, ActionListener cancelListener) {
		okButton.addActionListener(okListener);
		deleteButton.addActionListener(deleteListener);
		cancelButton.addActionListener(cancelListener);
	}

	/**
	 * 
	 * @param items
	 */
	public void setRepeatItems(String[] items) {
		repeat.setModel(new DefaultComboBoxModel<String>(items));
	}

	/**
	 * 
	 * @param description
	 * @param reciever
	 * @param repeat
	 */
	public void setAttributes(String description, String reciever, int repeat) {
		content.setText(description);
		this.reciever.setText(reciever);
		System.out.println(repeat);
		if (repeat >= 0 && repeat < this.repeat.getItemCount()) {
			this.repeat.setSelectedIndex(repeat);
		}
	}

	/**
	 * 
	 * @return Returns an Array of Strings with length 3. <br/>
	 *         1. reciever, 2. content, 3. repeat
	 */
	public String[] getAttributes() {
		return new String[] { reciever.getText(), content.getText(),
				String.valueOf(repeat.getSelectedIndex()) };
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(int mode) {
		if (mode == NEW_MODE) {
			deleteButton.setEnabled(false);
		} else {
			deleteButton.setEnabled(true);
		}
		this.mode = mode;
	}

	public void clear() {
		setAttributes("", "", 0);
	}
}

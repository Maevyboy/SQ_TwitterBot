/**
 * 
 */
package de.fhb.twitterCalendar.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * @author Tony Hoffmann & Maciej Gorski
 * 
 */
public class GradientJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GradientJPanel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 */
	public GradientJPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isDoubleBuffered
	 */
	public GradientJPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public GradientJPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();

		Rectangle clip = g2.getClipBounds();
		g2.setPaint(new GradientPaint(0.0f, 0.0f, new Color(0x666f7f).darker(),
				0.0f, getHeight(), new Color(0x262d3d).darker()));

		g2.fillRect(clip.x, clip.y, clip.width, clip.height);

	}

}

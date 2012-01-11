/**
 * 
 */
package de.fhb.twitterCalendar.main;

import de.fhb.twitterCalendar.controller.Controller;
import de.fhb.twitterCalendar.model.Model;
import de.fhb.twitterCalendar.view.View;

/**
 * @author Tony Hoffmann and Maciej Gorski
 * 
 */
public class Main {

	static View view;
	static Model model;
	static Controller controller;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		view = new View("Twitter Kalender");
		model = new Model();

		controller = new Controller(model, view);
		controller.init();
	}

}

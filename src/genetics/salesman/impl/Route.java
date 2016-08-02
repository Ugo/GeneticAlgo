/*
* Tour.java
* Stores a candidate tour
*/

package genetics.salesman.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import genetics.intf.IIndividual;
import genetics.salesman.City;
import genetics.salesman.RouteManager;
import genetics.salesman.ui.DisplayComponent;

public class Route implements IIndividual {

	// Holds our tour of cities
	private ArrayList<City> tour = new ArrayList<City>();

	// Cache
	private double fitness = 0;
	private int distance = 0;

	private int SIZE_DISPLAY_BOARD = 200;

	// Constructs a blank route
	public Route() {
		for (int i = 0; i < RouteManager.numberOfCities(); i++) {
			tour.add(null);
		}
	}

	public Route(ArrayList<City> tour) {
		this.tour = tour;
	}

	// Creates a random individual
	@Override
	public void generateIndividual() {
		// Loop through all our destination cities and add them to our tour
		for (int cityIndex = 0; cityIndex < RouteManager.numberOfCities(); cityIndex++) {
			setCity(cityIndex, RouteManager.getCity(cityIndex));
		}
		// Randomly reorder the tour
		Collections.shuffle(tour);
	}

	// Gets a city from the tour
	public City getCity(int tourPosition) {
		return (City) tour.get(tourPosition);
	}

	// Sets a city in a certain position within a tour
	public void setCity(int tourPosition, City city) {
		tour.set(tourPosition, city);
		// If the tours been altered we need to reset the fitness and distance
		fitness = 0;
		distance = 0;
	}

	// Gets the tours fitness
	@Override
	public double getFitness() {
		if (fitness == 0) {
			fitness = 1 / (double) getDistance();
		}
		return fitness;
	}

	/**
	 * @return the total distance of the route
	 */
	public int getDistance() {
		if (distance == 0) {
			int routeDistance = 0;
			for (int cityIndex = 0; cityIndex < routeSize(); cityIndex++) {
				// Get city we're travelling from
				City fromCity = getCity(cityIndex);
				// City we're travelling to
				City destinationCity;
				// Check we're not on our tour's last city, if we are set our
				// tour's final destination city to our starting city
				if (cityIndex + 1 < routeSize()) {
					destinationCity = getCity(cityIndex + 1);
				} else {
					destinationCity = getCity(0);
				}
				// Get the distance between the two cities
				routeDistance += fromCity.distanceTo(destinationCity);
			}
			distance = routeDistance;
		}
		return distance;
	}

	/**
	 * @return the route size
	 */
	public int routeSize() {
		return tour.size();
	}

	/**
	 * Check if the route contains a city
	 * 
	 * @param city
	 *            city to check
	 * @return true if the route contains the city given in parameter
	 */
	public boolean containsCity(City city) {
		return tour.contains(city);
	}

	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < routeSize(); i++) {
			geneString += getCity(i) + "|";
		}
		return geneString;
	}

	/**
	 * By default, it doesn't display the grid.
	 * 
	 * @param title
	 *            title of the route
	 */
	public void displayRoute(String title) {
		displayRoute(title, false);
	}

	/**
	 * Method to display the route in a separate window
	 * 
	 * @param title
	 *            title of the route
	 * @param printGrid
	 *            boolean to use to display the grid or not
	 */
	public void displayRoute(String title, boolean printGrid) {
		JFrame frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final DisplayComponent comp = new DisplayComponent();
		comp.setBorder(BorderFactory.createCompoundBorder());
		comp.setPreferredSize(new Dimension(SIZE_DISPLAY_BOARD * 2 + 50, SIZE_DISPLAY_BOARD * 2 + 50));
		frame.getContentPane().add(comp, BorderLayout.CENTER);

		if (printGrid) {
			for (int iter = 0; iter <= SIZE_DISPLAY_BOARD; iter++) {
				if (iter % 10 == 0) {
					comp.addLine(iter * 2, 0, iter * 2, SIZE_DISPLAY_BOARD * 2);
					comp.addLine(0, iter * 2, SIZE_DISPLAY_BOARD * 2, iter * 2);
				}
			}
		}

		for (int cityIndex = 0; cityIndex < routeSize(); cityIndex++) {
			City fromCity = getCity(cityIndex);
			City destinationCity;
			// check if this is the last city or not, if yes, the final city is the starting city
			if (cityIndex + 1 < routeSize()) {
				destinationCity = getCity(cityIndex + 1);
			} else {
				destinationCity = getCity(0);
			}
			// draw line accordingly
			comp.addLine(fromCity.getX() * 2, fromCity.getY() * 2, destinationCity.getX() * 2,
					destinationCity.getY() * 2, Color.RED);
		}
		frame.pack();
		frame.setVisible(true);
	}
}
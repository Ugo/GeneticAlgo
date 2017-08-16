package com.genetics.salesman;

import com.genetics.salesman.ui.DisplayComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Itinerary implements Individual {

    private ArrayList<City> cities = new ArrayList<>();

    // Cache
    private double fitness = 0;
    private int distance = 0;

    private int SIZE_DISPLAY_BOARD = 200;

    Itinerary() {}
    Itinerary(ArrayList<City> cities) {this.cities = cities;}

    City getCity(int index) {
        return cities.get(index);
    }

    void setCity(int index, City city) {
        cities.set(index, city);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }

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
    int getDistance() {
        if (distance == 0) {
            int routeDistance = 0;
            for (int cityIndex = 0; cityIndex < getLength(); cityIndex++) {
                // Get city we're travelling from
                City fromCity = getCity(cityIndex);
                // City we're travelling to
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if (cityIndex + 1 < getLength()) {
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

    int getLength() {
        return cities.size();
    }

    boolean containsCity(City city) {
        return cities.contains(city);
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < getLength(); i++) {
            geneString += getCity(i) + "|";
        }
        return geneString;
    }

    /**
     * By default, it doesn't display the grid.
     *
     * @param title title of the route
     */
    void print(String title) {
        print(title, false);
    }

    /**
     * Method to display the route in a separate window
     *
     * @param title     title of the route
     * @param printGrid boolean to use to display the grid or not
     */
    void print(String title, boolean printGrid) {
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

        for (int cityIndex = 0; cityIndex < getLength(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity;
            // check if this is the last city or not, if yes, the final city is the starting city
            if (cityIndex + 1 < getLength()) {
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
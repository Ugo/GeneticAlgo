package com.genetics.salesman;

import com.genetics.salesman.ui.DisplayComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Itinerary implements Individual {

    private ArrayList<City> cities = new ArrayList<>();

    // Cache
    private int distance = 0;

    private static final int SIZE_DISPLAY_BOARD = 200;
    private final Random RANDOM = new Random();

    Itinerary(ArrayList<City> cities) {
        this.cities = cities;
    }

    private City getCity(int index) {
        return cities.get(index);
    }

    // smaller is better
    @Override
    public double getFitness() {
        if (distance != 0) {
            return distance;
        }

        for (int count = 0; count < getSize(); count++) {
            City departure = getCity(count);
            City arrival = getCity((count + 1) % getSize());
            distance += departure.distanceTo(arrival);
        }
        return distance;
    }

    Itinerary createOffSpringWith(Itinerary parent){
        ArrayList<City> ret = new ArrayList<>();

        // Get start and end sub tour positions for parent1's tour
        int start = RANDOM.nextInt(this.getSize());
        int end = RANDOM.nextInt(this.getSize() - start) + start;

        // add the sub itinerary from parent1
        ret.addAll(this.cities.subList(start, end));

        // add missing cities from parent2 in the same order
        for (int i = 0; i < parent.getSize(); i++) {

            if (!ret.contains(parent.getCity(i))) {
                // Loop to find a spare position in the child's tour
                ret.add(parent.getCity(i));
            }
        }

        return new Itinerary(ret);
    }

    void swapCities(int index1, int index2) {
        City city1 = getCity(index1);
        City city2 = getCity(index2);

        // Swap them around
        cities.set(index2, city1);
        cities.set(index1, city2);
        distance = 0;
    }

    int getSize() {
        return cities.size();
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < getSize(); i++) {
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

        for (int cityIndex = 0; cityIndex < getSize(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity;
            // check if this is the last city or not, if yes, the final city is the starting city
            if (cityIndex + 1 < getSize()) {
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
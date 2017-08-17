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
        StringBuilder geneString = new StringBuilder("|");
        for (int i = 0; i < getSize(); i++) {
            geneString.append(getCity(i)).append("|");
        }
        return geneString.toString();
    }

    // Method to display the route in a separate window
    void print(String title) {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final DisplayComponent comp = new DisplayComponent();
        comp.setBorder(BorderFactory.createCompoundBorder());
        comp.setPreferredSize(new Dimension(SIZE_DISPLAY_BOARD * 2 + 50, SIZE_DISPLAY_BOARD * 2 + 50));
        frame.getContentPane().add(comp, BorderLayout.CENTER);

        for (int cityIndex = 0; cityIndex < getSize(); cityIndex++) {
            City fromCity = getCity(cityIndex);
            City destinationCity = getCity((cityIndex + 1) % getSize());

            comp.addLine(fromCity.getX() * 2, fromCity.getY() * 2, destinationCity.getX() * 2,
                    destinationCity.getY() * 2, Color.RED);
        }

        frame.pack();
        frame.setVisible(true);
    }
}
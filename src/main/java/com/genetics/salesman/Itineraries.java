package com.genetics.salesman;

import java.util.ArrayList;
import java.util.List;

public class Itineraries implements Population<Itinerary> {

    private List<Itinerary> itineraries;

    Itineraries(int count) {
        itineraries = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            addIndividual(ItineraryFactory.createRandomItinerary());
        }
    }

    @Override
    public void addIndividual(Itinerary itinerary) {
        itineraries.add(itinerary);
    }

    @Override
    public Itinerary getIndividual(int index) {
        return itineraries.get(index);
    }

    @Override
    public Itinerary getFittestIndividual() {
        Itinerary fittest = itineraries.get(0);
        for (int iter = 1; iter < size(); iter++) {
            if (fittest.getFitness() <= getIndividual(iter).getFitness()) {
                fittest = getIndividual(iter);
            }
        }
        return fittest;
    }

    @Override
    public int size() {
        return itineraries.size();
    }
}
package com.genetics.salesman;

import java.util.ArrayList;
import java.util.Collections;

class Factory {

    private Factory() {
    }

    private static Itinerary createRandomItinerary() {
        ArrayList<City> temp = new ArrayList<>(RouteManager.getCities());
        Collections.shuffle(temp);
        return new Itinerary(temp);
    }

    static Itineraries createRandomItineraries(int size) {
        Itineraries itineraries = new Itineraries();
        for (int i = 0; i < size; i++) {
            itineraries.addIndividual(Factory.createRandomItinerary());
        }

        return itineraries;
    }
}

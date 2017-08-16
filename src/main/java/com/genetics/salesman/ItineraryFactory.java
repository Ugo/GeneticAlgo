package com.genetics.salesman;

import java.util.ArrayList;
import java.util.Collections;

public class ItineraryFactory {

    public static Itinerary createRandomItinerary() {
        ArrayList<City> temp = new ArrayList<>(RouteManager.getCities());
        Collections.shuffle(temp);
        return new Itinerary(temp);
    }
}

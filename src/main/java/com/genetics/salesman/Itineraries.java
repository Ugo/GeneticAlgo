package com.genetics.salesman;

import java.util.ArrayList;
import java.util.List;

public class Itineraries implements Population<Route> {

    private List<Route> routes;

    Itineraries(int populationSize) {
        routes = new ArrayList<>();
        for (int routeIter = 0; routeIter < populationSize; routeIter++) {
            Route newRoute = new Route();
            newRoute.generateIndividual();
            addIndividual(newRoute);
        }
    }

    @Override
    public void addIndividual(Route route) {
        routes.add(route);
    }

    @Override
    public Route getIndividual(int index) {
        return routes.get(index);
    }

    @Override
    public Route getFittestIndividual() {
        Route fittest = routes.get(0);
        for (int iter = 1; iter < populationSize(); iter++) {
            if (fittest.getFitness() <= getIndividual(iter).getFitness()) {
                fittest = getIndividual(iter);
            }
        }
        return fittest;
    }

    @Override
    public int populationSize() {
        return routes.size();
    }
}
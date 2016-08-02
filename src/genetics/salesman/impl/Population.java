/*
* Population.java
* Manages a population of candidate tours
*/

package genetics.salesman.impl;

import genetics.intf.IPopulation;

public class Population implements IPopulation<Route>{

    // Holds population of tours
    Route[] routes;

    // Construct a population
    public Population(int populationSize, boolean initialise) {
        routes = new Route[populationSize];
        // If we need to initialize a population of tours do so
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < populationSize(); i++) {
                Route newRoute = new Route();
                newRoute.generateIndividual();
                saveIndividual(i, newRoute);
            }
        }
    }
    
    // Saves a tour
    @Override
    public void saveIndividual(int index, Route tour) {
        routes[index] = tour;
    }
    
    // Gets a tour from population
    @Override
    public Route getIndividual(int index) {
        return routes[index];
    }

    // Gets the best tour in the population
    @Override
    public Route getFittestIndividual() {
        Route fittest = routes[0];
        // Loop through individuals to find the fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    // Gets population size
    public int populationSize() {
        return routes.length;
    }
}
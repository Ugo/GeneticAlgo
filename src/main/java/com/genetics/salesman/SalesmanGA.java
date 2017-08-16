package com.genetics.salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalesmanGA implements GeneticAlgorithm<Itinerary, Itineraries> {

    private static final double MUTATION_RATE = 0.02;
    private static final int TOURNAMENT_SIZE = 10;
    private static final boolean ELITISM = true;
    private final Random RANDOM = new Random();

    @Override
    public Itineraries evolve(Itineraries population) {
        List<Itinerary> newPopulation = new ArrayList<>();

        // Keep our best individual if ELITISM is enabled
        int elitismOffset = 0;
        if (ELITISM) {
            newPopulation.add(population.getFittestIndividual());
            elitismOffset = 1;
        }

        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < population.size(); i++) {
            // Select best parents in small subsets
            Itinerary parent1 = selectRandomIndividual(population);
            Itinerary parent2 = selectRandomIndividual(population);
            // Crossover parents
            Itinerary child = createOffspring(parent1, parent2);
            // Add child to new population
            newPopulation.add(child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < population.size(); i++) {
            mutate(newPopulation.get(i));
        }

        return new Itineraries(newPopulation);
    }

    @Override
    public Itinerary createOffspring(Itinerary parent1, Itinerary parent2) {

        ArrayList<City> cities = new ArrayList<>();

        // Get start and end sub tour positions for parent1's tour
        int startPos = RANDOM.nextInt(parent1.getLength());
        int endPos = RANDOM.nextInt(parent1.getLength() - startPos) + startPos;

        // Loop and add the sub tour from parent1 to our child
        cities.addAll(parent1.getCities().subList(startPos, endPos));

        // add missing cities from parent2 in the same order
        for (int i = 0; i < parent2.getLength(); i++) {
            // If child doesn't have the city add it
            if (!cities.contains(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                cities.add(parent2.getCity(i));
            }
        }

        return new Itinerary(cities);
    }

    // Mutate a tour using swap mutation
    @Override
    public void mutate(Itinerary itinerary) {
        for (int index1 = 0; index1 < itinerary.getLength(); index1++) {
            // Apply mutation rate
            if (Math.random() < MUTATION_RATE) {
                // Get a second random position in the tour
                int index2 = RANDOM.nextInt(itinerary.getLength());

                itinerary.swapCities(index1, index2);
            }
        }
    }

    // select the fittest individual from 10 randomly chosen individuals of the population
    @Override
    public Itinerary selectRandomIndividual(Itineraries pop) {

        Itineraries randomItineraries = new Itineraries();
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int iter = 0; iter < TOURNAMENT_SIZE; iter++) {
            int randomId = RANDOM.nextInt(pop.size());
            randomItineraries.addIndividual(pop.getIndividual(randomId));
        }

        return randomItineraries.getFittestIndividual();
    }
}
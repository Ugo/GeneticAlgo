package com.genetics.salesman;

public class SalesmanGA  implements GeneticAlgorithm<Itinerary, Itineraries> {

	private static final double MUTATION_RATE = 0.02;
	private static final int TOURNAMENT_SIZE = 10;
	private static final boolean ELITISM = true;

	@Override
	public Itineraries evolve(Itineraries population) {
		Itineraries newPopulation = new Itineraries(population.size());

		// Keep our best individual if ELITISM is enabled
		int elitismOffset = 0;
		if (ELITISM) {
			newPopulation.addIndividual(population.getFittestIndividual());
			elitismOffset = 1;
		}

		// Loop over the new population's size and create individuals from
		// Current population
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			// Select best parents in small subsets
			Itinerary parent1 = selectRandomIndividual(population);
			Itinerary parent2 = selectRandomIndividual(population);
			// Crossover parents
			Itinerary child = createOffspring(parent1, parent2);
			// Add child to new population
			newPopulation.addIndividual(child);
		}

		// Mutate the new population a bit to add some new genetic material
		for (int i = elitismOffset; i < newPopulation.size(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	@Override
	public Itinerary createOffspring(Itinerary parent1, Itinerary parent2) {

		Itinerary child = new Itinerary();

		// Get start and end sub tour positions for parent1's tour
		int startPos = (int) (Math.random() * parent1.getLength());
		int endPos = (int) (Math.random() * parent1.getLength());

		// Loop and add the sub tour from parent1 to our child
		for (int i = 0; i < child.getLength(); i++) {
			// If our start position is less than the end position
			if (startPos < endPos && i > startPos && i < endPos) {
				child.setCity(i, parent1.getCity(i));
			} // If our start position is larger
			else if (startPos > endPos) {
				if (!(i < startPos && i > endPos)) {
					child.setCity(i, parent1.getCity(i));
				}
			}
		}

		// Loop through parent2's city tour
		for (int i = 0; i < parent2.getLength(); i++) {
			// If child doesn't have the city add it
			if (!child.containsCity(parent2.getCity(i))) {
				// Loop to find a spare position in the child's tour
				for (int ii = 0; ii < child.getLength(); ii++) {
					// Spare position found, add city
					if (child.getCity(ii) == null) {
						child.setCity(ii, parent2.getCity(i));
						break;
					}
				}
			}
		}
		return child;
	}

	// Mutate a tour using swap mutation
	@Override
	public void mutate(Itinerary tour) {
		// Loop through tour cities
		for (int routeIter = 0; routeIter < tour.getLength(); routeIter++) {
			// Apply mutation rate
			if (Math.random() < MUTATION_RATE) {
				// Get a second random position in the tour
				int route2 = (int) (tour.getLength() * Math.random());

				// Get the cities at target position in tour
				City city1 = tour.getCity(routeIter);
				City city2 = tour.getCity(route2);

				// Swap them around
				tour.setCity(route2, city1);
				tour.setCity(routeIter, city2);
			}
		}
	}

	// Selects candidate tour for createOffspring
	@Override
	public Itinerary selectRandomIndividual(Itineraries pop) {
		// Create a tournament population
		Itineraries tournament = new Itineraries(TOURNAMENT_SIZE);
		// For each place in the tournament get a random candidate tour and
		// add it
		for (int iter = 0; iter < TOURNAMENT_SIZE; iter++) {
			int randomId = (int) (Math.random() * pop.size());
			tournament.addIndividual(pop.getIndividual(randomId));
		}

		return tournament.getFittestIndividual();
	}
}
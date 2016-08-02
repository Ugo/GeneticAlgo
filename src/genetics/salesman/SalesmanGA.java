/*
* GA.java
* Manages algorithms for evolving population
*/

package genetics.salesman;

import genetics.intf.IGeneticAlgorithm;
import genetics.intf.IPopulation;
import genetics.salesman.impl.Population;
import genetics.salesman.impl.Route;

public class SalesmanGA  implements IGeneticAlgorithm<Route> { 

	/* GA parameters */
	private static final double mutationRate = 0.02; 
	private static final int tournamentSize = 10; 
	private static final boolean elitism = true;

	// Evolves a population over one generation
	@Override
	public IPopulation<Route> evolvePopulation(IPopulation<Route> pop) {
		Population newPopulation = new Population(pop.populationSize(), false);

		// Keep our best individual if elitism is enabled
		int elitismOffset = 0;
		if (elitism) {
			newPopulation.saveIndividual(0, pop.getFittestIndividual());
			elitismOffset = 1;
		}

		// Loop over the new population's size and create individuals from
		// Current population
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			// Select best parents in small subsets
			Route parent1 = selectIndividual(pop);
			Route parent2 = selectIndividual(pop);
			// Crossover parents
			Route child = crossover(parent1, parent2);
			// Add child to new population
			newPopulation.saveIndividual(i, child);
		}

		// Mutate the new population a bit to add some new genetic material
		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	// Applies crossover to a set of parents and creates offspring
	@Override
	public Route crossover(Route parent1, Route parent2) {
		// Create new child tour
		Route child = new Route();

		// Get start and end sub tour positions for parent1's tour
		int startPos = (int) (Math.random() * parent1.routeSize());
		int endPos = (int) (Math.random() * parent1.routeSize());

		// Loop and add the sub tour from parent1 to our child
		for (int i = 0; i < child.routeSize(); i++) {
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
		for (int i = 0; i < parent2.routeSize(); i++) {
			// If child doesn't have the city add it
			if (!child.containsCity(parent2.getCity(i))) {
				// Loop to find a spare position in the child's tour
				for (int ii = 0; ii < child.routeSize(); ii++) {
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
	public void mutate(Route tour) {
		// Loop through tour cities
		for (int routeIter = 0; routeIter < tour.routeSize(); routeIter++) {
			// Apply mutation rate
			if (Math.random() < mutationRate) {
				// Get a second random position in the tour
				int route2 = (int) (tour.routeSize() * Math.random());

				// Get the cities at target position in tour
				City city1 = tour.getCity(routeIter);
				City city2 = tour.getCity(route2);

				// Swap them around
				tour.setCity(route2, city1);
				tour.setCity(routeIter, city2);
			}
		}
	}

	// Selects candidate tour for crossover
	@Override
	public Route selectIndividual(IPopulation<Route> pop) {
		// Create a tournament population
		Population tournament = new Population(tournamentSize, false);
		// For each place in the tournament get a random candidate tour and
		// add it
		for (int iter = 0; iter < tournamentSize; iter++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.saveIndividual(iter, pop.getIndividual(randomId));
		}
		// Get the fittest route
		Route fittest = tournament.getFittestIndividual();
		return fittest;
	}

	
}
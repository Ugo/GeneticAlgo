package com.genetics.salesman;


public interface GeneticAlgorithm<R extends Individual, T extends Population<R>> {

	/**
	 * Make the population evolve one generation.
	 */
	T evolve(T population);

	/**
	 * Create an offspring from two parents having characteristics of both
	 * parents.
	 */
	R createOffspring(R parent1, R parent2);

	/**
	 * Performs a mutation on an individual to modify it.
	 */
	void mutate(R individual);

	/**
	 * Select one individual in the population.
	 */
	R selectRandomIndividual(T population);

}

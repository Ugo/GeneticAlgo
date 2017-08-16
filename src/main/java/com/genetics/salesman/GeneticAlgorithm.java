package com.genetics.salesman;


public interface GeneticAlgorithm<T extends Individual> {

	/**
	 * Make the population evolve one generation.
	 */
	Population<T> evolve(Population<T> pop);

	/**
	 * Create an offspring from two parents having characteristics of both
	 * parents.
	 */
	T createOffspring(T parent1, T parent2);

	/**
	 * Performs a mutation on an individual to modify it.
	 */
	void mutate(T individual);

	/**
	 * Select one individual in the population.
	 */
	T selectIndividual(Population<T> population);

}

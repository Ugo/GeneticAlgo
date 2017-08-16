package com.genetics.salesman;


public interface GeneticAlgorithm<R extends Individual, T extends Population<R>> {

	// Make the population evolve one generation.
	T evolve(T population);

	R createOffspring(R parent1, R parent2);

	void mutate(R individual);

	R selectGoodRandomIndividual(T population);

}

package com.genetics.salesman;

public interface Population<T extends Individual> {

	void addIndividual(T individual);

	T getIndividual(int index);

	T getFittestIndividual();
	
	int size();
}

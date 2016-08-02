package genetics.intf;

public interface IGeneticAlgorithm<T extends IIndividual> {

	/**
	 * Make the population evolve one generation
	 * 
	 * @param pop
	 *            the population to evolve
	 * @return the next generation of the population
	 */
	public IPopulation<T> evolvePopulation(IPopulation<T> pop);

	/**
	 * Create an offspring from two parents having characteristics of both
	 * parents
	 * 
	 * @param parent1
	 *            first parent
	 * @param parent2
	 *            second parent
	 * @return an offspring created from the two parents
	 */
	public T crossover(T parent1, T parent2);

	/**
	 * Performs a mutation on an individual to modify it.
	 * 
	 * @param individual
	 *            the individual on which the mutation is performed.
	 */
	public void mutate(T individual);

	/**
	 * Select one individual in the population.
	 * 
	 * @param population
	 *            population from which to choose an individual
	 * @return an individual
	 */
	public T selectIndividual(IPopulation<T> population);

}

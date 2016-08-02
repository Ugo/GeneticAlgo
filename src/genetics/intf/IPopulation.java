package genetics.intf;

public interface IPopulation<T extends IIndividual> {

	public void saveIndividual(int index, T individual);

	public T getIndividual(int index);

	public T getFittestIndividual();
	
	public int populationSize();
}

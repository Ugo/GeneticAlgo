package com.genetics.salesman;

public class TestPopulation {

    private static int POPULATION_SIZE = 5000;
    private static int NUMBER_GENERATION = 500;

    public static void main(String[] args) {

        // Create and add our cities
        TestRandom();
        // TestSquare();

        // Initialize population
        Itineraries pop = Factory.createRandomItineraries(POPULATION_SIZE);

        System.out.println("Initial distance: " + pop.getFittestIndividual().getDistance());
        long startTime = System.currentTimeMillis();
        pop.getFittestIndividual().print("Initial route");

        // Evolve population for all generations
        SalesmanGA ga = new SalesmanGA();
        pop = ga.evolve(pop);
        for (int i = 0; i < NUMBER_GENERATION; i++) {
            pop = ga.evolve(pop);
            System.out.println("shortest distance for generation " + i + ": " + pop.getFittestIndividual().getDistance());
        }

        // Print final results
        System.out.println("Final distance: " + pop.getFittestIndividual().getDistance());
        pop.getFittestIndividual().print("Solution route");
        long endTime = System.currentTimeMillis();
        System.out.println("Best route found after " + NUMBER_GENERATION + " generations on " + POPULATION_SIZE + " individuals in "
                + (endTime - startTime) + "ms");
    }

    public static void TestRandom() {
        POPULATION_SIZE = 2000;
        NUMBER_GENERATION = 200;

        // Create and add our cities
        RouteManager.addCity(new City(60, 200));
        RouteManager.addCity(new City(180, 200));
        RouteManager.addCity(new City(80, 180));
        RouteManager.addCity(new City(140, 180));
        RouteManager.addCity(new City(20, 160));
        RouteManager.addCity(new City(100, 160));
        RouteManager.addCity(new City(200, 160));
        RouteManager.addCity(new City(140, 140));
        RouteManager.addCity(new City(40, 120));
        RouteManager.addCity(new City(100, 120));
        RouteManager.addCity(new City(180, 100));
        RouteManager.addCity(new City(60, 80));
        RouteManager.addCity(new City(120, 80));
        RouteManager.addCity(new City(180, 60));
        RouteManager.addCity(new City(20, 40));
        RouteManager.addCity(new City(100, 40));
        RouteManager.addCity(new City(200, 40));
        RouteManager.addCity(new City(20, 20));
        RouteManager.addCity(new City(60, 20));
        RouteManager.addCity(new City(160, 20));
    }

    public static void TestSquare() {
        POPULATION_SIZE = 5000;
        NUMBER_GENERATION = 500;

        RouteManager.addCity(new City(100, 20));
        RouteManager.addCity(new City(100, 200));
        RouteManager.addCity(new City(110, 20));
        RouteManager.addCity(new City(110, 200));
        RouteManager.addCity(new City(120, 20));
        RouteManager.addCity(new City(120, 200));
        RouteManager.addCity(new City(130, 20));
        RouteManager.addCity(new City(130, 200));
        RouteManager.addCity(new City(140, 20));
        RouteManager.addCity(new City(140, 200));
        RouteManager.addCity(new City(150, 20));
        RouteManager.addCity(new City(150, 200));
        RouteManager.addCity(new City(160, 20));
        RouteManager.addCity(new City(160, 200));
        RouteManager.addCity(new City(170, 20));
        RouteManager.addCity(new City(170, 200));
        RouteManager.addCity(new City(180, 20));
        RouteManager.addCity(new City(180, 200));
        RouteManager.addCity(new City(190, 20));
        RouteManager.addCity(new City(190, 200));
        RouteManager.addCity(new City(200, 100));
        RouteManager.addCity(new City(200, 110));
        RouteManager.addCity(new City(200, 120));
        RouteManager.addCity(new City(200, 130));
        RouteManager.addCity(new City(200, 140));
        RouteManager.addCity(new City(200, 150));
        RouteManager.addCity(new City(200, 160));
        RouteManager.addCity(new City(200, 170));
        RouteManager.addCity(new City(200, 180));
        RouteManager.addCity(new City(200, 190));
        RouteManager.addCity(new City(200, 20));
        RouteManager.addCity(new City(200, 30));
        RouteManager.addCity(new City(200, 40));
        RouteManager.addCity(new City(200, 50));
        RouteManager.addCity(new City(200, 60));
        RouteManager.addCity(new City(200, 70));
        RouteManager.addCity(new City(200, 80));
        RouteManager.addCity(new City(200, 90));
        RouteManager.addCity(new City(20, 100));
        RouteManager.addCity(new City(20, 110));
        RouteManager.addCity(new City(20, 120));
        RouteManager.addCity(new City(20, 130));
        RouteManager.addCity(new City(20, 140));
        RouteManager.addCity(new City(20, 150));
        RouteManager.addCity(new City(20, 160));
        RouteManager.addCity(new City(20, 170));
        RouteManager.addCity(new City(20, 180));
        RouteManager.addCity(new City(20, 190));
        RouteManager.addCity(new City(20, 20));
        RouteManager.addCity(new City(20, 200));
        RouteManager.addCity(new City(20, 30));
        RouteManager.addCity(new City(20, 40));
        RouteManager.addCity(new City(20, 50));
        RouteManager.addCity(new City(20, 60));
        RouteManager.addCity(new City(20, 70));
        RouteManager.addCity(new City(20, 80));
        RouteManager.addCity(new City(20, 90));
        RouteManager.addCity(new City(30, 20));
        RouteManager.addCity(new City(30, 200));
        RouteManager.addCity(new City(40, 20));
        RouteManager.addCity(new City(40, 200));
        RouteManager.addCity(new City(50, 20));
        RouteManager.addCity(new City(50, 200));
        RouteManager.addCity(new City(60, 20));
        RouteManager.addCity(new City(60, 200));
        RouteManager.addCity(new City(70, 20));
        RouteManager.addCity(new City(70, 200));
        RouteManager.addCity(new City(80, 20));
        RouteManager.addCity(new City(80, 200));
        RouteManager.addCity(new City(90, 20));
        RouteManager.addCity(new City(90, 200));
        RouteManager.addCity(new City(200, 200));
    }
}
package com.genetics.salesman;

import java.util.ArrayList;
import java.util.List;

public class Itineraries implements Population<Itinerary> {

    private List<Itinerary> list;

    Itineraries(){
        this.list = new ArrayList<>();
    }

    Itineraries(List<Itinerary> itineraries){
        this.list = new ArrayList<>(itineraries);
    }

    @Override
    public void addIndividual(Itinerary itinerary) {
        list.add(itinerary);
    }

    @Override
    public Itinerary getIndividual(int index) {
        return list.get(index);
    }

    @Override
    public Itinerary getFittestIndividual() {

        Itinerary fittest = getIndividual(0);
        for (int iter = 1; iter < size(); iter++) {
            if (fittest.getFitness() > getIndividual(iter).getFitness()) {
                fittest = getIndividual(iter);
            }
        }
        return fittest;
    }

    @Override
    public int size() {
        return list.size();
    }
}
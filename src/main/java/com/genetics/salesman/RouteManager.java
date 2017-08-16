package com.genetics.salesman;

import java.util.ArrayList;

class RouteManager {

    private static ArrayList<City> cities = new ArrayList<>();

    private RouteManager() {}

    static void addCity(City city) {
        cities.add(city);
    }

    static ArrayList<City> getCities() {
        return cities;
    }

    static City getCity(int index){
        return cities.get(index);
    }
    static int numberOfCities(){
        return cities.size();
    }
}
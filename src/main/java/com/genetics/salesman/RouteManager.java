package com.genetics.salesman;

import java.util.ArrayList;

class RouteManager {

    private static ArrayList<City> cities = new ArrayList<City>();

    static void addCity(City city) {
        cities.add(city);
    }
    
    static City getCity(int index){
        return (City)cities.get(index);
    }
    
    static int numberOfCities(){
        return cities.size();
    }
}
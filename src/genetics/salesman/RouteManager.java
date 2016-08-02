/*
* TourManager.java
* Holds the cities of a tour
*/

package genetics.salesman;

import java.util.ArrayList;

public class RouteManager {

    // Holds our cities
    private static ArrayList<City> cities = new ArrayList<City>();

    // Adds a destination city
    public static void addCity(City city) {
        cities.add(city);
    }
    
    // Get a city
    public static City getCity(int index){
        return (City)cities.get(index);
    }
    
    // Get the number of destination cities
    public static int numberOfCities(){
        return cities.size();
    }
}
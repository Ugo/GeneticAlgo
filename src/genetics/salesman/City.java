/*
* City.java
* Models a city
*/

package genetics.salesman;
/**
 * Basic class to represent a City
 */
public class City {
    private int x;
    private int y;
    
    // Constructs a randomly placed city
    public City(){
        this((int)(Math.random()*200), (int)(Math.random()*200));
    }
    
    // Constructs a city at chosen x, y location
    public City(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // Gets city's x coordinate
    public int getX(){
        return this.x;
    }
    
    // Gets city's y coordinate
    public int getY(){
        return this.y;
    }
    
    // Gets the distance to given city
    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());
        return  Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
    }
    
    @Override
    public String toString(){
        return getX()+", "+getY();
    }
}
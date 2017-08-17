package com.genetics.salesman;

public class City {
    private int x;
    private int y;

    City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return this.x;
    }

    int getY() {
        return this.y;
    }

    double distanceTo(City city) {
        double xDistance = Math.abs(getX() - city.getX());
        double yDistance = Math.abs(getY() - city.getY());
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }
}
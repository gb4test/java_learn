package ru.stqa.pft.sandbox;

public class Coord {
    public double x;
    public double y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Coord other) {
        return Math.sqrt((Math.pow((other.x - this.x), 2)) +
                (Math.pow((other.y - this.y), 2)));
    }
}
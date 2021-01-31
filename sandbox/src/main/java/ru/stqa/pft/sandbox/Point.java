package ru.stqa.pft.sandbox;

public class Point {

    public static void main(String[] args) {

        Coord p1 = new Coord(1, 3);
        Coord p2 = new Coord(5, 1);

        System.out.println("Расстояние между двумя точками на плоскости = " +
                p1.distance(p2));
    }

}

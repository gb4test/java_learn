package ru.stqa.pft.sandbox.ex2;

public class Point {

    public static void main(String[] args) {

        Coordinates f = new Coordinates(1,3,5,1);

        System.out.println("Расстояние между двумя точками на плоскости = " +
                f.distance());
    }
}

package ru.stqa.pft.sandbox;

public class Point {

    public static void main(String[] args) {

        Point p1 = new Point(1, 3);
        Point p2 = new Point(5, 1);

        System.out.println("distance between two points = " +
                p1.distance(p2  ));
    }

        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distance(Point other) {
            return Math.sqrt((Math.pow((other.x - this.x), 2)) +
                    (Math.pow((other.y - this.y), 2)));
        }

}

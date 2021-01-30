package ru.stqa.pft.sandbox.ex2;

public class Coordinates {
        public double a;
        public double b;
        public double c;
        public double d;

        public Coordinates(double x1, double y1, double x2, double y2) {
            this.a = x1;
            this.b = y1;
            this.c = x2;
            this.d = y2;
        }

    public double distance() {
        return Math.sqrt((Math.pow((this.c - this.a), 2)) + (Math.pow((this.d - this.b), 2)));
    }
}

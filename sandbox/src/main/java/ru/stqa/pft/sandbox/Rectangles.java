package ru.stqa.pft.sandbox;

public class Rectangles {
  public double a;
  public double b;

  public Rectangles(double a, double b) {
    this.a = a;
    this.b = b;
  }

  public double area() {
    return this.a * this.b;
  }
}
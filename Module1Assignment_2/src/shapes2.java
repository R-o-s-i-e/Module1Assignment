//package myjavashapes;

import java.lang.Math;

abstract class TwoDShape {

    protected double width;
    protected double height;

    public TwoDShape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public TwoDShape() {
        // 0 means uninitialized
        this.width = 0;
        this.height = 0;
    }

    public abstract double getArea();

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void printInfo() {
        System.out.printf("Shape: width = %,.3f, height = %,.3f, area = %,.3f\n", width, height, getArea());
    }
}

class Triangle extends TwoDShape {

    private double side1, side2, side3;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getArea() {
        if (width != 0 & height != 0)
            return width * height / 2;
        else if (side1 != 0 & side2 != 0 & side3 != 0) {
            double s;
            s = (side1 + side2 + side3) / 2;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }
        else
            return -1; // Something wrong
    }

    public double heronsHeight() {
        if (side2 != 0)
            return 2 * getArea() / side2;
        else
            return height; // Constructed using width and height instead
    }

    public void printInfo() {
        if (width != 0 & height != 0)
            System.out.printf("Triangle: width = %,.3f, height = %,.3f, area = %,.3f\n", width, height, getArea());
        else if (side1 != 0 & side2 != 0 & side3 != 0)
            System.out.printf("Triangle: side1 = %,.3f, side2 = %,.3f, side3 = %,.3f, area = %,.3f, heronsHeight = %,.3f\n", side1, side2, side3, getArea(), heronsHeight());
    }
}

class Circle extends TwoDShape {

    public final double PI = 3.1415926;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return PI * radius * radius;
    }

    public void printInfo() {
        System.out.printf("Circle: radius = %,.3f, area = %,.3f\n", radius, getArea());
    }
}


public class shapes2 {
    public static void main(String[] args) {

        Triangle triangle1 = new Triangle(3, 4, 5);
        triangle1.printInfo();

        Triangle triangle2 = new Triangle(3, 4);
        triangle2.printInfo();

        Circle circle = new Circle(5);
        circle.printInfo();
    }
}
//package myjavashapes;

import java.lang.Math;

class TwoDShape {

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

    public double getArea() {
        return width * height;
    }
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

public class shapes1 {
    public static void main(String[] args) {
        TwoDShape shape1 = new TwoDShape();
        shape1.printInfo();
        shape1.setWidth(2);
        shape1.setHeight(5);
        shape1.printInfo();

        TwoDShape shape2 = new TwoDShape(2, 3);
        shape2.printInfo();

        Triangle triangle1 = new Triangle(3, 4, 5);
        triangle1.printInfo();

        Triangle triangle2 = new Triangle(3, 4);
        triangle2.printInfo();
    }
}
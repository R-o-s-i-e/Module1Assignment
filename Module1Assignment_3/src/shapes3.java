//package myjavashapes;

import java.lang.Math;

interface Rotate {
    public void rotate90();
    public void rotate180();
    public void rotate(double debgree);
}

abstract class TwoDShape
{ protected double width;
    protected double height;

    private double angle;

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

class Triangle extends TwoDShape implements Rotate {

    private double side1, side2, side3;
    private double angle;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;

        this.angle = Math.random() * 360;

        if (width == 0 ||
                height == 0 ) {
            System.out.printf("Invalid parameters: %,.3f, %,.3f... width and height shall not be 0(s)\n", width, height);
        }

    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        if (side1 + side2 < side3 ||
                side2 + side3 < side1 ||
                side1 + side3 < side2) {
            System.out.printf("Invalid parameters %,.3f, %,.3f, %,.3f... the sum of 2 sides shall be bigger than the other side\n", side1, side2, side3);
        }

        this.angle = Math.random() * 360;
    }

    public double getArea() {

        if (width != 0 & height != 0)
            return width * height / 2;
        else if (side1 != 0 & side2 != 0 & side3 != 0) {

            // invalid parameter shall return -1
            if (side1 + side2 < side3 ||
                    side2 + side3 < side1 ||
                    side1 + side3 < side2) {
                return -1;
            }

            double s;

            s = (side1 + side2 + side3) / 2;
            return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
        }
        else
            return -1;
    }

    public double heronsHeight() {
        if (side2 != 0)
            return 2 * getArea() / side2;   // herons' height
        else
            return height; // Constructed using width and height instead, not really herons height in this case.
    }

    // All rotations are anti-clockwise
    public void rotate90() {
        System.out.printf("Rotating 90 degrees from %,.3f...\n", angle);
        angle += 90;
        angle %= 360;   // Wrap around at 360
    }

    public void rotate180() {
        System.out.printf("Rotating 180 degrees from %,.3f...\n", angle);
        angle += 180;
        angle %= 360;   // Wrap around at 360
    }

    public void rotate(double degree) {
        System.out.printf("Rotating %,.3f degrees from %,.3f...\n", degree, angle);
        angle += degree;
        angle %= 360;   // Wrap around at 360
    }

    public void printInfo() {
        double area = getArea();

        // nothing to print if invalid
        if (area == -1) return;

        if (width != 0 & height != 0)
            System.out.printf("Triangle: width = %,.3f, height = %,.3f, area = %,.3f, angle = %,.3f\n", width, height, area, angle);
        else if (side1 != 0 & side2 != 0 & side3 != 0)
            System.out.printf("Triangle: side1 = %,.3f, side2 = %,.3f, side3 = %,.3f, area = %,.3f, heronsHeight = %,.3f, angle = %,.3f\n", side1, side2, side3, area, heronsHeight(), angle);
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


public class shapes3 {
    public static void main(String[] args) {

        System.out.printf("\nTest case 1: area and herons height of 3 sides constructor, with random angle\n");
        Triangle triangle1 = new Triangle(3, 4, 5);
        triangle1.printInfo();

        System.out.printf("\nTest case 2: rotation with width and height constructor, with random angle\n");
        Triangle triangle2 = new Triangle(3, 4);
        triangle2.printInfo();
        triangle2.rotate(90);
        triangle2.printInfo();
        triangle2.rotate(180);
        triangle2.printInfo();
        triangle2.rotate(180);
        triangle2.printInfo();
        triangle2.rotate(50);
        triangle2.printInfo();

        System.out.printf("\nTest case 3: invalid length of sides\n");
        Triangle triangle3 = new Triangle(1, 2, 5);
        triangle3.printInfo();

        System.out.printf("\nTest case 4: invalid width or height\n");
        Triangle triangle4 = new Triangle(0, 2);
        triangle4.printInfo();
        Triangle triangle5 = new Triangle(3, 0);
        triangle5.printInfo();
        Triangle triangle6 = new Triangle(0, 0);
        triangle6.printInfo();

        System.out.printf("\nTest case 5: circle");
        Circle circle = new Circle(5);
        circle.printInfo();
    }
}
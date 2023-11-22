package myjavashapes.shapes5;

import java.lang.Math;

enum Color {RED, GREEN, BLUE, NONE};

interface Rotate {
    public void rotate90();
    public void rotate180();
    public void rotate(double debgree);
}

abstract class TwoDShape {

    protected double width;
    protected double height;

    protected Color color;

    // Declaring ANSI_RESET so that we can change the colors
    public static final String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[34m", "\u001B[0m"};

    public TwoDShape(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public TwoDShape() {
        // 0 means uninitialized
        this.width = 0;
        this.height = 0;
        this.color = Color.NONE;
    }

    public abstract double getArea();

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // Print with the object of the object
    public void printInfo() {
        System.out.printf("%sShape: width = %,.3f, height = %,.3f, area = %,.3f%s\n", colors[color.ordinal()], width, height, getArea(), colors[Color.NONE.ordinal()]);
    }
}

class Triangle extends TwoDShape implements Rotate {

    private double side1, side2, side3;
    private double angle;

    public Triangle(double width, double height, Color color) {
        this.width = width;
        this.height = height;

        this.angle = Math.random() * 360;
        this.color = color;

        if (width == 0 ||
                height == 0 ) {
            System.out.printf("Invalid parameters: %,.3f, %,.3f... width and height shall not be 0(s)\n", width, height);
        }

    }

    public Triangle(double side1, double side2, double side3, Color color) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.angle = Math.random() * 360;
        this.color = color;

        if (side1 + side2 < side3 ||
                side2 + side3 < side1 ||
                side1 + side3 < side2) {
            System.out.printf("Invalid parameters %,.3f, %,.3f, %,.3f... the sum of 2 sides shall be bigger than the other side\n", side1, side2, side3);
        }
    }

    public double getArea() {

        if (width != 0 & height != 0)                     // Valid construction with width and height
            return width * height / 2;
        else if (side1 != 0 & side2 != 0 & side3 != 0) {  // Valid construction with 3 sides

            // Invalid parameter shall return -1
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
            return -1;  // Invalid construction with width or height
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

        // Nothing to print if invalid area, constructor shall have printed out the error message.
        if (area == -1) return;

        // Print with the object color
        if (width != 0 & height != 0)
            System.out.printf("%sTriangle: width = %,.3f, height = %,.3f, area = %,.3f, angle = %,.3f%s\n", colors[color.ordinal()], width, height, area, angle, colors[Color.NONE.ordinal()]);
        else if (side1 != 0 & side2 != 0 & side3 != 0)
            System.out.printf("%sTriangle: side1 = %,.3f, side2 = %,.3f, side3 = %,.3f, area = %,.3f, heronsHeight = %,.3f, angle = %,.3f%s\n", colors[color.ordinal()], side1, side2, side3, area, heronsHeight(), angle, colors[Color.NONE.ordinal()]);
    }
}

class Circle extends TwoDShape {

    public final double PI = 3.1415926;
    private double radius;

    public Circle(double radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public double getArea() {
        return PI * radius * radius;
    }

    // Print with the object color
    public void printInfo() {
        System.out.printf("%sCircle: radius = %,.3f, area = %,.3f%s\n", colors[color.ordinal()], radius, getArea(), colors[Color.NONE.ordinal()]);
    }
}


public class shapes5 {
    public static void main(String[] args) {

        System.out.printf("\nTest case 1: area and herons height of 3 sides constructor, with random angle and red color\n");
        Triangle triangle1 = new Triangle(3, 4, 5, Color.RED);
        triangle1.printInfo();

        System.out.printf("\nTest case 2: rotation with width and height constructor, with random angle and green color\n");
        Triangle triangle2 = new Triangle(3, 4, Color.GREEN);
        triangle2.printInfo();
        triangle2.rotate(90);
        triangle2.printInfo();
        triangle2.rotate(180);
        triangle2.printInfo();
        triangle2.rotate(180);
        triangle2.printInfo();
        triangle2.rotate(50);
        triangle2.printInfo();

        System.out.printf("\nTest case 3: invalid length of sides with no color\n");
        Triangle triangle3 = new Triangle(1, 2, 5, Color.BLUE);
        triangle3.printInfo();

        System.out.printf("\nTest case 4: invalid width or height with no color\n");
        Triangle triangle4 = new Triangle(0, 2, Color.NONE);
        triangle4.printInfo();
        Triangle triangle5 = new Triangle(3, 0, Color.NONE);
        triangle5.printInfo();
        Triangle triangle6 = new Triangle(0, 0, Color.NONE);
        triangle6.printInfo();

        System.out.printf("\nTest case 5: circle with no color");
        Circle circle = new Circle(5, Color.NONE);
        circle.printInfo();
    }
}
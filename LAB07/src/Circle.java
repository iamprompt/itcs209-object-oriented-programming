/*  The structure of Triangle class is similar to Rectangle */
public class Circle extends Shape {
    // Private member variables
    //add your code here
    private double radius;

    // Constructors
    public Circle() {
        //add your code here

    }

    public Circle(String color, double radius) {
        //add your code here
        this.radius = radius;
        super.setColor(color);
    }

    @Override
    public String toString() {
        //add your code here
        return "Circle[radius=" + this.radius + ", Shape[color=" + super.getColor() + "]]";
    }

    // Override the inherited getArea() to provide the proper implementation
    @Override
    public double getArea() {
        //add your code here
        return Math.PI * Math.pow(this.radius, 2);
    }

    public double getArea(double radius) {
        //add your code here
        this.radius = radius;
        return this.getArea();
    }
}

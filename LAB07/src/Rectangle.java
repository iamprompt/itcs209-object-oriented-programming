/*
 * The Rectangle class, subclass of Shape
 */
public class Rectangle extends Shape {
    // Private member variables
    //add your code here
    private double length;
    private double width;

    // Constructors
    public Rectangle() {
        //add your code here

    }

    public Rectangle(String color, double length, double width) {
        //add your code here
        this.length = length;
        this.width = width;
        super.setColor(color);
    }

    @Override
    public String toString() {
        //add your code here
        return "Rectangle[length=" + this.length + ", width=" + this.width + ", Shape[color=" + super.getColor() + "]]";
    }

    // Override the inherited getArea() to provide the proper implementation
    @Override
    public double getArea() {
        //add your code here
        return this.length * this.width;
    }

    public double getArea(double length, double width) {
        //add your code here
        this.length = length;
        this.width = width;
        return this.getArea();
    }
}



/*  The structure of Triangle class is similar to Rectangle */
public class Triangle extends Shape {
    // Private member variables
    //add your code here
    private double base;
    private double height;

    // Constructors
    public Triangle() {
        //add your code here

    }

    public Triangle(String color, double base, double height) {
        //add your code here
        this.base = base;
        this.height = height;
        super.setColor(color);
    }

    @Override
    public String toString() {
        //add your code here
        return "Triangle[base=" + this.base + ", height=" + this.height + ", Shape[color=" + super.getColor() + "]]";
    }

    // Override the inherited getArea() to provide the proper implementation
    @Override
    public double getArea() {
        //add your code here
        return (0.5) * this.base * this.height;
    }

    public double getArea(double base, double height) {
        //add your code here
        this.base = base;
        this.height = height;
        return this.getArea();
    }
}

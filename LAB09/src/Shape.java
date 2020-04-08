abstract class Shape implements Comparable {
    public static final double PI = 3.14;
    private String color;
    private String description;

    public Shape(String color, String description) {
        this.color = color;
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return this.description + " (color=" + this.color + ", area=" + this.getArea() + ")";
    }

    public int compareTo(Object shape) {
        double thisArea = this.getArea();
        double thatArea = ((Shape) shape).getArea();

        if (thisArea > thatArea) return 1;
        else if (thisArea == thatArea) return 0;
        else return -1;

    }

    public abstract double getArea();
}

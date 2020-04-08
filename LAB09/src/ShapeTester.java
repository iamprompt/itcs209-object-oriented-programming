import java.util.HashMap;
import java.util.Map;

public class ShapeTester {
    public static void main(String[] args) {
        HashMap<String, Shape> shapeMap = new HashMap<String, Shape>();
        shapeMap.put("circle_2", new Circle(10));
        shapeMap.put("my.circle", new Circle(2));
        shapeMap.put("2square", new Square(5));
        shapeMap.put("Square*", new Square(10));
        shapeMap.put("triangle2", new Triangle(20, 40));

        Triangle x = new Triangle(20, 10);
        if (shapeMap.get("my.circle").compareTo(x) == -1)
            System.out.println("my.circle is smaller than triangle x");
        if (shapeMap.get("Square*").compareTo(x) == 0)
            System.out.println("Square* is the same size as triangle x");
        if (shapeMap.get("triangle2").compareTo(x) == 1)
            System.out.println("triangle2 is larger than triangle x");
        System.out.println("--------------------");

        printAllShapes(shapeMap);

        System.out.println("--------------------");
        System.out.println("Number of invalid shape's name is " + countInvalidName(shapeMap));

        // Put two more object into the shapeMap
        // One object must have a valid name, another one must have invalid name

        shapeMap.put("*cat", new Circle(10));
        shapeMap.put("my_circle", new Circle(2));

        System.out.println("--------------------");

        printAllShapes(shapeMap);

        System.out.println("--------------------");
        System.out.println("Number of invalid shape's name is " + countInvalidName(shapeMap));
    }


    /*
     * Print all shapes in the HashMap
     * The format is: name -> shape description from toString() method.
     * For example: triangle2->Triangle with base 2.0, height  4.0 (color=red, area=4.0)
     * Note that the order of shape in your output might different from the expected output
     * This is OKAY!
     */
    public static void printAllShapes(HashMap<String, Shape> shapes) {
        // YOUR CODE GOES HERE
        for (Map.Entry<String, Shape> obj: shapes.entrySet()) {
            System.out.println(obj.getKey() + " -> " + obj.getValue());
        }
    }

    /*
     * Count number of shapes whose name is invalid
     * The valid name starts with a letter, followed by letters, digits, or underscores.
     * E.g., circle, Circle, my_Circle, circle_2
     * Here is an example of invalid name: 3circle, circle.my, circle*
     */
    public static int countInvalidName(HashMap<String, Shape> shapes) {
        // YOUR CODE GOES HERE
        int count = 0;

        for (Map.Entry<String, Shape> obj: shapes.entrySet()) {
            if (!(obj.getKey().matches("^([a-zA-Z])\\w+"))) count++;
        }

        // (You must delete the below statement "return 0;" when you implement this method.)
        return count;
    }


}

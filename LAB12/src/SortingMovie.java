// Supakarn Laorattanakul Section 3 ID:6288087

import java.util.ArrayList;

public class SortingMovie {

    public static void main(String[] args) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(1, "The Intern", 2009));
        movieList.add(new Movie(2, "The Gift", 2009));
        movieList.add(new Movie(3, "The Lost Room", 2009));
        movieList.add(new Movie(4, "The Gift", 2012));
        movieList.add(new Movie(5, "Pasolini", 2012));
        movieList.add(new Movie(6, "The Intern", 2009));
        movieList.add(new Movie(7, "American Ultra", 2019));
        movieList.add(new Movie(8, "Sweet Red Bean Paste", 2019));

        sort(movieList);
    }

    public static void sort(ArrayList<Movie> movies) {
        // Print Unsorted List
        System.out.println("== unsorted movie list ==");
        printMovies(movies);

        int swap = 1;
        // Sort
        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.size(); j++) {
                /*
                 * return 0  : same title, released year, mid
                 * return -1 : this comes before m
                 * return 1  : this comes after m
                 */
                //System.out.println(movies.get(i) + " " + movies.get(j) + " => " + movies.get(i).compareTo(movies.get(j)));
                if (movies.get(i).compareTo(movies.get(j)) == 1 && i < j) {
                    //System.out.println("\nSwapping " + movies.get(i) + " " + movies.get(j));

                    // Swapping
                    Movie temp = movies.get(i);
                    movies.set(i, movies.get(j));
                    movies.set(j, temp);

                    /*System.out.println("== Swapping " + swap++ + " ==");
                    printMovies(movies);*/
                }
            }
        }

        // Print Sorted List
        System.out.println("== sorted movie list ==");
        printMovies(movies);
    }

    private static void printMovies(ArrayList<Movie> movies) {
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println();
    }

}

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.nio.charset.StandardCharsets;

public class SimpleMovieDatabase {
    public Map<Integer, Movie> movies = null;

    public void importMovies(String movieFilename) {
        //YOUR CODE GOES HERE
        movies = new HashMap<Integer, Movie>();
        File file = new File(movieFilename);

        try {
            List<String> MoviesList = FileUtils.readLines(file, StandardCharsets.UTF_8);
            for (String mv : MoviesList) {
                Pattern p = Pattern.compile("(?<mid>\\d+)[,](?<title>(?:\\w+[\\s\\.\\:\\!\\-\\'\\?\\(\\)]*)+)[,](?<tag>\\([\\w+\\s?]+\\)|(?:\\w+\\-?\\|?)+)");
                Matcher m = p.matcher(mv);

                while (m.find()) {
                    // System.out.println(m.group("mid") + " " + m.group("title"));
                    int mid = Integer.parseInt(m.group("mid"));
                    String title = m.group("title").trim();
                    String[] tags = m.group("tag").split("\\|");

                    movies.put(mid, new Movie(mid, title));
                    movies.get(mid).tags.addAll(Arrays.asList(tags));
                }

                /*String[] MArr = m.split(",");
                if (MArr.length == 3) {
                    int mid = Integer.parseInt(MArr[0]);
                    String title = MArr[1].trim();
                    String[] tagArr = MArr[2].split("\\|");

                    if (mid > 0 && title.length() > 0) {
                        movies.put(mid, new Movie(mid, title));
                        movies.get(mid).tags.addAll(Arrays.asList(tagArr));
                    }
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //-------------------BONUS----------------------
    public List<Movie> searchMovies(String query) {
        //YOUR BONUS CODE GOES HERE
        List<Movie> result = new ArrayList<>();
        for (Movie m : movies.values()) {
            if (m.title.toLowerCase().contains(query)) {
                result.add(m);
            }
        }

        return result;
    }

    public List<Movie> getMoviesByTag(String tag) {
        //YOUR BONUS CODE GOES HERE
        List<Movie> result = new ArrayList<>();
        for (Movie m : movies.values()) {
            Iterator<String> T = m.tags.iterator();
            while (T.hasNext()) {
                if (T.next().equalsIgnoreCase(tag)) {
                    result.add(m);
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        SimpleMovieDatabase mdb = new SimpleMovieDatabase();
        mdb.importMovies("lab10_movies.txt");
        System.out.println("Done importing " + mdb.movies.size() + " movies");
        int[] mids = new int[]{139747, 141432, 139415, 139620, 141305};
        for (int mid : mids) {
            System.out.println("Retrieving movie ID " + mid + ": " + mdb.movies.get(mid));
        }

        //Uncomment for bonus
        System.out.println("\n////////////////////////// BONUS ///////////////////////////////");
        String[] queries = new String[]{"america", "thai", "thailand"};
        for (String query : queries) {
            System.out.println("Results for movies that match: " + query);
            for (Movie m : mdb.searchMovies(query)) {
                System.out.println("\t" + m);
            }
            System.out.println();
        }

        String[] tags = new String[]{"Musical", "Action", "Thriller"};
        for (String tag : tags) {
            System.out.println("Results for movies in category: " + tag);
            for (Movie m : mdb.getMoviesByTag(tag)) {
                System.out.println("\t" + m);
            }
            System.out.println();
        }
    }

}

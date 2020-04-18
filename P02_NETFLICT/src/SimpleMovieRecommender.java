import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMovieRecommender implements BaseMovieRecommender {
    Map<Integer, Movie> moviesList = new LinkedHashMap<>();
    Map<Integer, User> usersList = new LinkedHashMap<>();

    @Override
    public Map<Integer, Movie> loadMovies(String movieFilename) {
        File file = new File(movieFilename);
        try {
            List<String> moviesCSV = FileUtils.readLines(file, "UTF-8"); // Create List of String from CSV file
            moviesCSV.remove(0); // Remove Header Line
            Map<Integer, Movie> moviesMap = new LinkedHashMap<Integer, Movie>(); // Hashmap to store value and RETURN
            // RegEx for separating ID, Title, Year, and Tags and label for later usage (mid, title, year, tag)
            Pattern patt = Pattern.compile("(?<mid>\\d+),(?:(?:\")*(?<title>.+(?:\\((?!\\d{4})[^,]+\\)\\s)*)\\((?<year>\\d{4})\\)(?:\")*),(?<tags>(?:[\\w\\-]+\\|*)+|\\(no genres listed\\))");
            for (String m : moviesCSV) { // Loop through each line
                // System.out.println(m);
                Matcher match = patt.matcher(m); // Match String w/ RegEx
                while (match.find()) {
                    // System.out.println(match.group("mid") + " " + match.group("title").trim() + " " + match.group("year") + " " + match.group("tag"));

                    // Define text
                    int mid = Integer.parseInt(match.group("mid")); // Get mid
                    String title = match.group("title").trim(); // Get title
                    int year = Integer.parseInt(match.group("year")); // Get year
                    String[] tags = match.group("tags").split("\\|"); // Separate tags

                    // Put to the map
                    moviesMap.put(mid, new Movie(mid, title, year)); // Put movie to Map
                    for (String t : tags) {
                        moviesMap.get(mid).addTag(t); // Put all tags to Movie in Map
                    }
                }
            }
            // moviesMap.forEach((k,v) -> System.out.println(v));
            // System.out.println(moviesMap.size());
            return moviesMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Map<Integer, User> loadUsers(String ratingFilename) {
        File file = new File(ratingFilename);
        try {
            List<String> usersCSV = FileUtils.readLines(file, "UTF-8"); // Create List of String from CSV file
            usersCSV.remove(0); // Remove Header Line
            // System.out.println(usersCSV.size());
            Map<Integer, User> usersMap = new LinkedHashMap<Integer, User>(); // Hashmap to store value and RETURN
            for (String u : usersCSV) { // Loop through each line
                // System.out.println(u);
                // RegEx for separating ID, Title, Year, and Tags and label for later usage (mid, title, year, tag)
                Pattern patt = Pattern.compile("(?<uid>\\d+)[,](?<mid>\\d+)[,](?<rating>\\d+(?:\\.\\d+)*)[,](?<timestamp>\\d+)");
                Matcher match = patt.matcher(u); // Match String w/ RegEx
                while (match.find()) {
                    //System.out.println(match.group("uid") + " " + match.group("mid") + " " + match.group("rating") + " " + match.group("timestamp"));

                    // Define text
                    int uid = Integer.parseInt(match.group("uid")); // Get uid
                    int mid = Integer.parseInt(match.group("mid")); // Get mid
                    double rating = Double.parseDouble(match.group("rating")); // Get rating
                    long ts = Long.parseLong(match.group("timestamp")); // Get timestamp

                    // Put to the map
                    if (usersMap.get(uid) == null) usersMap.put(uid, new User(uid)); // Put movie to Map
                    usersMap.get(uid).addRating(this.getAllMovies().get(mid), rating, ts);
                }
            }

            // usersMap.forEach((k,v) -> System.out.println(v));
            // System.out.println(usersMap);
            return usersMap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void loadData(String movieFilename, String userFilename) {
        moviesList = loadMovies(movieFilename);
        usersList = loadUsers(userFilename);
    }

    @Override
    public Map<Integer, Movie> getAllMovies() {
        return moviesList;
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return usersList;
    }

    /**
     * Compute the user similarity between each pair of users, and produces an output model file "modelFilename"
     * With the following format:
     *
     * @NUM_USERS <num_users>
     * @USER_MAP {0=<user_id1>, 1=<user_id2>, 2=<user_id2>, ...}
     * @NUM_MOVIES <num_movies>
     * @MOVIE_MAP {0=<movie_id1>, 2=<movie_id2>, ...}
     * @RATING_MATRIX //is a num_users X (num_movies+1) matrix. Each element RATING_MATRIX(i,j) is the rating
     * //the user index i gives to the movie index j. For example (from test case "micro")
     * 4.0 0.0 1.5 4.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 3.1666666666666665
     * 0.0 3.5 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 3.5
     * 0.0 0.0 0.0 0.0 2.5 4.0 3.5 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 3.3333333333333335
     * @USERSIM_MATRIX //is a num_users X num_users matrix. Each element USERSIM_MATRIX(i,j) is the similarity
     * //score between the user index i and the user index j. For example (from test case "micro")
     * 1.0000000000000002 0.0 0.0
     * 0.0 0.0 0.0
     * 0.0 0.0 1.0000000000000002
     * <p>
     * You may assume that loadData() is called prior to the invocation of this method.
     */
    @Override
    public void trainModel(String modelFilename) {
        File file = new File(modelFilename);

        FileWriter writer;
        try {

            writer = new FileWriter(file, true);  //True = Append to file, false = Overwrite
            writer.write("@NUM_USERS " + this.getAllUsers().size() + "\r\n");
            writer.write("@USER_MAP {");
            int i = 0;
            for (int uid : this.getAllUsers().keySet()) {
                writer.write(i + "=" + uid);
                if (i != this.getAllUsers().size() - 1) {
                    writer.write(", ");
                    i++;
                } else writer.write("}\r\n");

            }
            writer.write("@NUM_MOVIES " + this.getAllMovies().size() + "\r\n");
            writer.write("@MOVIE_MAP {");
            int j = 0;
            for (int mid : this.getAllMovies().keySet()) {
                writer.write(j + "=" + mid);
                if (j != this.getAllMovies().size() - 1) {
                    writer.write(", ");
                    j++;
                } else writer.write("}\r\n");
            }
            writer.write("@RATING_MATRIX\r\n");
            for (User u : this.getAllUsers().values()) {
                int mCount = 0;
                for (Movie m : this.getAllMovies().values()) {
                    if (u.ratings.get(m.mid) != null) writer.write(u.ratings.get(m.mid).rating + "");
                    else writer.write("0.0");
                    if (mCount != this.getAllMovies().size()) writer.write(" ");
                    mCount++;
                }
                writer.write("\r\n");
            }
            writer.write("@USERSIM_MATRIX\r\n");
            for (User u : this.getAllUsers().values()) {
                int vCount = 0;
                for (User v : this.getAllUsers().values()) {
                    writer.write(String.format("%.1f",this.similarityCalc(u, v)));
                    if (vCount != this.getAllUsers().size() - 1) writer.write(" ");
                    else writer.write("\r\n");
                    vCount++;
                }
            }

            writer.close();

            System.out.println("Write success!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double similarityCalc(User user1, User user2) {
        // Find mid of movies that both user1 and user2 have rated
        List<Integer> intersectRatings = new ArrayList<>();
        for (Rating r1 : user1.ratings.values()) {
            for (Rating r2 : user2.ratings.values()) {
                if (r1.m.equals(r2.m)) {
                    intersectRatings.add(r1.m.getID());
                    break;
                }
            }
        }
        double numerators = 0;
        double U1 = 0;
        double U2 = 0;

        for (int m : intersectRatings) {
            //System.out.println(m);
            //System.out.println(user1 + " " + user2);
            //System.out.println("U1 " + user1.ratings.get(m) + " " + user1.getMeanRating());
            //System.out.println("U2 " + user2.ratings.get(m).rating + " " + user2.getMeanRating());
            numerators += (user1.ratings.get(m).rating - user1.getMeanRating()) * (user2.ratings.get(m).rating - user2.getMeanRating());
            U1 += Math.pow((user1.ratings.get(m).rating - user1.getMeanRating()), 2);
            U2 += Math.pow((user2.ratings.get(m).rating - user2.getMeanRating()), 2);
        }

        double denominators = Math.sqrt(U1) * Math.sqrt(U2); // If equals to 0, return 0.0

        return denominators != 0 ? numerators / denominators : 0.0;
    }

    @Override
    public void loadModel(String modelFilename) {

    }

    @Override
    public double predict(Movie m, User u) {
        return 0;
    }

    @Override
    public List<MovieItem> recommend(User u, int fromYear, int toYear, int K) {
        return null;
    }
}

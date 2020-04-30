import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

public class SimpleMovieRecommender implements BaseMovieRecommender {
    Map<Integer, Movie> moviesList = new LinkedHashMap<>();
    Map<Integer, User> usersList = new LinkedHashMap<>();

    Map<Integer, Map<Integer, Double>> ratingUser = new LinkedHashMap<Integer, Map<Integer, Double>>();
    Map<Integer, Map<Integer, Double>> simUser = new LinkedHashMap<Integer, Map<Integer, Double>>();


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
            // moviesMap.forEach((k, v) -> System.out.println(v));

            // Sort HashMap
            Map<Integer, Movie> sortedMoviesMap = moviesMap
                    .entrySet()
                    .stream()
                    .sorted(comparingByKey())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            // System.out.println(moviesMap.size());
            return sortedMoviesMap;
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
                /*Pattern patt = Pattern.compile("(?<uid>\\d+)[,](?<mid>\\d+)[,](?<rating>\\d+(?:\\.\\d+)*)[,](?<timestamp>\\d+)");
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
                }*/
                String[] userArr = u.split(",");

                if (userArr.length == 4) {
                    int uid = Integer.parseInt(userArr[0]); // Get uid
                    int mid = Integer.parseInt(userArr[1]); // Get mid
                    double rating = Double.parseDouble(userArr[2]); // Get rating
                    long ts = Long.parseLong(userArr[3]); // Get timestamp

                    if (usersMap.get(uid) == null) usersMap.put(uid, new User(uid)); // Put movie to Map
                    usersMap.get(uid).addRating(this.getAllMovies().get(mid), rating, ts);
                }
            }

            // Sort HashMap
            Map<Integer, User> sortedUsersMap = usersMap
                    .entrySet()
                    .stream()
                    .sorted(comparingByKey())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

            // sortedUsersMap.forEach((k,v) -> System.out.println(v));
            // System.out.println(usersMap);
            return sortedUsersMap;
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
            writer = new FileWriter(file, false);  //True = Append to file, false = Overwrite
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
                    writer.write(" ");
                    mCount++;
                }
                writer.write(u.getMeanRating() + "\r\n");
            }

            writer.write("@USERSIM_MATRIX\r\n");
            for (User u : this.getAllUsers().values()) {
                int vCount = 0;
                for (User v : this.getAllUsers().values()) {
                    writer.write(this.similarityCalc(u, v) + "");
                    if (vCount != this.getAllUsers().size() - 1) writer.write(" ");
                    else writer.write("\r\n");
                    vCount++;
                }
            }
            writer.close();

            // System.out.println("Write success!");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double similarityCalc(User user1, User user2) {
        double numerators = 0;
        double U1 = 0, U2 = 0;

        double U1Avg = user1.getMeanRating();
        double U2Avg = user2.getMeanRating();

        for (Rating r1 : user1.ratings.values()) {
            if (user2.ratings.get(r1.m.mid) != null) {
                numerators += (r1.rating - U1Avg) * (user2.ratings.get(r1.m.mid).rating - U2Avg);
                U1 += Math.pow((r1.rating - U1Avg), 2.0);
                U2 += Math.pow((user2.ratings.get(r1.m.mid).rating - U2Avg), 2.0);
            }
        }
        double denominators = Math.sqrt(U1) * Math.sqrt(U2); // If equals to 0, return 0.0
        // double denominators = Math.sqrt(U1 * U2); // If equals to 0, return 0.0

        if (denominators == 0) return 0.0;
        else {
            double result = numerators / denominators;
            if (user1.compareTo(user2) == 0) return 1;
            else if (result >= -1 && result <= 1) return result;
            else if (result < -1) return -1;
            else return 1;
        }
    }

    @Override
    public void loadModel(String modelFilename) {
        File file = new File(modelFilename);
        try {
            List<String> lines = FileUtils.readLines(file, "UTF-8"); // Create List of String from CSV file
            /*
             * line 0 : @NUM_USERS <num_users>
             * line 1 : @USER_MAP {0=<user_id1>, 1=<user_id2>, 2=<user_id2>, ...}
             * line 2 : @NUM_MOVIES <num_movies>
             * line 3 : @MOVIE_MAP {0=<movie_id1>, 2=<movie_id2>, ...}
             * line 4 : @RATING_MATRIX
             * line 5 - 5 + n(U) : 4.0 0.0 1.5 4.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 3.1666666666666665
             * line 5 + n(U) + 1 : @USERSIM_MATRIX //is a num_users X num_users matrix. Each element USERSIM_MATRIX(i,j) is the similarity
             * line 5 + n(U) + 2 - 6 + n(U) + 2 + n(U) : 1.0000000000000002 0.0 0.0
             */

            // Query UID map to Array following INDEX
            String userMapArr[] = lines.get(1).substring(11, lines.get(1).length() - 1).split(", ");
            List<Integer> uMap = new ArrayList<>(); // UID Map
            for (String uM : userMapArr) {
                int k = Integer.parseInt(uM.substring(uM.indexOf('=') + 1, uM.length()));
                uMap.add(k);
            }

            // Query MID map to Array following INDEX
            String movieMapArr[] = lines.get(3).substring(12, lines.get(3).length() - 1).split(", ");
            List<Integer> mMap = new ArrayList<>(); // MID Map
            for (String mM : movieMapArr) {
                int k = Integer.parseInt(mM.substring(mM.indexOf('=') + 1, mM.length()));
                mMap.add(k);
            }

            int userSize = usersList.size();
            int ratingStart = 5; // starting line of @RATING_MATRIX
            int simStart = 5 + userSize + 1; // starting line of @USERSIM_MATRIX

            for (int i = 0; i < userSize; i++) {
                // Rating - MID -> Map rating to mid
                String[] userRatingArr = lines.get(ratingStart + i).split(" ");
                Map<Integer, Double> userRatingMap = new LinkedHashMap<>();
                for (int ur = 0; ur < mMap.size(); ur++) {
                    userRatingMap.put(mMap.get(ur), Double.parseDouble(userRatingArr[ur]));
                }
                userRatingMap.put(-1, Double.parseDouble(userRatingArr[mMap.size()]));

                // Map User Similarity to UID
                String[] userSimArr = lines.get(simStart + i).split(" ");
                Map<Integer, Double> userSimMap = new LinkedHashMap<>();
                for (int us = 0; us < uMap.size(); us++) {
                    userSimMap.put(uMap.get(us), Double.parseDouble(userSimArr[us]));
                }

                ratingUser.put(uMap.get(i), userRatingMap);
                simUser.put(uMap.get(i), userSimMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Predicts the rating that the user "u" would give to the movie "m".
     * The range of predicted rating must be [0,5.0] inclusive.
     * You may assume that loadData() and loadModel() is called prior to the invocation of
     * this method.
     * <p>
     * If u does not exist in the training file, simply return u.getMeanRating();
     *
     * @param m
     * @param u
     * @return
     */
    @Override
    public double predict(Movie m, User u) {
        Map<Integer, Double> thisUserSimLine = simUser.get(u.uid);
        Map<Integer, Double> thisUserRatingLine = ratingUser.get(u.uid);

        double meanUser = u.getMeanRating();

        if (ratingUser.get(u.uid) != null){
            double numerator = 0;
            double denominator = 0;

            int matchUser = 0;

            for (Map.Entry<Integer, Map<Integer, Double>> anotherU : ratingUser.entrySet()) {
                if (anotherU.getKey() != u.uid && anotherU.getValue().get(m.mid) != 0.0) {
                    numerator += (thisUserSimLine.get(anotherU.getKey()) * (anotherU.getValue().get(m.mid) - anotherU.getValue().get(-1)));
                    denominator += Math.abs(thisUserSimLine.get(anotherU.getKey()));
                    matchUser++;
                }
            }

            double result = matchUser == 0 || denominator == 0.0 ? meanUser : meanUser + (numerator / denominator);

            if (result >= 0.0 && result <= 5.0) return result;
            else if (result > 5.0) return 5.0;
            else return 0.0;
        } else return meanUser;
        /*double userMean = u.getMeanRating();

        double num = 0;
        double den = 0;

        if (simUser.get(u.uid) == null) return userMean;
        else {
            thisUserSim = simUser.get(u.uid);
            thisUserRating = ratingUser.get(u.uid);
            userMean = thisUserRating.get(-1);

            for (Map.Entry<Integer, Map<Integer, Double>> ratingU : ratingUser.entrySet()) {
                // Todo: Except u naja
                if (ratingU.getValue().get(m.mid) != 0.0) {
                    double sim = thisUserSim.get(ratingU.getKey());
                    num += (sim * (ratingU.getValue().get(m.mid) - ratingU.getValue().get(-1)));
                    den += Math.abs(sim);
                }
            }
            return den == 0.0 ? userMean : userMean + (num / den);
        }*/
    }

    @Override
    public List<MovieItem> recommend(User u, int fromYear, int toYear, int K) {
        Map<Integer, Movie> qualifiedMYear = new LinkedHashMap<>();
        for (Movie m : moviesList.values()) {
            if (m.year >= fromYear && m.year <= toYear) qualifiedMYear.put(m.mid, m);
        }
        List<MovieItem> movie = new ArrayList<>();
        for (Movie m : qualifiedMYear.values()) {
            movie.add(new MovieItem(m, predict(m, u)));
        }
        Collections.sort(movie, Collections.reverseOrder());
        return movie.size() > K ? movie.subList(0, K - 1) : movie;
    }
}

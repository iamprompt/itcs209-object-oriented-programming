
public class Song {
    private String title;
    private double duration;

    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return getMinDuration() + "." + getSecDuration();
    }

    public int getDurationInSec() {
        return (getMinDuration() * 60) + getSecDuration();
    }

    public String toString() {
        return getTitle() + ": " + getDuration() + " minutes (" + getDurationInSec() + " seconds)";
    }

    private int getMinDuration() {
        String durString = Double.toString(this.duration);
        String minPart = durString.split("\\.")[0]; // Get Minute part (before .)
        int min = Integer.parseInt(minPart);
        return min;
    }

    private int getSecDuration() {
        String durString = Double.toString(this.duration); // In decimal part, 0 has been removed.
        String secPart = durString.split("\\.")[1]; // Get Second part (after .)
        if (secPart.length() == 1) secPart += "0"; // Add 0 to the end of the number (It's been removed when converted to String)
        int sec = Integer.parseInt(secPart);
        return sec;
    }
}

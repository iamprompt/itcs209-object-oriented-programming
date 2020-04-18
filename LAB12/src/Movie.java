// Supakarn Laorattanakul Section 3 ID:6288087

public class Movie implements Comparable<Movie> {
    public int mid = -1;
    public String title = null;
    public int year = -1;

    public Movie(int _mid, String _title, int _year) {
        mid = _mid;
        title = _title;
        year = _year;
    }


    public String toString() {
        return "[mid:" + mid + " |" + title + " |" + year + "]";
    }


    /*
     * return 0  : same title, released year, mid
     * return -1 : this comes before m
     * return 1  : this comes after m
     */
    public int compareTo(Movie m) {
        if (this.mid == m.mid && this.title.compareTo(m.title) == 0 && this.year == m.year) return 0;
        else if (this.title.compareTo(m.title) == 0) {
            if (this.year < m.year) return -1;
            else if (this.year == m.year) {
                if (this.mid < m.mid) return -1;
                else if (this.mid > m.mid) return 1;
                else return 0;
            } else return 1;
        } else if (this.title.compareTo(m.title) < 0) return -1;
        else return 1;
    }
}
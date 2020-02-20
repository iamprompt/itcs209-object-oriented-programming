/*
 Name: Supakarn Laorattanakul
 ID: 6288087
*/

import java.util.ArrayList;

public class Playlist {
    private String name;
    private int duration; // Duration in Second
    private ArrayList<Song> songs;


    public Playlist(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }

    public void addSong(Song aSong) {
        addDuration(aSong);
        songs.add(aSong);
    }

    public void addSongAtIndex(Song aSong, int index) {
        if (index < songs.size() && index >= 0) {
            addDuration(aSong);
            songs.add(index, aSong);
        } else System.out.println("Error: Couldn't add song at index " + index);
    }

    public boolean removeSongByIndex(int index) {
        if (songs.size() > 0 && index < songs.size() && index >= 0) {
            removeDuration(songs.get(index));
            songs.remove(index);
            return true;
        } else {
            System.out.println("Error: The index is invalid");
            return false;
        }
    }

    public boolean removeSongByTitle(String title) {
        int obj = -1;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(title)) obj = i;
        }
        if (obj != -1) {
            removeDuration(songs.get(obj));
            songs.remove(obj);
            return true;
        } else {
            System.out.println("Error: The title is not found");
            return false;
        }
    }

    public void moveUp(int current) {
        Song toMove = songs.get(current);
        songs.set(current, songs.get(current - 1));
        songs.set(current - 1, toMove);
    }

    public void moveDown(int current) {
        Song toMove = songs.get(current);
        songs.set(current, songs.get(current + 1));
        songs.set(current + 1, toMove);
    }

    public double getPlaylistDuration() {
        int MinDur = this.duration / 60;

        int SecDur = this.duration % 60;
        String SecDurTxt = Integer.toString(SecDur);
        if (SecDur >= 0 && SecDur < 10) SecDurTxt = "0" + SecDur; // Add 0 to the front of a single number

        return Double.parseDouble(MinDur + "." + SecDurTxt);
    }

    public void showPlaylist() {
        System.out.println(name);
        for (int i = 0; i < songs.size(); i++) {
            System.out.println("[" + i + "] " + songs.get(i).toString());
        }
        System.out.println("Total duration is " + getPlaylistDuration() + " minutes");
    }

    private void addDuration(Song aSong) {
        this.duration += aSong.getDurationInSec();
    }

    private void removeDuration(Song aSong) {
        this.duration -= aSong.getDurationInSec();
    }
}
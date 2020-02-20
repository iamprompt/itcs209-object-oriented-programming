// DO NOT MODIFY THIS CODE

public class SongApp {
	public static void main(String[] args) {
		Playlist myPlaylist = new Playlist("My Favorite Songs Playlist");
		
		System.out.println("Welcome to SongAPP");
		System.out.println("\nAdd songs --------------------------");
		myPlaylist.addSong(new Song("Perfect", 4.21));
		myPlaylist.addSong(new Song("How long", 3.30));
		myPlaylist.addSongAtIndex(new Song("End Game", 4.11), 0);
		myPlaylist.addSongAtIndex(new Song("Anywhere", 3.35), 2);
		myPlaylist.showPlaylist();
	
		System.out.println("\nRearrange songs ---------------------");
		myPlaylist.moveUp(1);
		myPlaylist.moveDown(2);
		myPlaylist.showPlaylist();
		
		System.out.println("\nRemove songs -----------------------");
		myPlaylist.removeSongByTitle("End Game");
		myPlaylist.removeSongByIndex(2);
		myPlaylist.showPlaylist();
		
		System.out.println("\nCheck error ------------------------");
		myPlaylist.addSongAtIndex(new Song("Find you", 3.38), 3);
		myPlaylist.removeSongByTitle("Find you");
		myPlaylist.removeSongByIndex(3);
	}
	
}

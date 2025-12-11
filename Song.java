//Not sure if we want to use

public class Song {
    private int songId;
    private String title;
    private String artist;
    private int memberID;

    public Song(int songId, String title, String artist, int memberID) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.memberID = memberID;
    }

    public int getSongId() {
        return songId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getMemberID() {
        return memberID;
    }
}

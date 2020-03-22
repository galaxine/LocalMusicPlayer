package FileReader;

public class MP3File {
    private String absoluteFilename;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String duration;

    public MP3File(String absoluteFilename) {
        this.absoluteFilename = "\\" + absoluteFilename;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAbsoluteFilename() {
        return absoluteFilename;
    }

    @Override
    public String toString() {
        return "MP3File{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

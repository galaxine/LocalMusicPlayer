import FileReader.Playlist;

public class LocalPlayer {
    public static void main(String[] args) {
        if (args.length == 0) {
            Playlist playlist = new Playlist(System.getProperty("user.dir"));
        } else {
            Playlist playlist = new Playlist(args[0]);
        }
    }
}

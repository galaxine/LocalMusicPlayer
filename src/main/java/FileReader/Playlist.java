package FileReader;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

/**
 * The Playlist is supposed to read the file
 */
public class Playlist {
    private LinkedList<MP3File> playlist;
    private File file;

    public Playlist(String arguments) {
        this.file = new File(arguments);
        this.playlist = new LinkedList<>();
        System.out.println(file.toString());
        System.out.println("Is a directory: " + file.isDirectory() + " and absolute: " + file.isAbsolute());
        findAndAddMp3Files();
    }

    private void findAndAddMp3Files() {
        try {
            for (String name : Objects.requireNonNull(file.list())) {
                if (name.endsWith(".mp3")) {
                    playlist.add(new MP3File(name));
                }
            }
            System.out.println("size of the mp3 LinkedList" + playlist.size());
            for (MP3File track : playlist
            ) {
                System.out.println(track.toString());
            }
        } catch (NullPointerException e) {
            try {
                throw new Exception_100();
            } catch (Exception_100 exception_100) {
                exception_100.printStackTrace();
            }
        }

    }

}

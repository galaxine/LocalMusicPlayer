package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.Media;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 * The Playlist is supposed to read the file arguments and then create a playlist to manipulate it.
 */
public class Playlist {
    private LinkedList<MP3File> playlist;
    private File file;
    /**
     * instantiates the file and Playlist
     * @param arguments  is the argument which is a filedirectory.
     */
    public Playlist(String arguments) {
        this.file = new File(arguments);
        this.playlist = new LinkedList<>();
        findAndAddMp3Files();
        shufflePlaylist();

    }


    private void shufflePlaylist() {
        Collections.shuffle(this.playlist);
    }

    public void showPlaylist(){
        for (MP3File track: playlist
             ) {
            System.out.println(track.toString());
        }
    }

    /**
     * Finds mp3 files in the given filedirectory and adds it into the playlist and
     *
     */
    private void findAndAddMp3Files() {
        try {
            for (String name : Objects.requireNonNull(file.list())) {
                if (name.endsWith(".mp3")) {
                    playlist.add(new MP3File(file.getAbsolutePath() + "\\" + name));
                }
            }


        } catch (NullPointerException e) {
            try {
                throw new Exception_100();
            } catch (Exception_100 exception_100) {
                exception_100.printStackTrace();
            }
        }
    }

    public LinkedList<MP3File> getPlaylist() {
        return playlist;
    }

    public File getFile() {
        return file;
    }
}

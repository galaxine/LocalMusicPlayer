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
    /**
     * Linked List of type MP3File that is containing our tracks
     */
    private LinkedList<MP3File> playlist;
    /**
     * File object needed to facilitate the file handling
     */
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

    /**
     * shuffles the playlist by pseudorandom means
     */
    private void shufflePlaylist() {
        Collections.shuffle(this.playlist);
    }

    /**
     * shows each Mp3 Tracks metadata in the playlist Linked list.
     */
    public void showPlaylist(){
        for (MP3File track: playlist
             ) {
            System.out.println(track.toString());
        }
    }

    /**
     * Finds mp3 files in the given filedirectory and adds it into the playlist by seeking any files that end with a
     * ".mp3". If there are no files or the given path is wrong, throws an exception that gives directs
     * to the problem.
     */
    private void findAndAddMp3Files() {
        try {
            // for each name in th elist of the list of available files in the list
            for (String name : Objects.requireNonNull(file.list())) {
                //add those files that end in ".mp3" into the playlist with it's absolute path as constructor parameter
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

    /**
     * Get the playlist to access and interact with it
     * @return a mp3 classed Linked list.
     */
    public LinkedList<MP3File> getPlaylist() {
        return playlist;
    }

    /**
     * returns the file object to use it outside this class
     * @return returns the file object of this class
     */
    public File getFile() {
        return file;
    }
}

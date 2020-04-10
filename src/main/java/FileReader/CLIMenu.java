package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.util.Scanner;

/**
 * The CLIMenu class that handles all the logic regarding the Commandline Menu usage.
 */
public class CLIMenu {
    /**
     * the mediaplayerfactory is needed to deploy a mediaplayer
     */
    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    /**
     * Mediaplayer is instantiated by creating a new mediaplayer through the mediaplayerfactory
     */
    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
    /**
     * Playlist class that's handling the mp3files.
     */
    private Playlist playlist;
    /**
     * The constructor takes the Commandline arguments or the file directory name and transfers it into the
     * Playlist object constructor as a parameter
     * @param arguments is the given parameters to start
     */
    public CLIMenu(String arguments) {
        this.playlist = new Playlist(arguments);
        printMetaData();
        startPlayMusic();
        startNextTracks();
        menu();
    }

    /**
     * Is the menu for the Commandline that has following functions:
     * plays the music automatically
     * shows metadata if "song" is entered
     * shows current playlist metadata when "playlist" is entered
     * exits the software by stopping the music and releasing the mediaplayer and mediaplayerfactory.
     */
    private void menu() {
        instructions();
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String command = scanner.nextLine();
                switch (command) {
                    case "exit":
                        mediaPlayer.controls().stop();
                        mediaPlayer.release();
                        mediaPlayerFactory.release();
                        break;
                    case "song":
                        System.out.println(this.playlist.getPlaylist().getFirst().toString());
                        break;
                    case "playlist":
                        this.playlist.showPlaylist();
                    default:
                        help();
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
     * If the user has typed anything else that's not acceptable, the acceptable functions are
     * displayed.
     */
    private void help() {
        System.out.println("Please enter either \"song\", \"playlist\" or " +
                "\"exit\" to control the menu");
    }

    /**
     * The instructions on how to use the CLI Menu
     */
    private void instructions() {
        System.out.println("Welcome to the upcoming internet player.\n"
        + "Please choose from the following:\n" +
                "enter \"song\" to get more information about the title, album, duration etc.");
        System.out.println("enter \" playlist\" to get the current track order \n" +
                "enter \"exit\" in order to exit the program ");
    }

    /**
     * prints the metadata of the playlist's mp3file linkedlist
     */
    private void printMetaData() {
        for (MP3File track : playlist.getPlaylist()
             ) {
            System.out.println(track.toString());
        }
    }

    /**
     * plays the music by running a new Thread and submitting the first track in the playlist.
     */
    private void startPlayMusic() {
        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());
            }
        });
    }

    /**
     * Waits for the currently playing mp3 track to end before the next track gets ontop of the list and is played.
     * This is now repeating until you stop.
     */
    private void startNextTracks(){
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {

                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                        datastructure();
                        mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());
                    }
                });
            }
        });
    }

    /**
     * Adds to the last index the first index object
     * then removes the first object index
     */
    private void datastructure () {
        this.playlist.getPlaylist().addLast(this.playlist.getPlaylist().getFirst());
        this.playlist.getPlaylist().removeFirst();
    }

}

package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.util.Scanner;

public class CLIMenu {
    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
    private Playlist playlist;
    /*
        TODO: in this Userstory, we are creaitng a CLI menu with following features
            1. if the word "song" is entered, every data about the current track is given
            2. "playlist" will show the current order
            3. if "exit" shall exit the program
            4. a help prompt if none of the words are entered.
     */
    public CLIMenu(String arguments) {
        this.playlist = new Playlist(arguments);
        printMetaData();
        startPlayMusic();
        startNextTracks();
        menu();
    }

    private void menu() {
        instructions();
        Scanner scanner = new Scanner(System.in);
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


    }

    private void help() {
        System.out.println("Please enter either \"song\", \"playlist\" or " +
                "\"exit\" to control the menu");
    }

    private void instructions() {
        System.out.println("Welcome to the upcoming internet player.\n"
        + "Please choose from the following:\n" +
                "enter \"song\" to get more information about the title, album, duration etc.");
        System.out.println("enter \" playlist\" to get the current track order \n" +
                "enter \"exit\" in order to exit the program ");
    }

    private void printMetaData() {
        for (MP3File track : playlist.getPlaylist()
             ) {
            System.out.println(track.toString());
        }
    }

    private void startPlayMusic() {
        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());
            }
        });
    }

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

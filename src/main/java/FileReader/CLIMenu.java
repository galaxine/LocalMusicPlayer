package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

public class CLIMenu {
    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();
    private Playlist playlist;

    public CLIMenu(String arguments) {
        this.playlist = new Playlist(arguments);
        printMetaData();
        startPlayMusic();
        startNextTracks();
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

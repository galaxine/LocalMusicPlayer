package model;

import FileReader.MP3File;
import FileReader.Playlist;
import javafx.application.Platform;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This is our Model, which encompasses the
 * logic behind the GUI of the LocalPlayer.
 * It is independent of the View and Controller.
 * It provides methods for the data requests and
 * informs the observer (controller) if any changes
 * occur.
 * Addendum: this class shouldn't have any javaFX relevant
 * elements. Any.
 * TODO: I seem to repeat myself playing the tracks. that needs to be fixed. New exclusive class?
 */
public class Model {
    private MediaPlayerFactory factory = new MediaPlayerFactory();
    private MediaPlayer mediaPlayer = factory.mediaPlayers().newMediaPlayer();
    private Playlist playlist;
    private PropertyChangeSupport support;
    private MP3File track = null;
    /**
     * @param filedirectory self explanatory, assigned to the playlist class constructor
     */
    public Model(final String filedirectory) {
        support = new PropertyChangeSupport(this);
        playlist = new Playlist(filedirectory);
        System.out.println(playlist.getPlaylist().getFirst().toString());
        menu();
    }
    private void menu(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                playTrack();
                playNextTrack();
            }
        });
    }

    /**
     * Should play the next track AFTER the track before has finished.
     */
    private void playNextTrack() {
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(final MediaPlayer mediaPlayer) {
                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                        playlist.nextTrack();
                        setNextTrack(playlist.getPlaylist().getFirst());
                        mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());
                    }
                });
            }
        });
    }

    /**
     * plays the first track.
     */
    private void playTrack() {
        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {
                setNextTrack(playlist.getPlaylist().getFirst());
                mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());
            }
        });
    }

    /**
     * this method sets the next track and then fires the property change.
     */
    public void setNextTrack(MP3File newTrack) {
        MP3File oldTrack =  this.track;
        this.track = newTrack;
        this.support.firePropertyChange("track", oldTrack, newTrack);
    }

    /**
     * Propertychangelistener for observer suscription.
     *
     * @param observer the current controller that should listen to this model
     */
    public void addPropertyChangeListener(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    /**
     * removes the subscription of the observer.
     *
     * @param observer the current controller is stopping listening to this model.
     */
    public void removePropertyChangeListener(PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

}

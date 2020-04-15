package model;

import FileReader.MP3File;
import FileReader.Playlist;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;

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
 */
public class Model {
    private MediaPlayerFactory factory = new MediaPlayerFactory();
    private MediaPlayer mediaPlayer = factory.mediaPlayers().newMediaPlayer();
    private Playlist playlist;
    private PropertyChangeSupport support;

    public Model(String filedirectory) {
        support = new PropertyChangeSupport(this);
        playlist = new Playlist(filedirectory);
        playTrack();
        playNextTrack();
    }

    private void playNextTrack() {
    }

    private void playTrack() {
    }

    /**
     *
     */
    public void setTrack() {
        MP3File oldTrack = playlist.getPlaylist().getFirst();
        playlist.nextTrack();
        MP3File newTrack = playlist.getPlaylist().getFirst();
        support.firePropertyChange("track", oldTrack, newTrack);
    }

    
    public void addPropertyChangeListener(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

}

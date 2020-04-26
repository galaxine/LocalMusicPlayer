package model;

import FileReader.MP3File;
import FileReader.Playlist;
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
 * Todo: playlist is constantly stuck, first solve this before I start to implement the firePropertyChange() into
 * the setTrackMethod;
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
    public Model(String filedirectory) {
        support = new PropertyChangeSupport(this);
        playlist = new Playlist(filedirectory);
        playTrack();
        playNextTrack();
    }

    /**
     * Should play the next track AFTER the track before has finished.
     */
    private void playNextTrack() {
        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(MediaPlayer mediaPlayer) {
                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {
                        setTrack();
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
                mediaPlayer.media().play(playlist.getPlaylist().getFirst().getAbsoluteFilename());

            }
        });
    }

    /**
     * this method sets the next track and then fires the property change.
     * TODO: implement the method. It should be something like this:
     * assign current mp3Track to track, assign to the oldTrack the track parameter.
     * THEN execute nextTrack from @playlist to assign it to newTrack.
     * So like this:
     * track = playlist.getCurrentTrack
     * oldTrack = track
     * track = playlist.nextTrack
     * newTrack = track
     * fire supporttrigger (track, oldTrack, newTrack)
     */
    private void setTrack() {
        track = playlist.getPlaylist().getFirst();//from the first index of playlist
        MP3File oldTrack = track;// assign track to old track
        playlist.nextTrack(); //add the first track to the end of the arrayList, abcda -> bcda ->bcdab ->cdab -> ...
        track = playlist.getPlaylist().getFirst(); // assign next track from playlist to track
        MP3File newTrack = track; //track to newTrack
        support.firePropertyChange("track", oldTrack, newTrack); //track should have changed, with old and new TRack as
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

    /**
     *
     */
    public void assignFirstPieceToTrack() {
        this.track = playlist.getPlaylist().getFirst();
    }
}

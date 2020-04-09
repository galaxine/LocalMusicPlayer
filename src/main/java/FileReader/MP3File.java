package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.Meta;
import uk.co.caprica.vlcj.media.MetaData;
import uk.co.caprica.vlcj.waiter.media.ParsedWaiter;

/**
 * The class Mp3 File has to have not only the absolutefilename to interact with the files themselves, they
 * also have to contain a few metadata to make sure that there is some kind of information to derive what the
 * hell you are listening to.
 */
public class MP3File {
    /**
     * Absolute filepath to access the mp3file
     */
    private String absoluteFilename;
    /**
     * The title of the mp3file
     */
    private String title;
    /**
     * artist name
     */
    private String artist;
    /**
     * Album name
     */
    private String album;
    /**
     * genre
     */
    private String genre;
    /**
     * Duration of the song, eventually it needs to be converted into minutes and seconds to give the users
     * a more conrete idea how long it runs.
     */
    private long duration;

    /**
     * The constructor of the Mp3file class takes the parameter of the absolute file path that is needed to
     * get the Metadata.
     * @param absoluteFilename is essentially needed to assign metadata to the other parameters
     */
    public MP3File(String absoluteFilename) {
        this.absoluteFilename = absoluteFilename;
        parseMetaDataToMp3File();
    }

    /**
     * creates a Mediaplayerfactory object to derive a Media object and parse the metadata to the private variables.
     * afterwards, media is released from memory, then mediaplayerfactory as well.
     * The Mediaplayerfactory inside the Playlist or CLIMenu is not transferred because i fear it will conflict and give
     * wrong data. This is easier to debug.
     */
    private void parseMetaDataToMp3File() {
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

        final Media media = mediaPlayerFactory.media().newMedia(absoluteFilename);
        final ParsedWaiter parsed = new ParsedWaiter(media) {
            @Override
            protected boolean onBefore(Media component) {
                return media.parsing().parse();
            }
        };

        try {
            parsed.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final MetaData metadata = media.meta().asMetaData();
        final InfoApi api = media.info();

        this.title = metadata.get(Meta.TITLE);
        this.artist = metadata.get(Meta.ARTIST);
        this.album = metadata.get(Meta.ALBUM);
        this.genre = metadata.get(Meta.GENRE);

        this.duration = api.duration();

        media.release();
        mediaPlayerFactory.release();
    }

    /**
     * To work with the mp3file directly
     * @return absolute path of the Mp3File
     */
    public String getAbsoluteFilename() {
        return absoluteFilename;
    }

    /**
     * To string with the relevant metadata to show in a CLIMenu
     * @return album, track, genre, duration and artist name for a start
     */
    @Override
    public String toString() {
        return "MP3File{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}

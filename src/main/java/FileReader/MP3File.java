package FileReader;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.Meta;
import uk.co.caprica.vlcj.media.MetaData;
import uk.co.caprica.vlcj.waiter.media.ParsedWaiter;

public class MP3File {
    private String absoluteFilename;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private long duration;
    public MP3File(String absoluteFilename) {
        this.absoluteFilename = absoluteFilename;
        parseMetaDataToMp3File();
    }

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

    public String getAbsoluteFilename() {
        return absoluteFilename;
    }

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

package FileReader;

public class MP3File {
    String absoluteFilename;

    public MP3File(String absoluteFilename) {
        this.absoluteFilename = absoluteFilename;
    }

    @Override
    public String toString() {
        return "MP3File{" +
                "filename='" + absoluteFilename + '\'' +
                '}';
    }

    public String getAbsoluteFilename() {
        return absoluteFilename;
    }
}

import FileReader.CLIMenu;

/**
 * This is the MainClass of the software. Takes an environment variable(?) and looks there for mp3 files.
 * If there is no given argument, the current project directory will be assigned.
 */
public class LocalPlayer {
    public static void main(String[] args) {
        if (args.length == 0) {
            CLIMenu menu = new CLIMenu(System.getProperty("user.dir"));
        } else {
            CLIMenu menu = new CLIMenu(args[0]);
        }
    }
}

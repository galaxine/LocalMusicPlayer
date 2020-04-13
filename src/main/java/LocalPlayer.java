import FileReader.CLIMenu;

/**
 * This is the MainClass of the software. Takes an environment variable(?) and looks there for mp3 files.
 * If there is no given argument, the current project directory will be assigned.
 */
public class LocalPlayer {
    public static void main(String[] args) {
        String system = System.getProperty("user.dir");
        if (args.length == 0) {// if the args is zero
            CLIMenu menu = new  CLIMenu(system); // start the menu by assigning it the system.dir
        } else if (args[0].contains("/") || args[0].contains("\\")) {// if it contains a / or \\
            CLIMenu menu = new CLIMenu(args[0]);// assign it to the constructor, is the filedirectory
        } else if (args[0].equals("--gui")) { //else if it has a --gui or -g,
            MusicplayerGUI.main(args); //let the MusicplayerGUi decide what to do with the args.
        }
    }
}

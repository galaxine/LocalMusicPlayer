import FileReader.CLIMenu;

public class LocalPlayer {
    public static void main(String[] args) {
        if (args.length == 0) {
            CLIMenu menu = new CLIMenu(System.getProperty("user.dir"));
        } else {
            CLIMenu menu = new CLIMenu(args[0]);
        }
    }
}

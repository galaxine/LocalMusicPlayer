package FileReader;

/**
 * The Exception 100 is there if there is no file or directory found. this way you canm
 */
public class Exception_100 extends Exception {
    // I am NOT sure how this really works, but I can go on and assign the same number as my exceptions and spend time
    // later to get what it is about.
    static final long serialVersionUID = 100L;

    /**
     * the constructor with overriden super method printing a more appropriate error for debugging.
     */
    public Exception_100() {

        super("Error 100, there is no file or directory found. ");
    }
}

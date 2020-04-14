import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * This is our Controller (Observer) takes Userinput and works it out. Typically
 * it gets the Eventlistener, which listens to for an example button-clicks, and
 * triggers actions which the Model let loose
 */
public class GUIController {
    @FXML
    TextArea textArea;

    public void initialize() {
        Label l = new Label("I am a new Label");

    }
}

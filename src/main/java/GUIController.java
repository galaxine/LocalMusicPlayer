import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This is our Controller (Observer) takes Userinput and works it out. Typically
 * it gets the Eventlistener, which listens to for an example button-clicks, and
 * triggers actions which the Model let loose
 */
public class GUIController implements PropertyChangeListener {
    @FXML
    TextArea textArea;

    public void initialize() {
        Label l = new Label("I am a new Label");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

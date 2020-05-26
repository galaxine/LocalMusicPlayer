import javafx.fxml.FXML;
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

  /*  public void initialize() {
        Label l = new Label("I am a new Label");

    }*/

    /**
     * Listens to the model class and at first should change the View (fxml) to show the current
     * @param evt for now, it should register the property track changing at {@link model.Model}'s setNexTrack
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("musicListener");
        if (evt.getPropertyName().equals("track")) {
            System.out.println(evt.toString());
            System.out.println("listener active");
            TextArea textArea = new TextArea(evt.getNewValue().toString());
            setTextArea(textArea);
        }
    }

    public void setTextArea(TextArea textArea) {
        this.textArea = textArea;
    }
}

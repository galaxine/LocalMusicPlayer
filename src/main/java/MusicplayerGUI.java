import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MusicplayerGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        String filedirectory = "";
        if (getParameters().getRaw().size() > 1) {
            filedirectory = getParameters().getRaw().get(1);
        } else {
            filedirectory = System.getProperty("user.dir");
        }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MusicPlayerGUI.fxml"));
        Pane root = fxmlLoader.load();
        GUIController controller = fxmlLoader.getController();
        Model model = new Model(filedirectory);
        model.addPropertyChangeListener(controller);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Localplayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

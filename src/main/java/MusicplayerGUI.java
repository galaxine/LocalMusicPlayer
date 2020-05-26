import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Model;

import java.io.IOException;

public class MusicplayerGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        String filedirectory = "";
        if (getParameters().getRaw().size() > 1) {
            filedirectory = getParameters().getRaw().get(1);
        } else {
            filedirectory = System.getProperty("user.dir");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MusicplayerGUI.fxml"));
        Pane root = fxmlLoader.load();
        GUIController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Localplayer");
        primaryStage.setScene(scene);
        primaryStage.show();
        Model model = new Model(filedirectory);
        model.addPropertyChangeListener(controller);

    }

    public static void main(String[] args) {
        launch(args);
    }
}

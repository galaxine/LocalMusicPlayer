import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MusicplayerGUI extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        List<String> data = getParameters().getRaw();
        for (String string: data
             ) {
            System.out.println(string);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MusicPlayerGUI.fxml"));

        Pane root = fxmlLoader.load();
        GUIController controller = fxmlLoader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Localplayer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

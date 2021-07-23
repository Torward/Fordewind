import com.sun.javafx.css.Style;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CloudApp extends Application {
    private Pane leftScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("cSfirstScreen.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.setTitle("FORDERWIND");
        primaryStage.show();
    }
}

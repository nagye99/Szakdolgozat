import database.HandleData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        HandleData.deleteDatabase();
        HandleData.initializeDatabase();

        Parent root = FXMLLoader.load(getClass().getResource("/openPage.fxml"));
        stage.setTitle("Helyfoglalási lista Intercitykhez");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}

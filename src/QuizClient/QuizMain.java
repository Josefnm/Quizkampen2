package QuizClient;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Client client = new Client();
        FXMLLoader qsl = new FXMLLoader(getClass().getResource("/QuizClient/QuestionScene.fxml"));
        QuestionSceneController qsc = new QuestionSceneController(client);
        qsl.setController(qsc);
        
        Scene scene = new Scene(qsl.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

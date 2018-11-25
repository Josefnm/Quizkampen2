package GameClient;

import GameServer.InfoPacket;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Josef
 */
public class PopupScene {

    private GameMain main;
    private Scene popupScene;
    private Button cancelPopupBtn;

    public PopupScene(GameMain main) throws IOException, ClassNotFoundException {
        this.main = main;
        BorderPane bp = new BorderPane();

        bp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        //bp.setId("score-test");

        popupScene = new Scene(bp, (main.getBoardHeight() / 2), (main.getBoardThicc() / 2));
        popupScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());

        cancelPopupBtn = new Button("Cancel");
        cancelPopupBtn.setId("button-test3");
        Text searching = new Text("Letar efter spelare...");
        searching.setId("textvit");
        ProgressIndicator pi = new ProgressIndicator();
        VBox pop = new VBox(searching, pi, cancelPopupBtn);
        //pop.setStyle("-fx-margin: 20");

        pop.setAlignment(Pos.CENTER);
        pop.setSpacing(10);
        bp.setCenter(pop);

        cancelPopupBtn.setOnAction(e -> {
            main.client.sendObject("cancel");
            main.closePopupStage();
        });

        new Thread(() -> {
            try {
                
                InfoPacket input = (InfoPacket) main.client.getInStream().readObject();

                //Platform.runlater anropar Application-tråden. Detta görs för att i javafx så kan bara scener köras från den tråden.
                Platform.runLater(() -> {
                    
                    main.getQuestionScene().setQuestions(input.getQuestions());
                    main.getQuestionScene().setNextQuestion();
                    main.setQuestionScene();
                    main.closePopupStage();
                });
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();
    }

    public Scene getScene() {
        return popupScene;
    }
}

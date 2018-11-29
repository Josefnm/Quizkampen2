package GameClient;

import GameServer.IdEnum;
import GameServer.InfoPacket;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PopupScene {

    private ClientMain main;
    private Scene popupScene;
    private Button cancelPopupBtn;

    public PopupScene(ClientMain main) {
        this.main = main;
        BorderPane bp = new BorderPane();

        bp.setStyle("-fx-background-color: darkslateblue");
        //bp.setId("score-test");
        popupScene = new Scene(bp, (main.getBoardWidth() / 2), (main.getBoardHeight() / 2));
        popupScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
        cancelPopupBtn = new Button("Cancel");
        cancelPopupBtn.getStyleClass().add("button");
        cancelPopupBtn.setId("cancelKnapp");
        Text searching = new Text("Letar efter spelare...");
        searching.getStyleClass().add("text_white");
        ProgressIndicator pi = new ProgressIndicator();
        VBox pop = new VBox(searching, pi, cancelPopupBtn);
        //pop.setStyle("-fx-margin: 20");
        pop.setAlignment(Pos.CENTER);
        pop.setSpacing(10);
        bp.setCenter(pop);

        cancelPopupBtn.setOnAction(e -> {
            main.getClient().send(new InfoPacket(IdEnum.CANCEL));
            main.closePopupStage();
        });
    }

    public Scene getScene() {
        return popupScene;
    }
}


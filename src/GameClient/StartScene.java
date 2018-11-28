package GameClient;

import GameServer.IdEnum;
import GameServer.InfoPacket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartScene { //fixar abstrakt senare

    private ImageView userAvatar;
    private Scene startScene;
    private ClientMain main;
    private Text userName;

    public StartScene(ClientMain main) {
        this.main = main;

        userAvatar = new ImageView(new Image(main.getUserAvatar()));
        userAvatar.setFitWidth(40);
        userAvatar.setPreserveRatio(true);
        userAvatar.setSmooth(true);
        userAvatar.setCache(true);

        userName = new Text(main.getUserName());
        userName.getStyleClass().add("text_white");

        Label nameLabel = new Label("Username:");
        nameLabel.getStyleClass().add("text_white");
        Button startBtn = new Button("Play?");
        startBtn.getStyleClass().add("button");
        startBtn.setId("startsceneKnapp");
        //startbtn.setMinSize(150, 25);

        startBtn.setOnAction(e -> {
            main.getClient().send(new InfoPacket(IdEnum.START));
            main.setPopupScene();

            System.out.println("hejdå");
        });

        //borderpane, vbox, hbox
        VBox vbox = new VBox(nameLabel, userName);
        HBox hbox = new HBox(userAvatar, vbox);
        HBox hboxKnapp = new HBox(startBtn);
        VBox vboxAllt = new VBox(hbox, hboxKnapp);
        vboxAllt.setSpacing(8);
        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hboxKnapp.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(20, 20, 20, 20));
        BP.setId("pane");
        BP.setCenter(vboxAllt);

        startScene = new Scene(BP, main.getBoardWidth(), main.getBoardHeight());
        startScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return startScene;
    }

}

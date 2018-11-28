package GameClient;

import GameClient.ClientMain;
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

    private Scene startScene;
    private ClientMain main;
    private Text userName;

    public StartScene(ClientMain main) {
        this.main = main;
        //label 1 "användarnamn"
        //label 2 "actual användarnamn"-betydligt större font
        //Avatarbild, den ska även gå att ändra

        //entryScene = new EntryScene(main);
        Image avatarBoy = new Image("./images/boy.png"); //en getter från server i slutändan?

        ImageView avatar = new ImageView();
        avatar.setImage(avatarBoy);
        avatar.setFitWidth(40);
        avatar.setPreserveRatio(true);
        avatar.setSmooth(true);
        avatar.setCache(true);

        Label nameLabel = new Label("Username:");
        userName = new Text(main.getUserName()); //kopplat till användarens input
        userName.setStyle("-fx-font-size: 15; -fx-fill: white;");
        //   Text userName = new Text("hej"); //kopplat till användarens input
        Button startbtn = new Button("Play?");
        startbtn.setId("button-test4");
        startbtn.setMinSize(150, 25);

        startbtn.setOnAction(e -> {
            main.getClient().send(new InfoPacket(IdEnum.START));
            main.setPopupScene();

            System.out.println("hejdå");
        });

        //borderpane, vbox, hbox
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(20, 20, 20, 20));
        BP.setId("pane");
        VBox vbox = new VBox(nameLabel, userName);
        HBox hbox = new HBox(avatar, vbox);
        HBox hboxKnapp = new HBox(startbtn);
        VBox vboxAllt = new VBox(hbox, hboxKnapp);
        vboxAllt.setSpacing(8);

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hboxKnapp.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);

        //BP.setBottom(hboxKnapp);
        BP.setCenter(vboxAllt);
        //BP.setTop(startbtn);
        //BP.setBottom(avatar);
        startScene = new Scene(BP, main.getBoardWidth(), main.getBoardHeight());
        startScene.getStylesheets().add(getClass().getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return startScene;
    }

}

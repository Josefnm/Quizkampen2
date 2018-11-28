package GameClient;

import GameServer.Question;
import GameServer.InfoPacket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class StartScene { //fixar abstrakt senare

    Scene startScene;
    GameMain main;
    Client client;
    QuestionScene qs;
    Scene popupScene;
    Button cancelPopupBtn;
    InfoPacket input;
    Thread listener = null;
    EntryScene entry;
    
    Text realUsername; //för vår setter
    ImageView avatar;

    public StartScene(GameMain main) {
        this.main = main;
        client = main.client;



        avatar = new ImageView();
        
        //avatar.setImage("");
        avatar.setFitWidth(40);
        avatar.setPreserveRatio(true);
        avatar.setSmooth(true);
        avatar.setCache(true);
        entry = new EntryScene(main);

        Label username = new Label("Username:");
        username.getStyleClass().add("text_white");

        

        realUsername = new Text("");
        realUsername.getStyleClass().add("text_white");
        Button startbtn = new Button("Play?");
        startbtn.getStyleClass().add("button");
        startbtn.setId("startsceneKnapp");


        startbtn.setOnAction(e -> {
            //TODO försöka få popupen att dyka upp på muspekarens position (kanske +- x, y)
            client.sendObject("start");
            try {
                popUp(main);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        //borderpane, vbox, hbox
        BorderPane BP = new BorderPane();
        BP.setPadding(new Insets(20, 20, 20, 20));
        BP.setId("pane");
        VBox vbox = new VBox(username, /*realUser,*/ realUsername); //leker
        HBox hbox = new HBox(avatar, vbox);
        HBox hboxKnapp = new HBox(startbtn);
        VBox vboxAllt = new VBox(hbox, hboxKnapp);
        vboxAllt.setSpacing(8);

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hboxKnapp.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);

        BP.setCenter(vboxAllt);

        this.startScene = new Scene(BP, main.getBoardHeight(), main.getBoardThicc());

        startScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());
    }

    public void setAvatar(Image input)
    {
        System.out.println(input);
        avatar.setImage(input);
    }
    public Scene getScene() {
        return startScene;
    }
    public void setUsername(String name)
    {
        System.out.println("username start");
        realUsername.setText(name);
    }

    public void popUp(GameMain main) throws IOException, ClassNotFoundException {
        BorderPane bp = new BorderPane();
        
        bp.setStyle("-fx-background-color: darkslateblue");
        popupScene = new Scene(bp, (main.getBoardHeight() / 2), (main.getBoardThicc() / 2));

        popupScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());

        cancelPopupBtn = new Button("Cancel");
        cancelPopupBtn.getStyleClass().add("button");
        cancelPopupBtn.setId("cancelKnapp");
        Text searching = new Text("Letar efter spelare...");
 
        searching.getStyleClass().add("text_white");
        ProgressIndicator pi = new ProgressIndicator();
        VBox pop = new VBox(searching, pi, cancelPopupBtn);
        

        pop.setAlignment(Pos.CENTER);
        pop.setSpacing(10);
        bp.setCenter(pop);

        
        main.setPopupScene(popupScene);

        //lambdaknappaction
        System.out.println("before thread");

        if (listener == null) { //annars blir det två trådar om man trycker cancel och sen start igen
            listener = new Thread(() -> { //annars hängde sig popupen
                System.out.println("thread start");
                try {
                    this.input = (InfoPacket) client.getInStream().readObject();
                    System.out.println("input");
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
                }
//Platform.runlater anropar Application-tråden. Detta görs för att i javafx så kan bara scener köras från den tråden.
                Platform.runLater(() -> {
                    main.questionScene.setQuestions(input.getQuestions());
                    main.questionScene.setNextQuestion();
                    main.setQuestionScene();
                    main.closePopupStage();
                });

            });
            listener.start();
        }
        
        cancelPopupBtn.setOnAction(e -> {
            client.sendObject("cancel");
            main.closePopupStage();
        });
    }
    public Scene getPopupScene() {
        System.out.println("kom tillbaka");
        return popupScene;
    }
}

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
        System.out.println("inne i start, username: " + main.tempUsername());
        //verkar inte uppdatera förens efter popupen
        Label username = new Label("Username:");
        System.out.println(main.userName);
        System.out.println("22222222222222");
        
        //realUsername = new Text("hehehehehehe"); //<---
        
        
        //realUser = new Text("KAPTEN BAJS"); //kopplat till användarens input
        //realUser.setStyle("-fx-font-size: 15; -fx-fill: white;");
        realUsername = new Text("");
        realUsername.getStyleClass().add("text_white");
        //realUsername.setStyle("-fx-font-size: 15; -fx-fill: white;");
      //   Text realUser = new Text("hej"); //kopplat till användarens input
        Button startbtn = new Button("Play?");
        startbtn.setId("button-test4");
        startbtn.setMinSize(150, 25);

        //lambda
        //tanken att vi genom start engagear servern
        startbtn.setOnAction(e -> {
            //TODO försöka få popupen att dyka upp på muspekarens position (kanske +- x, y)
            client.sendObject("start");
//            startbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent t) 
//            {
//                //txt=(TextBox)t.getSource();
//                if(t.getButton()==MouseButton.SECONDARY )
//                {
//                    popup.show(txt,t.getScreenX(),t.getScreenY());                   
//                }
//            }
            try {
                //main.setMainScene();
                //main.setScoreScene();
                popUp(main);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            //main.setScoreScene();
            System.out.println("hejdå");
        }); //hej funkar 100%
        //bonus, onclick, popop med spinning wheel + cancelknapp vars funktion stänger popupen

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

        //BP.setBottom(hboxKnapp);
        BP.setCenter(vboxAllt);

        //BP.setTop(startbtn);
        //BP.setBottom(avatar);
//        cancelPopupBtn.setOnAction(e -> {
//        System.out.println("hejdu");
//        main.closePopupStage();
//        System.out.println("nukörvi");
//        });
        this.startScene = new Scene(BP, main.getBoardHeight(), main.getBoardThicc());

        startScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());
    }
//    public void setAvatar(String input)
//    {
//        System.out.println(input);
//        if (input == "boy") avatar.setImage(avatarBoy);
//        else if (input == "girl")  avatar.setImage(avatarGirl);
//        else avatar.setImage(avatarPikatchu);//gäller för default samt not sure
//
//    }
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
        
        bp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        //bp.setId("score-test");
        popupScene = new Scene(bp, (main.getBoardHeight() / 2), (main.getBoardThicc() / 2));

        popupScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());
        //adda progressbar 
        //popup.cancelPopupBtn(primaryStage);
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

        System.out.println("HEHEHEHEH");
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
            System.out.println("hejdu");
            client.sendObject("cancel");
            main.closePopupStage();

            System.out.println("nukörvi");
        });
    }
    public Scene getPopupScene() {
        System.out.println("kom tillbaka");
        return popupScene;
    }
}

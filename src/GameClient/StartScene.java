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
    private Text userName;

    public StartScene(GameMain main) {
        this.main = main;
        client = main.client;
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

        System.out.println("inne i start, username: " + main.tempUsername());
        //verkar inte uppdatera förens efter popupen
        Label nameLabel = new Label("Username:");
        
        userName = new Text("spelarensNamn"); //kopplat till användarens input
        userName.setStyle("-fx-font-size: 15; -fx-fill: white;");
        //   Text userName = new Text("hej"); //kopplat till användarens input
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

    public Scene getScene() {
        return startScene;
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
            listener = new Thread(() -> { 
                System.out.println("popup: " + listener.getName());
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
                System.out.println("thread closed");
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

    public void setUserName(String userName) {
        this.userName.setText(userName);
    }
}

//    this.setName("QuizKampen");
//    createAndShowUI();
//    initComponents();
//    
//    private void createAndShowUI() {
//        this.setResizable(false);
//        this.setSize(500, 500);
//        this.setLocationRelativeTo(null); //Placerar fönstret i mitten
//        this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//    private void initComponents() {
//        Button startButton = new Button("Start Game");
//    }
//    
//
//    public void actionPerformed(ActionEvent e) {
//        try {
//            //start
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
//}
//        startbtn.setOnAction(new EventHandler<ActionEvent>() {
//
//            
//
//            @Override
//
//            public void handle(ActionEvent event) {
//                
//                System.out.println("hhehe");
//                main.setQuestionScene(/*client,*/);
//            }
//            
//        });

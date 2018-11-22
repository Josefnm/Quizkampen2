package GameClient;

import GameServer.Question;
import GameServer.StartPacket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class StartScene { //fixar abstrakt senare

    Scene startScene;
    Main main;
    Client client;
    QuestionScene qs;
    Scene popupScene;
    Button show;
    StartPacket input;
//

    public StartScene(Main main) {
        this.main = main;
        client = main.client;
        //label 1 "användarnamn"
        //label 2 "actual användarnamn"-betydligt större font
        //Avatarbild, den ska även gå att ändra

        Image avatarBoy = new Image("./images/boy.png"); //en getter från server i slutändan?

        ImageView avatar = new ImageView();
        avatar.setImage(avatarBoy);
        avatar.setFitWidth(40);
        avatar.setPreserveRatio(true);
        avatar.setSmooth(true);
        avatar.setCache(true);

        Label username = new Label("Username:");
        Text realUser = new Text("realDonaldTrump"); //kopplat till användarens input
        Button startbtn = new Button("Play?");
        startbtn.setMinSize(150, 25);

        //lambda
        //tanken att vi genom start engagear servern
        startbtn.setOnAction(e -> {
            client.sendObject("start");
            try {
                //main.setMainScene();
                //main.setScoreScene();
                popUp(main);
            } catch (IOException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            //main.setScoreScene();
            System.out.println("hejdå");
        }); //hej funkar 100%
        //bonus, onclick, popop med spinning wheel + cancelknapp vars funktion stänger popupen

        //borderpane, vbox, hbox
        BorderPane BP = new BorderPane();
        BP.setId("pane");
        VBox vbox = new VBox(username, realUser);
        HBox hbox = new HBox(avatar, vbox);
        HBox hboxKnapp = new HBox(startbtn);
        VBox vboxAllt = new VBox(hbox, hboxKnapp);

        hbox.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        hboxKnapp.setAlignment(Pos.CENTER);
        vboxAllt.setAlignment(Pos.CENTER);

        //BP.setBottom(hboxKnapp);
        BP.setCenter(vboxAllt);

        //BP.setTop(startbtn);
        //BP.setBottom(avatar);
//        show.setOnAction(e -> {
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

    public void popUp(Main main) throws IOException, ClassNotFoundException {
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        //bp.setId("score-test");
        popupScene = new Scene(bp, (main.getBoardHeight() / 2), (main.getBoardThicc() / 2));

        //adda progressbar 
        //popup.show(primaryStage);
        Button show = new Button("Cancel");
        Text searching = new Text("Letar efter spelare...");
        ProgressIndicator pi = new ProgressIndicator();
        VBox pop = new VBox(searching, pi, show);
        pop.setStyle("-fx-margin: 20");

        pop.setAlignment(Pos.CENTER);
        pop.setSpacing(5);
        bp.setCenter(pop);

        System.out.println("HEHEHEHEH");
        main.setPopupScene(popupScene);

        //lambdaknappaction
        show.setOnAction(e -> {
            System.out.println("hejdu");
            main.closePopupStage();
            System.out.println("nukörvi");
        });
        System.out.println("before thread");

        new Thread(() -> { //annars hängde sig popupen
            System.out.println("thread start");
            try {

                input = (StartPacket) client.getInStream().readObject();
                System.out.println("input recieved");
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(StartScene.class.getName()).log(Level.SEVERE, null, ex);
            }
            Platform.runLater(() -> {
                   main.qs.setQuestions(input.getQuestions());
                   main.qs.setNextQuestion();
                   main.setQuestionScene();
                   main.closePopupStage();
                });

        }).start();
//        Object input=client.getInStream().readObject();
//        main.setQuestionScene();

        //skapa popupruta
        //innehåller progressbar
        //samt knapp cancel
        //kopplad till boolean
    }

    public Scene getPopupScene() {
        System.out.println("kom tillbaka");
        return popupScene;
    }
    //main.set

    //primaryStage.setTitle("BMI Calculator");
    //primaryStage.setScene(startScene);
    //primaryStage.show();
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

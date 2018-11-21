package GameClient;

import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author Josef
 */
public class StartScene { //fixar abstrakt senare

    Scene startScene;
    Main main;
    Client client;
    QuestionScene qs;

    public StartScene(Main main) {
        this.main = main;

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
       // startbtn.setOnAction(e -> {main.setMainScene(); System.out.println("hejdå");}); //hej funkar 100%
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
        
        
        

      //  this.startScene = new Scene(BP, main.getBoardHeight(), main.getBoardThicc());
        
        startScene.getStylesheets()
                .add(StartScene.class
                        .getResource("stylingCSS.css").toExternalForm());
    }

    public Scene getScene() {
        return startScene;
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

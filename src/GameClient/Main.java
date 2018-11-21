/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.io.IOException;
import java.util.HashSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author Josef
 */
public class Main extends Application {

    private Stage stage;
    StartScene sc;
    Client client;
    Scene tempScene;
    QuestionScene qs;
    Scene mainScene;
    ScoreScene scoresc;
    //popuptest
    Stage stagepop;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
//        stage = primaryStage;
//      //  sc = new StartScene(this);
////        qs = new QuestionScene(this);
        primaryStage.setTitle("Quiz!");

        client = new Client();
        stage = primaryStage;
        stagepop = new Stage();
        sc = new StartScene(this);
        qs = new QuestionScene();
        scoresc = new ScoreScene(this);
        primaryStage.setTitle("QUIZKAMPEN!");
        primaryStage.setScene(sc.getScene());
        primaryStage.show();

        Button btn = new Button("lol");
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        mainScene = new Scene(root, getBoardHeight(), getBoardThicc());
        btn.setOnAction(e -> setScoreScene());
        //btn.setOnAction(e -> popUp());
        //test
        stagepop.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            stagepop.hide();
            stagepop.close();
            System.out.println("closed");
        });
    }

    public void setQuestionScene() {
        tempScene = qs.getScene();
        stage.setScene(tempScene);
        //allt kan egentligen göras med en metod som tar in scenen de ska bytas till
        //med nackdelen att metoden skulle vara aningens mer otydlig då namnet
        //skulle bli "setScene" istället för vilken scen
    }

    public void setMainScene() {
        tempScene = mainScene;
        stage.setScene(tempScene);
        //stage.initModality(Modality.WINDOW_MODAL);
    }

    public void setScoreScene() {
        tempScene = scoresc.getScene();
        stage.setScene(tempScene);
        //stage.initModality(Modality.WINDOW_MODAL);
    }

    public void setStartScene() {
        tempScene = sc.getScene();
        stage.setScene(tempScene);
    }

    public void setPopupScene(Scene scene) {
        System.out.println("kom in i main");
        tempScene = scene;
        sc.getPopupScene();
        stagepop.setScene(tempScene);
        if (stagepop.getModality() == Modality.NONE) {
            stagepop.initModality(Modality.APPLICATION_MODAL);
        } //ger popupkänslan
         //måste dock fixa visible-shizzle genom close()

    }

    public void closePopupStage() {
        System.out.println("tjenalolol");
        stagepop.close();
        System.out.println("tvåtvå");
    }

    public int getBoardHeight() {
        return 300;
    }

    public int getBoardThicc() {
        return 506;
    }

    public void popUp() {
        final Popup popup = new Popup();
        popup.setX(300);
        popup.setY(200);
        //popup.show(primaryStage);
        Button show = new Button("Cancel");

        popup.getContent().addAll(show);
        popup.show(stage);
        show.setOnAction(e -> {
            popup.hide();
            System.out.println("hejdå");
        });

        //skapa popupruta
        //innehåller progressbar
        //samt knapp cancel
        //kopplad till boolean
    }

//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene tempScene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    //instans av scorescene och retunerar sin respektive panel
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GameMain extends Application {

    public static int counter = 0;
    
    private Stage stage;
    StartScene sc;
    Client client;
    Scene tempScene;
    QuestionScene questionScene;
    Scene mainScene;
    ScoreScene scoresc;
    //popuptest
    Stage stagepop;
   
    EntryScene entryScene;
    
    String userName;
    
    public GameMain() {
        counter++;
        System.out.println("I AM IN THIS" + counter);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        primaryStage.setTitle("Quiz!");

        client = new Client();
        stage = primaryStage;
        stagepop = new Stage();
        entryScene = new EntryScene(this);
        sc = new StartScene(this);
        entryScene.startInitiated(sc); //<------
        questionScene = new QuestionScene(this);
        scoresc = new ScoreScene(this);
        entryScene.scoreInitiated(scoresc); // <---
        primaryStage.setTitle("QUIZKAMPEN!");
        primaryStage.setScene(entryScene.getScene());
        primaryStage.show();

        
        //detta handlar om second stage (aka popups) x-funktion, tack vare josef överflödig?
        stagepop.setOnCloseRequest(event -> {
        System.out.println("Stage is closing"); 
        stagepop.hide();
        stagepop.close();
        System.out.println("closed");
        System.out.println(entryScene.getUsername()); //ger null
        });
        
    }
    public String tempUsername()
    {
        System.out.println("tempUserName "+entryScene);
        return entryScene.getUsername();
    }

    public void setQuestionScene() {
        tempScene = questionScene.getScene();
        stage.setScene(tempScene);
        //allt kan egentligen göras med en metod som tar in scenen de ska bytas till
        //med nackdelen att metoden skulle vara aningens mer otydlig då namnet
        //skulle bli "setScene" istället för vilken scen

        //^Bra med olika metoder som det är nu tycker jag /josef
    }

    public void setMainScene() {
        tempScene = mainScene;
        stage.setScene(tempScene);
    }

    public void setScoreScene() {
        tempScene = scoresc.getScene();
        stage.setScene(tempScene);
    }
    public void setEntryScene()
    {
        tempScene = entryScene.getScene();
        stage.setScene(tempScene);
    }

    public void setStartScene()
    {
        tempScene = sc.getScene();
        stage.setScene(tempScene);
    }

    public void setPopupScene(Scene scene) {
        tempScene = scene;
        sc.getPopupScene();
        stagepop.setScene(tempScene);

        if (stagepop.getModality() == Modality.NONE) {
            stagepop.initModality(Modality.APPLICATION_MODAL); //ger popupkänslan
        }
        stagepop.show();
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
    
    public static void main(String[] args) {
        launch(args);

    }
}

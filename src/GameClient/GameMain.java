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
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GameMain extends Application {

    private Stage primaryStage;
    Stage popupStage;
    Client client;

    Scene tempScene;
    StartScene startScene;
    QuestionScene questionScene;
    ScoreScene scoreScene;
    EntryScene entryScene;
    
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        client = new Client();
        this.primaryStage = primaryStage;
        popupStage = new Stage();
        entryScene = new EntryScene(this);
        startScene = new StartScene(this);
        questionScene = new QuestionScene(this);
        scoreScene = new ScoreScene(this);
        primaryStage.setTitle("QUIZKAMPEN!");
        primaryStage.setScene(entryScene.getScene());
        primaryStage.show();

        //detta handlar om second stage (aka popups) x-funktion, tack vare josef överflödig?
        popupStage.setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
            popupStage.hide();
            popupStage.close();
            System.out.println("closed");
            System.out.println(entryScene.getUsername()); //ger null
        });

    }

    public String tempUsername() {
        System.out.println("tempUserName " + entryScene);
        return entryScene.getUsername();
    }

    public void tempGenderChoice() {
        //en setter kanske vore något
    }

    public void setQuestionScene() {
        tempScene = questionScene.getScene();
        primaryStage.setScene(tempScene);
    }

    public void setScoreScene() {
        scoreScene.ThreadCalling();
        tempScene = scoreScene.getScene();
        
        primaryStage.setScene(tempScene);
    }

    public void setEntryScene() {
        tempScene = entryScene.getScene();
        primaryStage.setScene(tempScene);
    }

    public void setStartScene() {
        tempScene = startScene.getScene();
        primaryStage.setScene(tempScene);
    }

    public void setPopupScene(Scene scene) {
        tempScene = scene;
        startScene.getPopupScene();
        popupStage.setScene(tempScene);

        if (popupStage.getModality() == Modality.NONE) {
            popupStage.initModality(Modality.APPLICATION_MODAL);

        }
        popupStage.show();//ger popupkänslan
    }

    public void closePopupStage() {
        System.out.println("tjenalolol");
        popupStage.close();
        System.out.println("tvåtvå");
    }

    public int getBoardHeight() {
        return 300;
    }

    public int getBoardThicc() {
        return 506;
    }

//    public void popUp() {
//        final Popup popup = new Popup();
//        popup.setX(300);
//        popup.setY(200);
//        //popup.show(primaryStage);
//        Button cancelBtn = new Button("Cancel");
//
//        popup.getContent().addAll(cancelBtn);
//        popup.show(primaryStage);
//        cancelBtn.setOnAction(e -> {
//            popup.hide();
//            System.out.println("hejdå");
//        });
//
//        //skapa popupruta
//        //innehåller progressbar
//        //samt knapp cancel
//        //kopplad till boolean
//    }


    public static void main(String[] args) {
        launch(args);

    }

    public void setUserName(String userName) {
        scoreScene.setUserName(userName);
        startScene.setUserName(userName);
    }
}

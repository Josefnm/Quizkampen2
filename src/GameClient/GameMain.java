/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameMain extends Application {

    private Stage primaryStage;
    Stage popupStage;
    Client client;

    private StartScene startScene;
    private QuestionScene questionScene;
    private ScoreScene scoreScene;
    private EntryScene entryScene;
    private PopupScene popupScene;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {

        client = new Client();
        int rounds = (Integer)client.getInStream().readObject();
        this.primaryStage = primaryStage;
        popupStage = new Stage();
        popupStage.initStyle(StageStyle.UNDECORATED); //tar bort standardpanelen högst upp som vanliga windowsfönster har
        popupStage.initModality(Modality.APPLICATION_MODAL);
        entryScene = new EntryScene(this);
        startScene = new StartScene(this);
        questionScene = new QuestionScene(this);
        scoreScene = new ScoreScene(this, rounds);
        popupScene=new PopupScene(this);
        primaryStage.setTitle("QUIZKAMPEN!");
        primaryStage.setScene(entryScene.getScene());
        primaryStage.show();
    }

    public void tempGenderChoice() {
        //en setter kanske vore något
    }

    public void setQuestionScene() {
        primaryStage.setScene(questionScene.getScene());
    }

    public void setScoreScene() {
        scoreScene.inputThread();
        primaryStage.setScene(scoreScene.getScene());
    }

    public void setEntryScene() {      
        primaryStage.setScene(entryScene.getScene());
    }

    public void setStartScene() {
        primaryStage.setScene(startScene.getScene());
    }

    public void setPopupScene() {
        popupStage.setScene(popupScene.getScene());
        popupStage.setX(primaryStage.getX()+getBoardHeight()/4);
        popupStage.setY(primaryStage.getY()+getBoardThicc()/4);
        popupStage.show();//ger popupkänslan
    }

    public void closePopupStage() {
        popupStage.close();
    }

    public int getBoardHeight() {
        return 300;
    }

    public int getBoardThicc() {
        return 506;
    }

    public void setUserName(String userName) {
        scoreScene.setUserName(userName);
        startScene.setUserName(userName);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public QuestionScene getQuestionScene() {
        return questionScene;
    }

    public ScoreScene getScoreScene() {
        return scoreScene;
    }
}

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

    private Stage stage;
    StartScene sc;
    Client client;
    Scene tempScene;
    QuestionScene questionScene;
    Scene mainScene;
    ScoreScene scoresc;
    //popuptest
    Stage stagepop;
   
    EntryScene entryScene ; //= new EntryScene(this);;
    
    String userName;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
//        stage = primaryStage;
//      //  sc = new StartScene(this);
////        questionScene = new QuestionScene(this);
        primaryStage.setTitle("Quiz!");

        client = new Client();
        stage = primaryStage;
        stagepop = new Stage();
        entryScene = new EntryScene(this);
        sc = new StartScene(this);
        questionScene = new QuestionScene();
        scoresc = new ScoreScene(this);
        primaryStage.setTitle("QUIZKAMPEN!");
        primaryStage.setScene(entryScene.getScene());
        primaryStage.show();

        //Button btn = new Button("lol");
        //StackPane root = new StackPane();
        //root.getChildren().add(btn);
        //användes vid tidigare tester
        //mainScene = new Scene(root, getBoardHeight(), getBoardThicc());
        //btn.setOnAction(e -> setScoreScene());
        //btn.setOnAction(e -> popUp());
        //test
        
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
        String hej = "";
        System.out.println("tempUserName "+entryScene);
        return entryScene.getUsername();
    }
    public void tempGenderChoice()
    {
        //en setter kanske vore något
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
        //stage.initModality(Modality.WINDOW_MODAL);
    }

    public void setScoreScene() {
        tempScene = scoresc.getScene();
        stage.setScene(tempScene);
        //stage.initModality(Modality.WINDOW_MODAL);
    }
    public void setEntryScene()
    {
        tempScene = entryScene.getScene();
        stage.setScene(tempScene);
    }

    public void setStartScene() {
        tempScene = sc.getScene();
        stage.setScene(tempScene);
    }

    public void setPopupScene(Scene scene) {
        tempScene = scene;
        sc.getPopupScene();
        stagepop.setScene(tempScene);

        if (stagepop.getModality() == Modality.NONE) {
            stagepop.initModality(Modality.APPLICATION_MODAL);

        }
        stagepop.show();//ger popupkänslan
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

    public static void main(String[] args) {
        launch(args);

    }
}

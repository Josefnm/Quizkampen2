/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
    
    @Override
    public void start(Stage primaryStage) {
//        stage = primaryStage;
//        Scene scene = logInScene();
//        primaryStage.setScene(scene);
//        primaryStage.show();

        
        stage = primaryStage;
        sc = new StartScene(this);
        qs = new QuestionScene(this);
        primaryStage.setTitle("Quiz!");
        primaryStage.setScene(sc.getScene());
        //setMain();
        primaryStage.show();
        
        Button btn = new Button("lol");
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        mainScene = new Scene(root, getBoardHeight(), getBoardThicc());
        btn.setOnAction(e -> setQuestionScene());
    }  
    public void setQuestionScene()
    {
        tempScene = qs.getScene();
        stage.setScene(tempScene);
        //allt kan egentligen göras med en metod som tar in scenen de ska bytas till
        //med nackdelen att metoden skulle vara aningens mer otydlig då namnet
        //skulle bli "setScene" istället för vilken scen
    }
    public void setMainScene()
    {
        tempScene = mainScene;
        stage.setScene(tempScene);
    }
    public void setScoreScene()
    {
        //tempScene = scoreScene;
        //stage.setScene(tempScene);
    }
    public void setStartScene()
    {
        tempScene = sc.getScene();
        stage.setScene(tempScene);
    }
    public int getBoardHeight()
    {
        return 300;
    }
    public int getBoardThicc()
    {
        return 506;
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

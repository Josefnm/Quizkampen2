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
    
    @Override
    public void start(Stage primaryStage) {
//        stage = primaryStage;
//        Scene scene = logInScene();
//        primaryStage.setScene(scene);
//        primaryStage.show();

        
        stage = primaryStage;
        sc = new StartScene(this);
        primaryStage.setTitle("Quiz!");
        primaryStage.setScene(sc.getScene());
        //setMain();
        primaryStage.show();
    }  
    
        public int femmman()
    {
        int fem = 5;
        return fem;
    }
    public void setQuestionScene(Scene activeScene)
    {
        stage.setScene(activeScene);
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
//        Scene scene = new Scene(root, 300, 250);
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

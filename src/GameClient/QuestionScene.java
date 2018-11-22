package GameClient;

import GameServer.Question;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;



public class QuestionScene {

   Main main;
   private Scene scene;
ArrayList<Question> questions;
   ArrayList<String> svar = new ArrayList<>(); //Questions ska in här istället
   ArrayList<Button> buttons = new ArrayList();
   HBox hbox = new HBox();
   Label label = new Label();
   String correctAnswer;
   int nextQuestion = 0;
       
   Object inline;

   QuestionScene(){
       System.out.println("test");
       


       BorderPane border = new BorderPane();
       GridPane grid = new GridPane();

       for(int i=0;i<4;i++){

           Button button = new Button();
           button.setMinSize(300,200);
           button.setOnAction(click);

           buttons.add(button);
       }

       grid.add((buttons.get(0)),0,0);
       grid.add((buttons.get(1)),0,1);
       grid.add((buttons.get(2)),1,0);
       grid.add((buttons.get(3)),1,1);

       Button next = new Button("Next");
       next.setOnAction(e -> main.setStartScene());
       next.setMinSize(50,25);
       next.setOnAction(setScene);
       hbox.getChildren().add(next);
       hbox.setAlignment(Pos.CENTER);
       label.setMinSize(600, 200);
       label.setAlignment(Pos.CENTER);
       border.setTop(label);
       grid.setAlignment(Pos.CENTER);
       border.setCenter(grid);
       border.setBottom(hbox);

       this.scene = new Scene(border);
   }


   EventHandler click = new EventHandler() {
       @Override
       public void handle(Event event) {
           Button btn = (Button)event.getSource();
           if(btn.getText().equals(correctAnswer)){
               btn.setStyle("-fx-background-color: Green");
               for(Button b : buttons){
                   b.setDisable(true);
               }
           }
           else{
               btn.setStyle("-fx-background-color: Red");
               for(Button b : buttons){
                   b.setDisable(true);
               }
           }
       }
   };
   

   EventHandler setScene = new EventHandler() {
       @Override
       public void handle(Event event) {
           setNextQuestion();
       }
   };
   
   public void setNextQuestion(){
        Button btn = new Button();
        int i=0;
        for(Button b : buttons){
            correctAnswer = questions.get(nextQuestion).getCorrectAnswer();
            b.setDisable(false);
            b.setStyle(btn.getStyle());
            b.setText(questions.get(nextQuestion).getAnswer(i));
            label.setText(questions.get(nextQuestion).getQuestion());
            i++;
            }
        nextQuestion++;
   }



   public Scene getScene(){
       return scene;
   }
   public ArrayList<Button> getButtons(){
       return buttons;
   }
   public void setButtonText(ArrayList <String> a){
       for(int i=0; i<4; i++){
           buttons.get(i).setText(a.get(i));
       }
   }
   public void setQuestions(ArrayList <Question> questions){
       this.questions = questions;
   }
}

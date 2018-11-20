package GameClient;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javax.swing.JButton;


public class QuestionScene {

   Main main;
   private Scene scene;
   String[] svar = new String[]{"Svar:","hej","sa","nej"};  //Questions ska in här istället
   ArrayList<Button> buttons = new ArrayList();
   HBox hbox = new HBox();

   QuestionScene(Main main){
       this.main = main;

       BorderPane border = new BorderPane();
       GridPane grid = new GridPane();

       for(int i=0;i<4;i++){

           Button button = new Button();
           button.setMinSize(300,200);
           button.setOnAction(click);
           button.setText(svar[i]);
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
       Label label = new Label("Fråga");
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
           if(btn.getText().equals(svar[0])){
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

       }
   };



   public Scene getScene(){
       return scene;
   }
   public ArrayList<Button> getButtons(){
       return buttons;
   }
}
package GameClient;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JButton;


public class QuestionScene {

    private Scene scene;
    String[] svar = new String[]{"Svar:","hej","sa","nej"};
    QuestionScene(){   
    
        ArrayList<Button> buttons = new ArrayList();
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
        
        Text text = new Text("Frågan som ställs");
        border.setTop(text);
        grid.setAlignment(Pos.CENTER);
        border.setCenter(grid);
        
        this.scene = new Scene(border);
    }
    
    
    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {
            Button btn = (Button)event.getSource();
            if(btn.getText().equals(svar[0])){
                btn.setStyle("-fx-background-color: Green");
            }
            else{
                btn.setStyle("-fx-background-color: Red");
            }
        }
    };
    
    public Scene getScene(){
        return scene;
    }
}

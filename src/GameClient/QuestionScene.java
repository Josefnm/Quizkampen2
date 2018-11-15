package GameClient;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.swing.JButton;


public class QuestionScene {

    Scene scene;
    BorderPane border = new BorderPane();
    GridPane grid = new GridPane(); 
    
    QuestionScene(){   
    
        for(int i = 0; i<2; i++){            
            for(int j = 0; j<2; j++){
                Button button = new Button("Svar:");
                button.setMinSize(50,50);
                button.setOnAction(click);
                grid.add(button, i, j);
            }
        }
        Text text = new Text("Frågan som ställs");
        border.setTop(text);
        grid.setAlignment(Pos.CENTER);
        border.setCenter(grid);
        
        this.scene = new Scene(border,300,300);
    }
    
    
    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println("hej");
        }
    };
    
    public Scene getScene(){
        return scene;
    }
}

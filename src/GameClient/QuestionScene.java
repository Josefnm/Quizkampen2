package GameClient;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javax.swing.JButton;


public class QuestionScene {

    Scene scene;
    GridPane grid = new GridPane(); 
    
    QuestionScene(){   
        ArrayList<JButton> buttons = new ArrayList<>();
    
        for(int i = 0; i<2; i++){            
            for(int j = 0; j<2; j++){
                Button button = new Button("");
                button.setOnAction(click);
                grid.add(button, i, j);
            }
        }
        Scene scene = new Scene(grid,300,300);
    }
    
    
    EventHandler click = new EventHandler() {
        @Override
        public void handle(Event event) {
            System.out.println("hej");
        }
    };
    
}

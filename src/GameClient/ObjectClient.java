/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameClient;

import java.awt.Insets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JButton;



/**
 *
 * @author julia
 */
public class ObjectClient extends Application {
   Label msg;
   Label question;
   Button[] buttons;
   private Socket socket;  
   
    ObjectInputStream in;
    ObjectOutputStream out;
    int PORT = 12345;
    Object input;
    Object inputList; 
    int questionNum = 0;
    
    public void play() throws IOException{
//        socket = new Socket("localhost", 12345);
//        in = new ObjectInputStream(socket.getInputStream());
//        out = new ObjectOutputStream(socket.getOutputStream());
    }
    
    
    public static void main(String[] args) throws IOException {
        launch(args);

    }

    @Override
    public void start(Stage myStage) throws Exception {
        myStage.setTitle("Welcome!");
        BorderPane rootNode = new BorderPane();
        GridPane grid = new GridPane();
        Scene myScene = new Scene(rootNode, 300, 100);
        myStage.setScene(myScene);
        msg = new Label("msg");
        question = new Label("question");
        Button a = new Button("a");
        Button b = new Button("b");
        Button c = new Button("c");
        Button d = new Button("d");
        Button next = new Button("next");
        Button[] buttons = new Button[]{a,b,c,d};
       next.setMinSize(50,25);
        
        grid.add(question, 0, 0);
        grid.add(a, 1, 0);
        grid.add(b, 1, 1);
        grid.add(c, 2, 0);
        grid.add(d, 2, 1);
        System.out.println("add");
        rootNode.setTop(msg);
        rootNode.setCenter(grid);
        rootNode.setBottom(next);
        myStage.show();
        System.out.println("show");
//        play();
        socket = new Socket("localhost", 12345);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        try{
            if((input = in.readObject()) != null){
                    if(input instanceof List){
                        msg.setText("Now start...");
//                        msg.repaint();
                        inputList = input;
                        setQuestion(inputList, questionNum++);
                    }
//                    else if(((String) input).equals("wait")){
//                        condition.setText("opponent answer now, wait!!!");
//                        condition.repaint();
//                    }
//                    else if(((String) input).startsWith("begin")){  //p2rond2
//                        condition.setText("Now start...");
//                        condition.repaint();
//                        setQuestion(inputList, ++questionNum);
//                        questionNum++;
//                    }
//                    else if(((String) input).startsWith("p1rond2")){    //p1rond2
//                        condition.setText("Now start...");
//                        condition.repaint();
//                        setQuestion(inputList, ++questionNum);
//                        questionNum++;
//                    }
//                    else if(((String) input).startsWith("p2end")){
//                        opponentInfo = ((String) input).split(",");   
//                        condition.setText(opponentInfo[1]+"..."+opponentInfo[2]);
//                        condition.repaint();
//                    }                
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
     public void setQuestion(Object obj, int index){}
    
}

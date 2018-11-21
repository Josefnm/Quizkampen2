package GameClient;

import GameServer.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Client{

    private final String address;
    private final int port;
    protected ObjectInputStream inStream;
    protected ObjectOutputStream outStream;
    Main main;
    QuestionScene qs;
    Object input;
    
    public Client() throws IOException, ClassNotFoundException {
        qs = new QuestionScene();
        this.address = "127.0.0.1";
        this.port = 12345;
        Question questionFromServer;
        String quest;
        String[] answers= new String[4];
        Socket socket = new Socket(address, port);
        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());
//        List<Object> input;
        try {
            input =inStream.readObject();
                if(input instanceof Question){
                    questionFromServer = (Question)input;
                    System.out.println("hej" + questionFromServer.getCorrectAnswer()+ "hej");
                    qs.label.setText(questionFromServer.getQuestion());
                    for(int i = 0; i<4; i++){
                        qs.svar.add(questionFromServer.getAnswer(i));
                    }
                    qs.correctAnswer = questionFromServer.getCorrectAnswer();
                    Collections.shuffle(qs.svar);
                    qs.setButtonText(qs.svar);
                
                
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }

    }

}

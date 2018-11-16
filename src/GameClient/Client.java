package GameClient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Client {

    int portNr = 12345;
    String hostIPAddress = "127.0.0.1";
    ObjectInputStream in;
    private ObjectOutputStream out;
    

    public Client() {

        try {
            Socket adressSocket = new Socket(hostIPAddress, portNr);
            out = new ObjectOutputStream(adressSocket.getOutputStream());
            in = new ObjectInputStream(adressSocket.getInputStream());

//            Thread inputListener = new Thread(() -> {
//               
//                try {
//                    while (true) {
//                        System.out.println("reading fromserver");
//                        //
//                        
//
//                    }
//                } catch (IOException | ClassNotFoundException ex) {
//                    System.out.println("inputListener");
//                }
//
//            });
//            inputListener.start();
        } catch (IOException ex) {

            System.out.println("fel");
            System.exit(1);
        }

    }

}

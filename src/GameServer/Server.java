package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket listener;
    Protocol protocol; 

    public Server() {
        protocol = new Protocol();
        try {
            listener = new ServerSocket(12345);
            System.out.println("Quiz Server is Running");
            while (true) {
                final Socket socket = listener.accept();
                Player player = new Player(socket, protocol);
                player.startGame();
                player.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

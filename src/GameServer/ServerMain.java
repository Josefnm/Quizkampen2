package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    private ServerSocket listener;
    private ServerProtocol protocol;


    public ServerMain() {

        
        protocol = new ServerProtocol();
        try {
            listener = new ServerSocket(12345);
            System.out.println("Quiz Server is Running");
            while (true) {
                final Socket socket = listener.accept();
                Player player = new Player(socket, protocol);
                player.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void main(String[] args) {
        ServerMain main = new ServerMain();

    }
}

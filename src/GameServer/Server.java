package GameServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket listener;

    public Server() {
        try {
            listener = new ServerSocket(12345);
            System.out.println("Quiz Server is Running");
            while (true) {
                final Socket socket = listener.accept();
                Player player = new Player(socket);
                player.start();
                //Game game = new Game();
                //Player playerO = new Player(listener.accept(), "O", game);
                //playerX.setOpponent(playerO);
                //playerO.setOpponent(playerX);
                //game.currentPlayer = playerX;
                //playerO.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
